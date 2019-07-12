import java.util.Random;

public class Utils {

    public static double gaussian(double mean, double stdDev) {
        Random random = new Random();
        return mean + (stdDev * random.nextGaussian());
    }
}
