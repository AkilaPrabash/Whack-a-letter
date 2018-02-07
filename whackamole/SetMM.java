
package whackamole;


public class SetMM {
   RandomIntGen rand;
    private int min;
    private int max;
    
    public SetMM(int min, int max){
        this.min = min;
        this.max = max;
    }
    
    public int inRand(){
        rand = new RandomIntGen(min, max);
        return rand.nextInt();
    }
}
