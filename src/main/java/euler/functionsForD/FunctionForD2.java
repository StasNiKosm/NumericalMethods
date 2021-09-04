package euler.functionsForD;

import euler.AbstractFunction;

import java.util.List;

public class FunctionForD2 extends AbstractFunction {

    public FunctionForD2() {
    }

    public FunctionForD2(double step, double x, List<Double> y) {
        super(step, x, y);
    }

    @Override
    public double calculate(double[] args) {
        return args[1] - this.y.get(1) + this.step * (args[1] * args[1] - 2.5 * this.x / (1 + this.x * this.x));
    }
}
