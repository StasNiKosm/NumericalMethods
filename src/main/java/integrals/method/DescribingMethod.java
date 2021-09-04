package integrals.method;

/**
 * Давнный интерфейс описывает свойсва которыми должен обладать каждый метод
 * интегрирования функций (в данной программе используется только
 * для ф-ции одной переменной)
 */
public interface DescribingMethod {
    double solve(double step);
    double accurateSolve(double step, double degreeOfAccurate);
    int order();
}
