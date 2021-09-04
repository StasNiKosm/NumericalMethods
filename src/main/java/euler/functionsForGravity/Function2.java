package euler.functionsForGravity;

import euler.AbstractFunction;

import java.util.List;

public class Function2 extends AbstractFunction {

    public Function2() {
    }

    public Function2(double step, double x, List<Double> y) {
        super(step, x, y);
    }

    @Override
    public double calculate(double[] args) {
        return args[1] - this.y.get(1) - this.step * args[3];
    }
}
