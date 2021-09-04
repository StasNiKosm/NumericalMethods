package util.points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PointsFromDAlgoUtil {
    public static List<Point> toPoints(List<String> list){
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> tokens = Arrays.asList(list.get(i).trim().split(" "));
            points.add(new Point(Double.parseDouble(tokens.get(0)), Double.parseDouble(tokens.get(1))));
        }
        return points;
    }
}
