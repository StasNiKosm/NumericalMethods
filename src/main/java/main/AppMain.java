package main;

import euler.ExplicitEulerMethod;
import euler.ImplicitEulerMethod;
import euler.functionsForD.FunctionForD1;
import euler.functionsForD.FunctionForD2;
import euler.functionsForGravity.Function1;
import euler.functionsForGravity.Function2;
import euler.functionsForGravity.Function3;
import euler.functionsForGravity.Function4;
import parsersExpression.expressionSolver.ExpressionStr;
import parsersExpression.tokenTree.BuilderTokenTree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AppMain{

    public static final double G = 66.74184;
    public static final double M = 1989;

    public static void main(String[] args) {

    }











/**
    private static double max(List<List<Double>> l){
        List<Double> list = new ArrayList<>();
        for (List<Double> doubles : l) list.add(doubles.stream().reduce(Double::max).get());
        return list.stream().reduce(Double::max).get();
    }

    public static void main(String[] args) {


        List<Double> initialY1 = new ArrayList<>();
        initialY1.add(3.0);
        initialY1.add(1.0);

        List<Double> initialY = new ArrayList<>();
        initialY.add(152.098233);
        initialY.add(0.0);
        initialY.add(0.0);
        initialY.add(29.3);


        List<Double> initialY2 = new ArrayList<>();
        initialY2.add(0.0);
        initialY2.add(0.0);

         //ПРИМЕР ВЫЗОВА НЕЯВНОГО МЕТОДА

         Map<Double, List<Double>> map = new ImplicitEulerMethod(0.0, initialY, 100.0, 0.1, 0.01, 0.0001,
         new Function1(), new Function2(), new Function3(), new Function4()).solve();

       // Map<Double, List<Double>> map2 = new ImplicitEulerMethod(0.0001, initialY2, 1.0, 0.1, 0.1, 0.001,
         //       new FunctionForD1(), new FunctionForD2()).solve();

     //   Map<Double, List<Double>> map1 = new ExplicitEulerMethod(0.0001, initialY2, 1.0, 0.1, 0.001,
       //         x -> - x[1] * x[2] + Math.sin(x[0])/x[0],
         //       y -> - y[2] * y[2] + 2.5 * y[0] / (1 + y[0] * y[0]) ).solve();

        try(FileWriter writer = new FileWriter("src/main/resources/points.csv")) {
            writer.write(map.entrySet().stream().map(x->( x.getKey().toString()
                    + " "
                    + x.getValue().toString().substring(1, x.getValue().toString().length()-2).replace(",", "")
                    + "\n").replace(".", ",")).reduce(String::concat).get());
        } catch (IOException e) {
            e.printStackTrace();
        }


/**
         Map<Double, List<Double>> map = new ExplicitEulerMethod(0.0, initialY, 10000.0, 0.1, 0.001,
         x -> x[3],
         y -> y[4],
         v -> -G * M * v[1] / Math.pow(v[1] * v[1] + v[2] * v[2], 1.5),
         u -> -G * M * u[2] / Math.pow(u[1] * u[1] + u[2] * u[2], 1.5) ).solve();
         /** ПРИМЕР ВЫЗОВА ЯВНОГО МЕТОДА
         Map<Double, List<Double>> map1 = new ExplicitEulerMethod(0.0, initialY1, 1.0, 0.01, 0.001,
         x -> x[1] * x[1] / (x[2] - x[0]),
         y -> y[1] + 1 ).solve();


         Map<Double, List<Double>> map2 = new ImplicitEulerMethod(0.0001, initialY2, 1.0, 0.1, 0.001,
         new FunctionForD1(),
         new FunctionForD2().solve();



        //Задача А
        //UniformWithoutHeatInflux_TaskA uniformWithoutHeatInflux = new UniformWithoutHeatInflux_TaskA(new Plate_TaskA(), 0.0001);
        //uniformWithoutHeatInflux.solve();
        //System.out.println(uniformWithoutHeatInflux);


        //Задача В
        //HeterogeneousWithHeatInflux_TaskB heterogeneousWithHeatInflux_taskB = new HeterogeneousWithHeatInflux_TaskB(new Plate_TaskB(0.05 ), 0.0001);
        //heterogeneousWithHeatInflux_taskB.solve();
        //System.out.println(heterogeneousWithHeatInflux_taskB);


    }
*/
}








