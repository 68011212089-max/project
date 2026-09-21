package makeAssign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GalaxyOfmet {
    public static void main(String[] args) {
        Frame frame = new Frame();
    }
}

/**
 * frame
 */
class Frame extends JFrame {

    public Frame() {
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Galaxy g = new Galaxy();
        add(g, BorderLayout.CENTER);

        setVisible(true);
    }

}

class Galaxy extends JPanel {
    Met[] met = new Met[10];
    MetMove[] metMoves = new MetMove[met.length];

    public Galaxy() {
        setLayout(null);
        setBackground(Color.BLACK);

        for (int i = 0; i < met.length; i++) {
            met[i] = new Met();
            add(met[i]);
            metMoves[i] = new MetMove(met[i], this);
            metMoves[i].start();
        }

        (new Collition(met, metMoves)).start();
    }
}

class Collition extends Thread {
    Met[] m = new Met[10];
    MetMove[] move = new MetMove[10];

    Collition(Met m[], MetMove[] move) {
        this.m = m;
        this.move = move;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            for (int i = 0; i < m.length; i++) {
                Met current = m[i];

                for (int k = 0; k < m.length; k++) {
                    if (i == k)
                        continue;

                    Met target = m[k];

                    if (Math.abs(target.getX() - current.getX()) < 50
                            && Math.abs(target.getY() - current.getY()) < 50) {
                        target.destroy();
                        move[k].setDie();
                        break;
                    }
                }
            }

        }
    }
}

class Met extends JPanel {
    Random random = new Random();
    String s = System.getProperty("user.dir") + File.separator + "images" + File.separator + (random.nextInt(10) + 1)
            + ".png";
    Image metImage = Toolkit.getDefaultToolkit().createImage(s);

    public Met() {
        setBounds((random.nextInt(500) + 1), (random.nextInt(500) + 1), 50, 50);
        setOpaque(false);
    }

    public void destroy() {
        setSize(0, 0);
        setLocation(-100, -100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(metImage, 0, 0, 50, 50, this);

    }

}

class MetMove extends Thread {
    Met met;
    Galaxy galaxy;
    Random random = new Random();
    private int _x = 0;
    private int _y = 0;

    boolean isDie = false;

    public MetMove(Met met, Galaxy galaxy) {
        this.met = met;
        this.galaxy = galaxy;
        while (_x == 0 && _y == 0) {
            set_x(random.nextInt(3) - 1);
            set_y(random.nextInt(3) - 1);
        }
    }

    @Override
    public void run() {
        while (!isDie) {
            met.setLocation(met.getX() + get_x(), met.getY() + get_y());
            crashBorder();
            try {
                Thread.sleep(100 / 3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void setDie() {
        isDie = true;
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    void crashBorder() {
        if (met.getX() <= 0) {
            set_x(1);
            set_y(random.nextInt(3) - 1);
        } else if ((met.getX() + met.getWidth()) >= galaxy.getWidth()) {
            set_x(-1);
            set_y(random.nextInt(3) - 1);
        }

        if (met.getY() <= 0) {
            set_y(1);
            set_x(random.nextInt(3) - 1);
        } else if ((met.getY() + met.getHeight()) >= galaxy.getHeight()) {
            set_y(-1);
            set_x(random.nextInt(3) - 1);
        }
    }
}
