
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

public final class Kutu1 extends JLabel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public int mGenislik = 20;

    public int mYON = YON.SAG;

    Kutu1() {
        setBounds(200, 200, mGenislik, mGenislik);
    }

    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2);

        g2.setColor(Color.white);

        g2.setStroke(new BasicStroke(2));

        g2.draw(rect);

        g2.setColor(Color.green);

        g2.fill(rect);

    }

    public void SolaGit() {
        int PosX = getX();
        int PosY = getY();

        PosX -= mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }

    public void SagaGit() {
        int PosX = getX();
        int PosY = getY();

        PosX += mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }

    public void YukariGit() {
        int PosX = getX();
        int PosY = getY();

        PosY -= mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }

    public void AsagiGit() {
        int PosX = getX();
        int PosY = getY();

        PosY += mGenislik;
        setBounds(PosX, PosY, mGenislik, mGenislik);
    }

    
    public Kutu1 KutuOlustur1() {
        Kutu1 K1 = new Kutu1();

        int X = getX();
        int Y = getY();

        K1.setBounds(X, Y, mGenislik, mGenislik);

        K1.mYON = -mYON;

        K1.Hareket();

        K1.mYON = mYON;
        return K1;
    }
    

    public void Hareket() {
        switch (mYON) {
            case YON.SOL:
                SolaGit();
                break;
            case YON.SAG:
                SagaGit();
                break;
            case YON.ASAGI:
                AsagiGit();
                break;
            default:
                YukariGit();
                break;
        }

    }

}
