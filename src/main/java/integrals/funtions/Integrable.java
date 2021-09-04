package integrals.funtions;

/**
 * Данный интерфейс является фунциональным => можно использовать на ее местя lambda expression
 * Описывает функцию одной переменной
 */
@FunctionalInterface
public interface Integrable {
    double calculate(double x);
}
