package euler;

import newton.method.NewtonMethod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ImplicitEulerMethod {
    private AbstractFunction[] functions;
    private double initialX;
    private List<Double> initialY;
    private double to;
    private double maxStep;
    private double minStep;
    private double exp;
    private List<Double> steps = new ArrayList<>();

    public ImplicitEulerMethod(double initialX, List<Double> initialY, double to, double maxStep, double minStep, double exp, AbstractFunction... functions) {
        this.functions = functions;
        this.initialX = initialX;
        this.initialY = initialY;
        this.to = to;
        this.maxStep = maxStep;
        this.minStep = minStep;
        this.exp = exp;
        steps.add(minStep);
        steps.add(minStep);
        steps.add(minStep);
    }

    public Map<Double, List<Double>> solve(){
        Map<Double, List<Double>> solve = new LinkedHashMap<>();
        solve.put(this.initialX, this.initialY);
        double step = this.stepSize(this.initialX, solve);
        double x = this.initialX;

        while (x <= this.to) {

            for (AbstractFunction function : this.functions) {
                function.setStep(step);
                function.setX(x);
                function.setY(solve.get(x));
            }
            List<Double> y = NewtonMethod.solve(this.exp, toArray(solve.get(x)), this.functions);
            if(y == null) return solve;

            x += step;
            step = stepSize(x, solve);
            solve.put(x, y);
        }

        return solve;
    }


    private static double[] toArray(List<Double> y){
        double[] arr = new double[y.size()];
        for (int i = 0; i < y.size(); i++) {
            arr[i] = y.get(i);
        }
        return arr;
    }

    private double stepSize(double x, Map<Double, List<Double>> map) {/*
        if(map.size() <= 2) return this.minStep;
        List<Double> steps = new ArrayList<>();
        List<Double> y1 = map.get(x - this.steps.get(this.steps.size() - 1) );
        List<Double> y2 = map.get(x - this.steps.get(this.steps.size() - 2));
        List<Double> y3 = map.get(x - this.steps.get(this.steps.size() - 3));
        double h1 = this.steps.get(this.steps.size() - 1);
        double h2 = this.steps.get(this.steps.size() - 2);
        for (int i = 0; i < this.functions.length; i++) {
           steps.add(-h1 / (h1 + h2) * (y1.get(i) - y2.get(i) - h1 * (y2.get(i) - y3.get(i)) / h2 ));
        }
        double e = Math.abs(steps.stream().reduce(Double::max).get());
        double step = h1;
        if(e > this.exp) step /= 2;
        if(e < this.exp/4) step *= 2;
        if(step > this.maxStep) {
            this.steps.add(this.maxStep);
            return this.maxStep;
        }
        if(step < this.minStep) {
            this.steps.add(this.minStep);
            return this.minStep;
        }
        this.steps.add(step);
        return step;
        */
        return this.minStep;
    }


}

