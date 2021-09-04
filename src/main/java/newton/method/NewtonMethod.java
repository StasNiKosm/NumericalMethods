package newton.method;

import gauss.expressions.ExpressionImpl;
import gauss.linearsystems.LinearSystem;
import gauss.methods.GaussMethod;
import newton.functions.FunctionOfSeveralVariables;
import newton.util.MaxOfRealArrayUtil;
import newton.util.PartialDerivativeUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс который отвечает за метод Ньютона: решенеие системы нелинейных уравнений многих переменных.
 * Ассимпотическая сложность O(K * n^3), где К - колличесво итеразий, а кубическая зависсимость из-за метода Гаусса,
 * которое применяется в данном методе. O(n^2) - сложность определения матрицы Якоби в точке - то что дополно делается в методе.
 * Скорость сходимости квадратичная.
 * Поддерживает любой размер систем уравнений.
 */
public class NewtonMethod {
    /**
     * константа, определяющая завершение наших итераций.
     */
    private static final int MAX_COUNT_OF_ITERATIONS = 100;

    /**
     * Статичекий метод => можно вызвать не создавая инстанса класса.
     * Мы проверяем, критерии завершения: норма вектора невязки(каждая итерация),
     * норма изменения вектора приближений(каждая итерация) и кол-во допустимых и тераций.
     * Проверяем на валидность всей системы (кол-во переданых координат первого приближения на реверново кол-ву функций в системе).
     * И собственно далее ищем приближенные значения, пока не приблизимся к решению с допустимой точностью.
     * @param exp - точность решения.
     * @param firstApproach - первое приближенное решение.
     * @param functions - система нелинейных уравнений.
     * @return - вектор решения системы нелинейных уравнений с заданной точностью,
     *           либо null, если количество функций != количеству координат точки первого приближения
     *           и если мы перевалили за недопустимое кол-во итераций.
     */
    public static List<Double> solve(double exp, final double[] firstApproach, FunctionOfSeveralVariables ... functions){
        if(firstApproach.length != functions.length) return null;
        int n = functions.length;
        double[] approach = firstApproach;
        double[] nextApproach = new double[n];
        for (int count = 0; count < MAX_COUNT_OF_ITERATIONS; count++) {
            double[] residual = residual(values(approach, functions));
            List<Double> solveOfJacobi = solveOfJacobiLinearSystem(approach, residual, functions);
            if(solveOfJacobi == null) return null;
            for (int i = 0; i < n; i++)
                nextApproach[i] = approach[i] + solveOfJacobi.get(i);
            if(!isSolver(exp, approach, nextApproach, residual)) approach = nextApproach;
            else {
                System.out.println("Number of iterations: " + count);
                List<Double> solve = new ArrayList<>(n);
                for (int i = 0; i < n; i++) solve.add(approach[i]);
                return solve;
            }
        }
        System.out.println("Number of iterations: MAX COUNT OF ITERATIONS = 100");
        return null;
    }

    /**
     * Вспомогательный метод.
     * Проверят два критерия сходимости итерационного метода Ньютона:
     * норма вектора невязки и норма изменения вектора приближений одновременно
     * должны быть меньше либо равно переданной точности (допустимой погрешности).
     * @param exp - точность решения.
     * @param approach - приближение.
     * @param nextApproach - следующее приближение.
     * @param residual - вектор невязки.
     * @return - true, если критерии сходимости выполнились,
     *           false, если критерии сходимости НЕ выполнились.
     */
    private static boolean isSolver(double exp, double[] approach, double[] nextApproach, double[] residual){
        int n = approach.length;
        double[] difference = new double[n];
        for (int i = 0; i < n; i++)
            difference[i] = nextApproach[i] >= 1 ? Math.abs(nextApproach[i] - approach[i]) / Math.abs(nextApproach[i]) : Math.abs(nextApproach[i] - approach[i]);
        double[] moduleOfResidual = new double[n];
        for (int i = 0; i < n; i++) moduleOfResidual[i] = Math.abs(residual[i]);
        return !(MaxOfRealArrayUtil.max(difference) >= exp) && !(MaxOfRealArrayUtil.max(moduleOfResidual) >= exp);
    }

    /**
     * Вспомогательный метод.
     * Ищет значения функции в приближенной точке для определения вектора невязи в последующем.
     * @param xk - точка в которой мы ищем значения функций.
     * @param functions - наша система нелинейных функций.
     * @return - вектор значений системы функций в точке.
     */
    private static double[] values(final double[] xk, FunctionOfSeveralVariables ... functions){
        double[] b = new double[functions.length];
        for (int i = 0; i < functions.length; i++) {
            b[i] = functions[i].calculate(xk);
        }
        return b;
    }

    /**
     * Вспомогательный метод.
     * Нам достоточно знать только вектор значений функции в приближенной точке
     * для определения вектора невязки, из-за того, что каждая функции должна в итоге равняться 0 =>
     * мы просто берем вектор с противоположным знаком.
     * @param b - вектор значений функции в приближенной токче.
     * @return - вектор невяски.
     */
    private static double[] residual(final double[] b){
        double[] residual = new double[b.length];
        for (int i = 0; i < b.length; i++) residual[i] = -1 * b[i];
        return residual;
    }

    /**
     * Приватным метод для нахождения вектор изменения приближенного значения.
     * Решается через матрицу Якоби, частные производные расчитываются численно точке.
     * Дадее решается система линейных уравнений (которая состоит из:
     * матрицы коэфициентов - Якобиан в точке, вектор свободных членов - невязка,
     * неизвестные - вектор изменения приближенного значения) методом Гаусса.
     *
     * @param xk - точка в которой считаются частные производные наших функций.
     * @param residual - вектор невязки для расчета векторора разницы до следующего приближенного решения.
     * @param functions - система наших нелинейных функций.
     * @return - вектор изменения приближенного значения между точкой которое мы передали и следующим приближенным значением.
     */
    private static List<Double> solveOfJacobiLinearSystem(double[] xk, double[] residual, FunctionOfSeveralVariables ... functions){
        int n = functions.length;
        List<ExpressionImpl> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Double> coefficients = new ArrayList<>(n + 1);
            for (int j = 0; j < n; j++)
                coefficients.add(PartialDerivativeUtil.partialDerivative(j, xk, functions[i]));
            coefficients.add(residual[i]);
            list.add(new ExpressionImpl(coefficients));
        }
        return new GaussMethod(new LinearSystem<>(list)).solve();
    }

}
