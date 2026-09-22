import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {
    int canvasWidth = 1800;
    int canvasHeight = 1000;
    int planetSize = 50;

    Main(int planetCount) {
        setSize(canvasWidth, canvasHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Galaxy galaxy = new Galaxy(canvasWidth, canvasHeight, planetCount, planetSize);
        add(galaxy);

        Thread thread = new Thread(galaxy);
        thread.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        int planetCount = 5;

        String maxPlanet = JOptionPane.showInputDialog(null, "Number Of Planet", "Falling Star",
                JOptionPane.QUESTION_MESSAGE);

        try {
            planetCount = Integer.valueOf(maxPlanet);
        } catch (Exception e) {
        }

        new Main(planetCount);
    }
}
