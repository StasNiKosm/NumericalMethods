package passiveCoolingSystem.util;

import java.util.List;

public final class CopyListOfListsUtil {
    public static void copy(List<List<Double>> to, List<List<Double>> from){
        for (int i = 0; i < from.size(); i++)
            for (int j = 0; j < from.get(i).size(); j++)
                to.get(i).set(j, from.get(i).get(j));
    }
}
