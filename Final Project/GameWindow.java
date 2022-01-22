import javax.swing.*;;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.util.*;
public class GameWindow extends JPanel implements KeyListener {
    private int x = 200, y = 500;    
    private int xObj = 1450, yObj = 300; 
    private int xObj1 = 819, yObj1 = 600; 
    private int speed = 25;
    BufferedImage backg, retsim, obj, obj1;    
    private int score = 0;
    private String name;
    ArrayList<GameWindow> array = new ArrayList<GameWindow>();
    private boolean collision = false;

    public GameWindow() {
        setSize(new Dimension(1456, 819));
        setPreferredSize(new Dimension(1456, 819));
        setFocusable(true);
        addKeyListener(this);
    }

    public static void main(String args[]) {        
        GameWindow gw = new GameWindow();

        JFrame frame = new JFrame();
        frame.setTitle("Retsim Rider");
        frame.add(gw);
        frame.pack();
        frame.setSize(new Dimension(1456, 819));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void paint(Graphics g) {

        super.paint(g);

        try {       
            backg = ImageIO.read(new File("Background.jpg"));
            retsim = ImageIO.read(new File("retsim.png")); 
            obj = ImageIO.read(new File("object4.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
        if(yObj > 0 || yObj < 1456 && xObj > 0 || xObj < 819) { //makes obj move
            xObj -= speed(score);
        }
        if(yObj1 > 0 || yObj1 < 1456 && xObj1 > 0 || xObj1 < 819) { //makes obj1 move
            xObj1 -= speed(score);
        }
        if(xObj < 5) { //sets next objs corrds
            yObj = (int)(819*Math.random()+1);
            xObj = 1450;
            score++;
        }
        if(xObj1 < 5) { //sets next objs corrds
            yObj1 = (int)(1456*Math.random()+1);
            xObj1 = 819;
            score++;
        }
        g.drawImage(backg, 0, 0, null);

        g.drawImage(retsim, x, y, null); 

        g.drawImage(obj, xObj, yObj, null);
        obj1 = obj;
        g.drawImage(obj1, yObj1, xObj1, null);
        g.drawString("Score: " + score, 728, 50);

        if(xObj >= x && xObj <= x+146) {
            if(yObj >= y && yObj <= y+186) {   

                System.out.println("You lose! Better luck next time.");
                System.out.println("Your score was: " + score);

                try{
                    Thread.sleep(5000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }
        }
        if(yObj1 >= x && yObj1 <= x+146) {
            if(xObj1 >= y && xObj1 <= y+186) {   

                System.out.println("You lose! Better luck next time.");
                System.out.println("Your score was: " + score);

                try{
                    Thread.sleep(5000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }
        }
        repaint();
    }

    public int speed(int score) {
        if(score < 10) {
            speed = 25;
        }
        else if(score >= 10 && score < 20) {
            speed = 35;
        }
        else if(score >= 20 && score < 30) {
            speed = 45;
        }
        else if(score >= 30 && score < 40) {
            speed = 55;
        }
        else {
            speed = 75;
        }
        return speed;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 38) {
            if(y >= 0 && y <= 819) {
                if(y-50 <= 0) {
                    y = 0;
                }
                else {
                    y -= 50;
                }
            }

        }
        if(keyCode == 40) {
            if(y <= 819 && y >= 0) {
                if(y+50+186 >= 819) {
                    y = 819-186;
                }
                else {
                    y += 50;
                } 
            }

        }
        if(keyCode == 37) {
            if(x <= 1456 && x >= 0) {
                if(x-50 <= 0) {
                    x = 0;
                }
                else {
                    x -= 50;
                }
            }

        }
        if(keyCode == 39) {
            if(x >= 0 && x <= 1456) {
                if(x+50+146 >= 1456) {
                    x = 1456-146;
                }
                else {
                    x += 50;
                }
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        //not used

    }

    public void keyReleased(KeyEvent txt) {
        //not used

    }

}
