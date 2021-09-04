package integrals.method;

import integrals.funtions.Integrable;

/**
 * Это расчет интеграла методом трапеций.
 * Данный класс имеет один парметризирванный конструктор,
 * это сделанно для того, чтобы была возможнотсь вызывать статические методы
 * в методах данного класса только на его валидных обьектах.
 *
 * Реализует интерфейс DescribingMethod, по соглашению с которым класс должен
 * знать какой у него порядок точности, уметь считать интеграл с заданной точностью или
 * с заданным шагом интегрирования
 *
 * У данного метода поряок точности = 2
 *
 * Результатом может быть NaN, например, если мы решили посчитаь несобственный интеграл -
 * об этом стоит помнить, т.к. есть такая вероятность
 */
public class MethodTrapeze implements DescribingMethod {
    /**
     * поля класса
     */
    private static final int ORDER_OF_ACCURACY = 2;
    private Integrable function;
    private double from;
    private double to;

    /**
     * Еднственный параметризирванный конструктор класса
     * @param function - наша рабочая функция
     * @param from - с переданного значения идет подсчет
     * @param to - до этого значения идет подсчет
     */
    public MethodTrapeze(Integrable function, double from, double to) {
        this.function = function;
        this.from = from;
        this.to = to;
    }

    /**
     * Данный метод хорошо вызывать на обьектах класса,
     * если подсчет новых интегралов функций проиходит не часто(т.е. создание новых обьектов)
     * В своем теле вызывает статический метод на инстансе нашего класса и использует значения его полей
     *
     * Данный метод производит посчет тем точнее, чем меньше шаг
     * @param step - шаг(размер) итерирования по оси ОХ
     * @return - возвращается результат интегрированния
     */
    @Override
    public double solve(double step) {
        return this.solve(this.function, this.from, this.to, step);
    }

    /**
     * Этот метод создан для того, чтобы не создавать можество обьектов для посчета интегралов
     * Например, если нам нужно очень часто и много производить расчет, то создание большого количесва обьектов
     * может вызвать GC, который вероятно может на время чистки залокировать процесс.
     * Или для простого избежания лишнего кода и излишних 'new'
     *
     * Данный метод производит посчет тем точнее, чем меньше шаг
     * @param function - наша рабочая функция
     * @param from - с переданного значения идет подсчет
     * @param to - до этого значения идет подсчет
     * @param step - шаг(размер) итерирования по оси ОХ
     * @return - возвращается результат интегрированния
     */
    public static double solve(Integrable function, double from, double to, double step){
        double point = from;
        double sum = function.calculate(point);
        for (int i = 1; i < (to - from)/step; i++) {
            point += step;
            sum += 2 * function.calculate(point);
        }
        point += step;
        sum += function.calculate(point) ;
        return sum * step/2 ;
    }

    /**
     * @return - статический метод, который возвращает знаменаталь в формуле
     * для более точного подсчета значения
     */
    private static double denConst(){
        return 2*ORDER_OF_ACCURACY - 1;
    }

    /**
     * Данный метод хорошо вызывать (на обьектах класса),
     * если подсчет новых интегралов функций проиходит не часто(т.е. создание новых обьектов)
     * В своем теле вызывает статический метод на инстансе нашего класса и использует значения его полей
     *
     * Данный метод производит посчет с заданной точностью, по не достижению которой уменьшаейт свой шаг интегрирования в двое,
     * до того момента пока не будет достигнута заданная точность
     * @param step - шаг(размер) итерирования по оси ОХ
     * @param degreeOfAccurate - заданная точность
     * @return - возвращается результат интегрированния
     */
    @Override
    public double accurateSolve(double step, double degreeOfAccurate) {
        return this.accurateSolve(this.function, this.from, this.to, step, degreeOfAccurate);
    }

    /**
     * Этот метод создан для того, чтобы не создавать можество обьектов для посчета интегралов
     * Например, если нам нужно очень часто и много производить расчет, то создание большого количесва обьектов
     * может вызвать GC, который вероятно может на время чистки залокировать процесс.
     * Или для простого избежания лишнего кода и излишних 'new'
     *
     * Данный метод производит посчет с заданной точностью, по не достижению которой уменьшаейт свой шаг интегрирования в двое,
     * до того момента пока не будет достигнута заданная точность
     * @param function - наша рабочая функция
     * @param from - с переданного значения идет подсчет
     * @param to - до этого значения идет подсчет
     * @param step - шаг(размер) итерирования по оси ОХ
     * @param degreeOfAccurate - заданная точность
     * @return - возвращается результат интегрированния
     */
    public  static double accurateSolve(Integrable function, double from, double to, double step, double degreeOfAccurate){
        double h = step;
        double s;
        do {
            s = solve(function, from, to, h)/denConst() - solve(function, from, to, h/2)/denConst();
            h /= 2;
        } while (Math.abs(s) >= degreeOfAccurate);
        return solve(function, from, to, h);
    }

    /**
     * @return - возвращает порядок точности данного метода
     */
    @Override
    public int order() {
        return ORDER_OF_ACCURACY;
    }
}
