package euler.functionsForGravity;

import euler.AbstractFunction;
import main.AppMain;

import java.util.List;

public class Function4 extends AbstractFunction {

    public Function4() {
    }

    public Function4(double step, double x, List<Double> y) {
        super(step, x, y);
    }

    @Override
    public double calculate(double[] args) {
        return args[3] - this.y.get(3) + this.step * AppMain.G * AppMain.M * args[1] / Math.pow(args[0] * args[0] + args[1] * args[1], 1.5);
    }
}
