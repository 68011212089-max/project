package makeAssign;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GalaxyOfmet {
    public static void main(String[] args) {
        Frame frame = new Frame();
    }
}

/**
 * frame 
 */
class Frame extends JFrame {

    public Frame()  {
        setSize(550,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
    }
    
    
}
