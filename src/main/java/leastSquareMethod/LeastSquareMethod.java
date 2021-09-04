package leastSquareMethod;

import gauss.expressions.Expression;
import gauss.expressions.ExpressionImpl;
import gauss.linearsystems.LinearSystem;
import gauss.methods.GaussMethod;
import util.points.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LeastSquareMethod {
    private List<Point> points;
    private static final int DEFAULT_POLYNOMIAL_DEGREE = 1;
    private int polynomialDegree;
    private List<Double> x;
    private List<Double> y;
    private TypeOfEquation typeOfEquation;

    public LeastSquareMethod(List<Point> points, int polynomialDegree) {
        this.points = points;
        this.polynomialDegree = polynomialDegree;
        this.typeOfEquation = TypeOfEquation.POLYNOMIAL;
    }

    //для експоненты
    public LeastSquareMethod(List<Point> points, TypeOfEquation type) {
        this.points = points;
        this.typeOfEquation = type;
    }

    public LeastSquareMethod(List<Point> points) {
        this.points = points;
        this.polynomialDegree = DEFAULT_POLYNOMIAL_DEGREE;
        this.typeOfEquation = TypeOfEquation.POLYNOMIAL;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public int getPolynomialDegree() {
        return polynomialDegree;
    }

    public void setPolynomialDegree(int polynomialDegree) {
        this.polynomialDegree = polynomialDegree;
    }

    public List<Double> solve(){
        return this.typeOfEquation == TypeOfEquation.POLYNOMIAL ? this.polynomialSolve() : this.exponentialSolve();
    }

    private List<Double> polynomialSolve(){
        doListPointsForPolynomial();
        return solver(this.x, this.y, this.polynomialDegree);
    }

    private List<Double> exponentialSolve(){
        doListPointsForExponential();
        List<Double> solve = solver(this.x, this.y, DEFAULT_POLYNOMIAL_DEGREE);
        solve.set(0, Math.pow(Math.E, solve.get(0)));
        return solve;
    }

    private static List<Double> solver(List<Double> x, List<Double> y, int polynomialDegree){
        int n = 2 * polynomialDegree ;
        List<Double> xCopy = new ArrayList<>(x);
        List<Double> yCopy = new ArrayList<>(y);
        List<List<Double>> linearSystem = new ArrayList<>();
        List<Double> freeMember = new ArrayList<>();
        linearSystem.add(new ArrayList<>());
        linearSystem.get(0).add((double) xCopy.size());
        freeMember.add(yCopy.stream().mapToDouble(Double::doubleValue).sum());
        for (int i = 1; i <= n; i++) {
            double sumXIter = xCopy.stream().mapToDouble(Double::doubleValue).sum();
            if(i > polynomialDegree){
                for (int j = i - polynomialDegree; j <= polynomialDegree; j++)
                    linearSystem.get(i - j).add(sumXIter);
            } else {
                linearSystem.add(new ArrayList<>());
                for (int j = 0; j <= i; j++)
                    linearSystem.get(i - j).add(sumXIter);
                freeMember.add(sumOfMultiplications(xCopy, yCopy));
            }
            if(i < n) multiplyListWith(xCopy, x);
        }
        List<Double> solve = solveLinearSystem(linearSystem, freeMember);
        Collections.reverse(solve);
        return solve;
    }

    private void doListPointsForPolynomial(){
        this.x = this.points.stream().map(p->p.getX()).collect(Collectors.toList());
        this.y = this.points.stream().map(p->p.getY()).collect(Collectors.toList());
    }

    private void doListPointsForExponential(){
        this.x = this.points.stream().map(p->p.getX()).collect(Collectors.toList());
        this.y = this.points.stream().map(p->Math.log(p.getY())).collect(Collectors.toList());
    }

    private static List<Double> solveLinearSystem(List<List<Double>> linearSystem, List<Double> freeMember){
        List<ExpressionImpl> list = new ArrayList<>();
        for (int i = 0; i < linearSystem.size(); i++) {
            linearSystem.get(i).add(freeMember.get(i));
            list.add(new ExpressionImpl(linearSystem.get(i)));
           // System.out.println(list.get(i));
        }
        return new GaussMethod(new LinearSystem<>(list)).solve();
    }

    private static List<Double> multiplyListWith(List<Double> l1, List<Double> l2){
        for (int i = 0; i < l1.size(); i++)
            l1.set(i, l1.get(i) * l2.get(i));
        return l1;
    }

    private static double sumOfMultiplications(List<Double> l1, List<Double> l2){
        double sum = 0.0;
        for (int i = 0; i < l1.size(); i++)
            sum += l1.get(i) * l2.get(i);
        return sum;
    }

}
