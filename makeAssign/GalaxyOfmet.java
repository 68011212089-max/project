package makeAssign;

import java.awt.BorderLayout;
import java.awt.Color;
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
        
        add(new Galaxy(),BorderLayout.CENTER);
        

        setVisible(true);
    }
    
    
}

 class Galaxy extends JPanel {
    // Met n =new Met();
    public Galaxy() {
        setLayout(null);
        setBackground(Color.BLACK);
        Met met = new Met();
        super.add(met);
    }
    
    
}
class Met extends JPanel {
    String s =System.getProperty("user.dir")+File.separator+"images"+File.separator+"4.png";
    Image metImage = Toolkit.getDefaultToolkit().createImage(s);
    public Met() {
        setBounds(0, 0, 500, 500);
        System.out.println("44");
    }

    @Override
    public void paintComponent(Graphics g) {
       super.paintChildren(g);
        g.drawImage(metImage, 50, 30,50,50, this);
       System.out.println(getX());System.out.println(getX());
      
    }
    
    
}