package makeAssign;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
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

    public Frame()  {
        setSize(550,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new Met());

        setVisible(true);
    }
    
    
}

 class Galaxy extends JPanel {
    // Met n =new Met();
    public Galaxy() {
        setLayout(null);
        // setBackground(Color.BLACK);
        
       
        
    }
    
    
}
class Met extends JPanel {
    String s =System.getProperty("user.dir")+File.separator+"4.png";
    Image metImage = Toolkit.getDefaultToolkit().createImage(s);
    public Met() {
        
        System.out.println(s +"");
        
        
    }

    @Override
    public void paint(Graphics g) {
       g.drawImage(metImage, 50, 50,1000,1000, this);
    }
    
    
}