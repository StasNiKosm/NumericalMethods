package passiveCoolingSystem;

import passiveCoolingSystem.util.*;

import java.util.ArrayList;
import java.util.List;

public class UniformWithoutHeatInflux_TaskA {

    private static final double TIME_STEP = 0.1;

    private List<List<Double>> matrixOfTemperatures = new ArrayList<>();
    private AbstractPlate plate;
    private double exp;

    private static final int LIMIT_OF_ITERATIONS = 3000;

    public UniformWithoutHeatInflux_TaskA(AbstractPlate plate, double exp) {
        this.plate = plate;
        for (int i = 0; i < this.plate.matrixOfTemperatures.size(); i++)
            matrixOfTemperatures.add(new ArrayList<>(this.plate.matrixOfTemperatures.get(i)));
        this.exp = exp;
    }

    public List<List<Double>> solve() {
        for (int k = 0; k < LIMIT_OF_ITERATIONS; k++) {
            for (int i = 0; i < this.plate.getMatrixOfTemperatures().size(); i++) {
                for (int j = 0; j < this.plate.getMatrixOfTemperatures().get(i).size(); j++) {
                    if(this.plate.isElementHeatBorder(i,j)) continue;
                    this.matrixOfTemperatures.get(i).set(j, calculate(this.plate.getNeighboringTemperatures(i, j), this.plate.getNeighboringBorders(i, j), i, j));
                }
            }
            if (AccuracyUtil.isAchievedAccuracy(this.plate.matrixOfTemperatures, this.matrixOfTemperatures, this.exp)){
                System.out.println("COUNT OF ITERATIONS: " + (k + 1));
                return this.plate.getMatrixOfTemperatures();
            }
            CopyListOfListsUtil.copy(this.plate.matrixOfTemperatures, this.matrixOfTemperatures);

            //ВЫВОДИМ КАЖДЫЕ 30 ИТЕРАЦИЙ

            if(k%30==0){
                System.out.println("COUNT OF ITERATIONS: " + (k + 1));
                System.out.println(this);
            }

        }
        System.out.println("COUNT OF ITERATIONS: " + LIMIT_OF_ITERATIONS);
        return this.plate.getMatrixOfTemperatures();
    }

    private double calculate(List<Double> temperatures, List<Double> borders, int i, int j) {
        double sum = 0.0;
        for (int k = 0; k < temperatures.size(); k++)
            sum += borders.get(k) * temperatures.get(k);
        return this.plate.matrixOfTemperatures.get(i).get(j) + (TIME_STEP / Plate_TaskA.STEP_X)*(sum - this.plate.matrixOfTemperatures.get(i).get(j) * borders.stream().reduce(Double::sum).get());
    }

    @Override
    public String toString() {
        return this.plate.getMatrixOfTemperatures().stream()
                .map(x->x.toString().replace("[", "").replace("]", "\n").replace(",", "").replace(".", ","))
                .reduce(String::concat).get();

    }
}

