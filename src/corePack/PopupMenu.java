package corePack;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
class PopupMenu extends Frame implements KeyListener
{
    int i=17;
    int j=90;
    boolean s1=false;
    boolean s2=false;

    public PopupMenu()
    {
        Frame f=new Frame("Pad");

        f.setSize(400,400);
        f.setLayout(null);
        Label l=new Label();
        l.setBounds(34,34,88,88);
        f.add(l);

        f.setVisible(true);
        f.addKeyListener(this);
    }

    public static void main(String arg[]){
        new PopupMenu();
    }

    public void keyReleased(KeyEvent e) {
        //System.out.println("re"+e.getKeyChar());

        if(i==e.getKeyCode())
        {
            s1=false;
        }

        if(j==e.getKeyCode())
        {
            s2=false;
        }
    }

    public void keyTyped(KeyEvent e) {
        //System.out.println("Ty");
    }

    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        System.out.println("pre"+e.getKeyCode());

        if(i==e.getKeyCode())
        {
            s1=true;
        }

        if(j==e.getKeyCode())
        {
            s2=true;
        }

        if(s1==true && s2==true)
        {
            System.out.println("EXIT NOW");
            System.exit(0);
        }
    }

    /** Handle the key released event from the text field. */

}