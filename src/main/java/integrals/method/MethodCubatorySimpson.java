package integrals.method;

import integrals.funtions.DoubleVariableIntegrable;
import util.points.Point;

/**
 * Это расчет интеграла методом Кубаторного Симпсона.
 * Данный класс имеет один парметризирванный конструктор,
 * это сделанно для того, чтобы была возможнотсь вызывать статические методы
 * в методах данного класса только на его валидных обьектах.
 *
 * Результатом может быть NaN, например, если мы решили посчитаь несобственный интеграл -
 * об этом стоит помнить, т.к. есть такая вероятность
 */
public class MethodCubatorySimpson {
    /**
     * поля класса
     */
    DoubleVariableIntegrable function;
    private Point from;
    private Point to;
    private double step;

    /**
     * Единственный конструктор класса
     * (единственный потому что в методах класса вызываются другие методы, но уже статические =>
     * для вызов идет через оператор this. - на инстансе класса
     * @param function - наша рабочая функция
     * @param from - с переданного точки идет подсчет
     * @param to - до этой точки идет подсчет
     */
    public MethodCubatorySimpson(DoubleVariableIntegrable function, Point from, Point to) {
        this.function = function;
        this.from = from;
        this.to = to;
    }

    /**
     * Данный метод хорошо вызывать на обьектах класса,
     * если подсчет новых интегралов функций проиходит не часто(т.е. создание новых обьектов)
     * В своем теле вызывает статический метод на инстансе нашего класса и использует значения его полей
     *
     * Количесво шагов должно быть четным числом по направлению ОХ и ОУ =>
     * происходит проверка на данное условие и вывод сообщения
     *
     * Данный метод производит посчет тем точнее, чем меньше шаг
     * @param step - с переданныйм шагом произходинт подсчет интеграла
     * @return - возвращается значение инграла
     */
    public double solve(double step) {
        return this.solve(this.function, this.from, this.to, step);
    }

    /**
     * Этот метод создан для того, чтобы не создавать можество обьектов для посчета интегралов
     * Например, если нам нужно очень часто и много производить расчет, то создание большого количесва обьектов
     * может вызвать GC, который вероятно может на время чистки залокировать процесс.
     * Или для простого избежания лишнего кода и излишних 'new'
     *
     * Количесво шагов должно быть четным числом по направлению ОХ и ОУ =>
     * происходит проверка на данное условие и вывод сообщения
     *
     * Данный метод производит посчет тем точнее, чем меньше шаг
     * @param function - наша рабочая функция
     * @param from - с переданного точки идет подсчет
     * @param to - до этой точки идет подсчет
     * @param step - шаг(размер) итерирования по области сначала по ОХ потом по ОУ
     * @return - возвращается значение инграла
     */
    public static double solve(DoubleVariableIntegrable function, Point from, Point to, double step){
        int countOfSegments = (int) (Math.abs(from.getY() - to.getY()) / step);
        if(!(countOfSegments % 2 == 0)) return -1;
        Point localFrom = new Point(from);
        double sum = MethodCubatorySimpson.colverStr(function, localFrom, to, step);
        localFrom.setY(localFrom.getY()+step);
        for (int j = 1; j < countOfSegments; j++) {
            if(j % 2 == 0) sum += 2 *  MethodCubatorySimpson.colverStr(function, localFrom, to, step);
            else sum += 4 *  MethodCubatorySimpson.colverStr(function, localFrom, to, step);
            localFrom.setY(localFrom.getY()+step);
        }
        sum += MethodCubatorySimpson.colverStr(function, localFrom, to, step);
        return sum * step*step/9;
    }

    /**
     * Приватный метод, является вспомогательнвм для подсчета интеграла
     * Подсчитывает сумму точек через каждый шаг(заданного размера) помноженных
     * на соответствующие коэффициенты по оси ОХ
     * @param function - наша рабочая функция
     * @param from - с переданного точки идет подсчет
     * @param to - до этой точки идет подсчет
     * @param step - шаг(размер) итерирования по области сначала по ОХ потом по ОУ
     * @return - возвращает сумму точек через каждый шаг(заданного размера) помноженных
     * на соответствующие коэффициенты
     */
    private static double colverStr(DoubleVariableIntegrable function, Point from, Point to, double step){
        Point localFrom = new Point(from);
        double sum = 0.0;
        int countOfSegments = (int) (Math.abs(from.getX() - to.getX()) / step);
        sum += function.calculate(localFrom.getX(), localFrom.getY());
        localFrom.setX(localFrom.getX()+step);
        for (int j = 1; j < countOfSegments; j++) {
            if(j % 2 == 0) sum += 2 * function.calculate(localFrom.getX(), localFrom.getY());
            else sum += 4 * function.calculate(localFrom.getX(), localFrom.getY());
            localFrom.setX(localFrom.getX()+step);
        }
        sum += function.calculate(localFrom.getX(), localFrom.getY());
        return sum;
    }
}
