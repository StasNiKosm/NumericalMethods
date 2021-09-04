package euler;

import newton.functions.FunctionOfSeveralVariables;

import java.util.*;

public class ExplicitEulerMethod {
    private FunctionOfSeveralVariables[] functions;
    private double initialX;
    private List<Double> initialY;
    private double[] initialValues;
    private double to;
    private double maxStep;
    private double exp;

    public ExplicitEulerMethod(double initialX, List<Double> initialY, double to, double maxStep, double exp, FunctionOfSeveralVariables... functions) {
        this.functions = functions;
        this.initialX = initialX;
        this.initialY = initialY;
        this.to = to;
        this.maxStep = maxStep;
        this.exp = exp;
    }

    public  Map<Double, List<Double>> solve(){
        Map<Double, List<Double>> solve = new LinkedHashMap<>();
        solve.put(this.initialX, this.initialY);
        double step = this.stepSize(this.initialX, this.initialY);
        double x = initialX;
        double xNext = x;

        while (x <= this.to) {
            List<Double> yNext = new ArrayList<>();
            for (int i = 0; i < this.functions.length; i++) {
                yNext.add(this.functions[i].calculate(toArray(xNext, solve.get(x))) * step + solve.get(x).get(i));
            }
            xNext += step;
            step = stepSize(xNext, yNext);
            solve.put(xNext, yNext);
            x = xNext;
        }

        return solve;
    }

    double stepSize(double x, List<Double> y){
        List<Double> steps = new ArrayList<>();
        for (int i = 0; i < this.functions.length; i++) {
            steps.add(this.exp / (Math.abs(functions[i].calculate(toArray(x, y))) + this.exp/this.maxStep ));
        }
        /**
        double step = steps.stream().min(Double::compareTo).get();
        if(step >= this.maxStep) return this.maxStep;
        if(step <= this.exp) return this.exp;
        return step;
         **/
        return this.maxStep;
    }

    private static double[] toArray(double x, List<Double> y){
        double[] arr = new double[y.size() + 1];
        arr[0] = x;
        for (int i = 1; i <= y.size(); i++) {
            arr[i] = y.get(i-1);
        }
        return arr;
    }
}
