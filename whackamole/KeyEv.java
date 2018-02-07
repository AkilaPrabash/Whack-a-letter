
package whackamole;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.lang.Character;

public class KeyEv extends JPanel implements KeyListener{
    jframe jf;
   
    public KeyEv(jframe jf){
   
        this.jf = jf;
        
    }
   
    public void keyPressed(KeyEvent e) {
   
    }

    public void keyReleased(KeyEvent e) {
        //System.out.println(e.getKeyChar());
        jf.pressed = Character.toUpperCase(e.getKeyChar());
       
    }
    
    public void keyTyped(KeyEvent e) {

    }
     
}