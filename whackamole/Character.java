
package whackamole;


public class Character extends SetMM{
    
    public Character(int min, int max){
        super(0,41);
    }
    
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ,./;'[]1234567890";
    public char getChar(){
        return str.charAt(inRand());
        
    }
}
