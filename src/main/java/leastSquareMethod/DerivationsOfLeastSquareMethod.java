package leastSquareMethod;

import util.points.Point;

import java.util.List;
import java.util.stream.Collectors;

public final class DerivationsOfLeastSquareMethod {

    public static double derivation(List<Double> coefficients, List<Point> points, TypeOfEquation type){
        double sum = 0.0;
        for (int i = 0; i < points.size(); i++) {
            sum += (Math.abs(new CalculateEquation(type).calculate(coefficients, points.get(i).getX())) - Math.abs(points.get(i).getY()))
                    * (Math.abs(new CalculateEquation(type).calculate(coefficients, points.get(i).getX())) - Math.abs(points.get(i).getY()));
        }
        return Math.sqrt(sum / points.size());
    }

}
