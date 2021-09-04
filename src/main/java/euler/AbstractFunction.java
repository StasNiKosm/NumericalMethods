package euler;

import newton.functions.FunctionOfSeveralVariables;

import java.util.List;

public abstract class AbstractFunction implements FunctionOfSeveralVariables {
    protected double step;
    protected double x;
    protected List<Double> y;

    public AbstractFunction(double step, double x, List<Double> y) {
        this.step = step;
        this.x = x;
        this.y = y;
    }

    protected AbstractFunction() {
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> y) {
        this.y = y;
    }
}
