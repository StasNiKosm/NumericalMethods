package newton.util;

import newton.functions.FunctionOfSeveralVariables;

/**
 * Утилитный класс - вспомогательный класс.
 * Служит для расчета частной производной функции любого количества переменных в точке.
 */
public final class PartialDerivativeUtil {
    /**
     * величина приращения, которая в последующем домножается на соответствующую величину хi
     */
    private static final double PARTIAL_DERIVATIVE_INCREMENT = 0.01;

    /**
     * Метод для расчета часной производной переменной, указанной через переданный индекс,
     * переданной функции в переданной точке.
     * @param index - индекс переменной по которой находится частная производная.
     * @param xk - координаты точки, в которой ищется частная производная.
     * @param functions - функция нескольких переменных.
     * @return - численной значение частной производной в точке.
     */
    public static double partialDerivative(int index, final double[] xk, FunctionOfSeveralVariables functions){
        double[] approach = new double[xk.length];
        double x = xk[index] == 0.0 ? 1 : xk[index];
        for (int k = 0; k < xk.length; k++)
            approach[k] = k == index ?  xk[k] + PARTIAL_DERIVATIVE_INCREMENT * x : xk[k];
        return (functions.calculate(approach) - functions.calculate(xk)) / (PARTIAL_DERIVATIVE_INCREMENT * x);
    }
}
