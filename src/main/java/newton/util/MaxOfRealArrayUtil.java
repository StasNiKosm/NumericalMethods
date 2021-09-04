package newton.util;

/**
 * Утилитный класс - вспомогательный.
 * Служит для поиска максимального значения в масиве.
 * У нас массивы обычно совсем небольшие в программе, поэтому мудрить я не хотел. Так проще всего.
 */
public final class MaxOfRealArrayUtil {
    public static double max(final double[] a){
        double max = a[0];
        for(double v : a) max = Math.max(max, v);
        return max;
    }
}
