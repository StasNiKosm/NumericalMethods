package euler.functionsForGravity;

import euler.AbstractFunction;

import java.util.List;

public class Function1 extends AbstractFunction {

    public Function1() {
    }

    public Function1(double step, double x, List<Double> y) {
        super(step, x, y);
    }

    @Override
    public double calculate(double[] args) {
        return args[0] - this.y.get(0) - this.step * args[2];
    }
}
