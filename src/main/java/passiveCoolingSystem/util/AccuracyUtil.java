package passiveCoolingSystem.util;

import java.util.List;

public final class AccuracyUtil {
    public static boolean isAchievedAccuracy(List<List<Double>> before, List<List<Double>> after, double exp) {
        for (int i = 0; i < before.size(); i++)
            for (int j = 0; j < before.get(i).size(); j++)
                if (Math.abs(before.get(i).get(j) - after.get(i).get(j)) > exp)
                    return false;
        return true;
    }
}


