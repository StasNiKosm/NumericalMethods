package leastSquareMethod;

import java.util.List;

public class CalculateEquation {
    private TypeOfEquation typeOfEquation;

    public CalculateEquation(TypeOfEquation typeOfEquation) {
        this.typeOfEquation = typeOfEquation;
    }

    public double calculate(List<Double> coefficients, double x){
        switch (this.typeOfEquation){
            case POLYNOMIAL: return polynomialSolver(coefficients, x);
            case EXPONENTIAL: return exponentialSolver(coefficients, x);
            default: return Double.NaN;
        }
    }

    private double polynomialSolver(List<Double> coefficients, double x){
        double sum = 0.0;
        for (int i = 0; i < coefficients.size(); i++) {
            sum += Math.pow(x, coefficients.size() - i - 1) * coefficients.get(i);
        }
        return sum;
    }

    private double exponentialSolver(List<Double> coefficients, double x){
        return Math.pow(Math.E, -1 * (coefficients.get(0) * x)) * coefficients.get(1);
    }

}
