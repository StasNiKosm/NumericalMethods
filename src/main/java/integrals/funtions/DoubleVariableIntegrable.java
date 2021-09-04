package integrals.funtions;

/**
 * Данный интерфейс является фунциональным => можно использовать на ее местя lambda expression
 * Описывает функцию двух переменных
 */
@FunctionalInterface
public interface DoubleVariableIntegrable {
    double calculate(double x, double y);
}
