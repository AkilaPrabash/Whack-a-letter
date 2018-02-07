
package whackamole;
import java.util.PrimitiveIterator;
import java.util.Random;

public final class RandomIntGen{

    private PrimitiveIterator.OfInt randomIterator;

    public RandomIntGen(int min, int max) {
        randomIterator = new Random().ints(min, max + 1).iterator();
    }

    public int nextInt() {
        return randomIterator.nextInt();
    }
}
