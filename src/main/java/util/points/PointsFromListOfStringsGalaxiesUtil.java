package util.points;

import util.points.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PointsFromListOfStringsGalaxiesUtil {

    public static final double SPEED_OF_LIGHT = 299792.458;

    public static List<Point> toPoints(List<String> list){
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder str = new StringBuilder(list.get(i).trim());
            for (int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == ' '){
                    int count = j+1;
                    while (str.charAt(count) == ' '){
                        count++;
                    }
                    str.delete(j, count-1);
                }
            }
            List<String> listTokens = Arrays.asList(str.toString().split(" "));
            points.add(new Point(Double.parseDouble(listTokens.get(listTokens.size() - 1)), Double.parseDouble(listTokens.get(listTokens.size() - 2)) * SPEED_OF_LIGHT));
        }
        return points;
    }
}
