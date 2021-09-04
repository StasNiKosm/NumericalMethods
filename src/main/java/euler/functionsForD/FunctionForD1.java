package euler.functionsForD;

import euler.AbstractFunction;

import java.util.List;

public class FunctionForD1 extends AbstractFunction {

    public FunctionForD1() {
    }

    public FunctionForD1(double step, double x, List<Double> y) {
        super(step, x, y);
    }

    @Override
    public double calculate(double[] args) {
        return args[0] - this.y.get(0) + this.step * (args[0] * args[1] - Math.sin(this.x) / this.x);
    }
}
