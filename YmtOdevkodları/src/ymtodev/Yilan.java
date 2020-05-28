
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public final class Yilan extends JLabel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Kutu mHead = new Kutu();
    public Kutu1 mHead1 = new Kutu1();

    public Timer mTimer = null;
    public Timer mTimer1 = null;

    public Yem mYem = new Yem();

    public Random mRandom = null;

    public ArrayList<Kutu> Liste = new ArrayList<>();
    public ArrayList<Kutu1> Liste1 = new ArrayList<>();

    @Override

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10);

        g2.setColor(Color.red);

        g2.setStroke(new BasicStroke(10));

        g2.draw(rect);
    }

    Yilan() {
        mRandom = new Random(System.currentTimeMillis());

        addKeyListener(new KlavyeKontrol());
        setFocusable(true);

        mTimer = new Timer(100, new TimerKontrol());
        mTimer1 = new Timer(100, new TimerKontrol1());
        mTimer.start();
        mTimer1.start();

        Liste.add(mHead);
        Liste1.add(mHead1);
        for (int i = 1; i < 5; i++) {
            KuyrukEkle();
        }
        for (int i = 1; i < 5; i++) {
            KuyrukEkle1();
        }

        add(mYem);
        add(mHead);
        add(mHead1);
    }

    public void KuyrukEkle() {

        Kutu K = Liste.get(Liste.size() - 1).KutuOlustur();
        
        Liste.add(K);

        add(K);

    }
    public void KuyrukEkle1() {

        Kutu1 K1 = Liste1.get(Liste1.size() - 1).KutuOlustur1();
        
        Liste1.add(K1);

        add(K1);

    }
    

    public void YemEkle() {

        int Width = getWidth() - 40 - mYem.mGenislik;
        int Height = getHeight() - 40 - mYem.mGenislik;

        int PosX = 20 + Math.abs(mRandom.nextInt()) % Width;
        int PosY = 20 + Math.abs(mRandom.nextInt()) % Height;

        PosX = PosX - PosX % 20;
        PosY = PosY - PosY % 20;

        for (int i = 0; i < Liste.size(); i++) {
            if ((PosX == mHead.getX()) && (PosY == Liste.get(i).getY())) {
                YemEkle();
                return;
            }
        }
        for (int i = 0; i < Liste1.size(); i++) {
            if ((PosX == mHead1.getX()) && (PosY == Liste1.get(i).getY())) {
                YemEkle();
                return;
            }
        }

        mYem.setPosition(PosX, PosY);
    }

    public void HepsiniYurut() {
        for (int i = Liste.size() - 1; i > 0; i--) {
            Kutu Onceki = Liste.get(i - 1);
            Kutu Sonraki = Liste.get(i);

            Liste.get(i).Hareket();

            Sonraki.mYON = Onceki.mYON;

        }
        mHead.Hareket();
    }
    public void HepsiniYurut1() {
        for (int i = Liste1.size() - 1; i > 0; i--) {
            Kutu1 Onceki = Liste1.get(i - 1);
            Kutu1 Sonraki = Liste1.get(i);

            Liste1.get(i).Hareket();

            Sonraki.mYON = Onceki.mYON;

        }
        mHead1.Hareket();
    }

    public boolean CarpismaVarmi() {
        int Kalem = 10;

        int genislik = getWidth();
        int Yukseklik = getHeight();

        if (mHead.getX() <= 10) {
            return true;
        }

        if (mHead.getX() + mHead.mGenislik >= genislik - Kalem) {
            return true;
        }

        if (mHead.getY() <= 10) {
            return true;
        }

        if (mHead.getY() + mHead.mGenislik >= Yukseklik - Kalem) {
            return true;
        }

        for (int i = 1; i < Liste.size(); i++) {
            int X = Liste.get(i).getX();
            int Y = Liste.get(i).getY();

            if ((X == mHead.getX()) && (Y == mHead.getY())) {
                return true;
            }
        }

        if ((mYem.getX() == mHead.getX()) && (mYem.getY() == mHead.getY())) {
            KuyrukEkle();
            YemEkle();
        }
        if ((mYem.getX() == mHead1.getX()) && (mYem.getY() == mHead1.getY())) {
            KuyrukEkle();
            YemEkle();
        }

        return false;
    }
    public boolean CarpismaVarmi1() {
        int Kalem = 10;

        int genislik = getWidth();
        int Yukseklik = getHeight();

        if (mHead1.getX() <= 10) {
            return true;
        }

        if (mHead1.getX() + mHead1.mGenislik >= genislik - Kalem) {
            return true;
        }

        if (mHead1.getY() <= 10) {
            return true;
        }

        if (mHead1.getY() + mHead1.mGenislik >= Yukseklik - Kalem) {
            return true;
        }

        for (int i = 1; i < Liste1.size(); i++) {
            int X = Liste1.get(i).getX();
            int Y = Liste1.get(i).getY();

            if ((X == mHead1.getX()) && (Y == mHead1.getY())) {
                return true;
            }
        }

        if ((mYem.getX() == mHead1.getX()) && (mYem.getY() == mHead1.getY())) {
            KuyrukEkle1();
            YemEkle();
        }

        return false;
    }

    class KlavyeKontrol implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (mHead.mYON != YON.SAG) {
                    mHead.mYON = YON.SOL;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (mHead.mYON != YON.SOL) {
                    mHead.mYON = YON.SAG;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (mHead.mYON != YON.ASAGI) {
                    mHead.mYON = YON.YUKARI;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (mHead.mYON != YON.YUKARI) {
                    mHead.mYON = YON.ASAGI;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (mHead1.mYON != YON.SAG) {
                    mHead1.mYON = YON.SOL;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (mHead1.mYON != YON.SOL) {
                    mHead1.mYON = YON.SAG;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (mHead1.mYON != YON.ASAGI) {
                    mHead1.mYON = YON.YUKARI;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (mHead1.mYON != YON.YUKARI) {
                    mHead1.mYON = YON.ASAGI;
                }
            }

        }

        @Override

        public void keyReleased(KeyEvent e) {

        }

    }

    class TimerKontrol implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            HepsiniYurut();
            if (CarpismaVarmi()) {
                mTimer.stop();
            }

        }
    }
    class TimerKontrol1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            HepsiniYurut1();
            if (CarpismaVarmi1()) {
                mTimer1.stop();
            }

        }
    }
}
