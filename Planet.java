import java.awt.*;
import java.io.File;
import java.util.Random;

public class Planet extends Thread {
    Random random = new Random();

    String planetPath = System.getProperty("user.dir")
            + File.separator + "images"
            + File.separator + (random.nextInt(10) + 1) + ".png";

    String bombPath = System.getProperty("user.dir")
            + File.separator + "images"
            + File.separator + "bomb.gif";

    Image planetImage = Toolkit.getDefaultToolkit().createImage(planetPath);
    Image bombImage = Toolkit.getDefaultToolkit().createImage(bombPath);
    Image currentImage = planetImage;

    int x, y;
    int vx, vy;
    int canvasWidth, canvasHeight;
    int planetSize;

    int speed = 1;
    boolean isCrashed = false;
    boolean isDead = false;

    public Planet(int canvasWidth, int canvasHeight, int planetSize) {
        this.planetSize = planetSize;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        x = random.nextInt(canvasWidth - planetSize) + planetSize;
        y = random.nextInt(canvasHeight - planetSize) + planetSize;

        do {
            vx = random.nextInt(7) - 3;
            vy = random.nextInt(7) - 3;
        } while (vx == 0 && vy == 0);

        speed = random.nextInt(10) + 10;
    }

    public void draw(Graphics g) {
        if (!isCrashed || !isDead)
            g.drawImage(currentImage, x, y, planetSize, planetSize, null);
    }

    public void destroy() {
        vx = 0;
        vy = 0;

        isCrashed = true;
        currentImage = bombImage;
    }

    protected void move() {
        x += vx;
        y += vy;
    }

    protected boolean isCollision(int tx, int ty) {
        if (isCrashed)
            return false;

        return Math.abs(x - tx) <= planetSize
                && Math.abs(y - ty) <= planetSize;
    }

    protected void collisionObserver() {
        if (x <= 0) {
            vx = 1;
            vy = random.nextInt(3) - 1;
            speed++;
        } else if ((x + planetSize) >= canvasWidth) {
            vx = -1;
            vy = random.nextInt(3) - 1;
            speed++;
        }

        if (y <= 0) {
            vx = random.nextInt(3) - 1;
            vy = 1;
            speed++;
        } else if ((y + planetSize) >= canvasHeight) {
            vx = random.nextInt(3) - 1;
            vy = -1;
            speed++;
        }
    }

    @Override
    public void run() {
        while (!isCrashed || !isDead) {
            move();
            collisionObserver();

            if (isCrashed) {
                try {
                    Thread.sleep(800);
                    isDead = true;
                } catch (Exception e) {
                }
            }

            try {
                Thread.sleep(100 / speed);
            } catch (Exception e) {
            }
        }
    }
}