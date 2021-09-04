package passiveCoolingSystem;

import java.util.ArrayList;
import java.util.List;

public class Plate_TaskA extends AbstractPlate {

    private static final double BORDER_OF_PLATE_1 = 1.0;
    private static final double BORDER_OF_PLATE_2 = 2.0;
    public static final double STEP_X = 0.1;
    private static final double STEP_Y = 0.2;
    private static final double TEMPERATURE_K1 = 12;
    private static final double TEMPERATURE_K2 = 35; //по умолчанию вся плата этой температуры


    @Override
    public List<List<Double>> buildPlate() {
        List<List<Double>> matrixOfTemperature = new ArrayList<>();
        int n1 = (int) (BORDER_OF_PLATE_1 / STEP_X); // =10
        int n2 = (int) (BORDER_OF_PLATE_2 / STEP_X); // =20
        for (int i = 0; i < n1; i++) {
            List<Double> lineOfTemperatures = new ArrayList<>();
            if(i < n1/2) {
                lineOfTemperatures.add(TEMPERATURE_K1); // температура на проводящей границе всегда известна
                for (int j = 1; j < n1; j++)
                    lineOfTemperatures.add(TEMPERATURE_K2); // 35 по умолчаю по всей плате
            } else {
                lineOfTemperatures.add(TEMPERATURE_K1);
                for (int j = 1; j < n2; j++)
                    lineOfTemperatures.add(TEMPERATURE_K2); // 35 по умолчаю по всей плате
            }
            matrixOfTemperature.add(lineOfTemperatures);
        }
        return matrixOfTemperature;
    }

    @Override
    public boolean isElementHeatBorder(int i, int j) {
        return j == 0 || j == (int)(BORDER_OF_PLATE_2 / STEP_X) - 1 && i >= 5 && i < (int)(BORDER_OF_PLATE_1 / STEP_X);
    }

    @Override
    public List<Double> getNeighboringTemperatures(int i, int j) {
        List<Double> neighbors = new ArrayList<>();
        if (j - 1 >= 0)
            neighbors.add(this.matrixOfTemperatures.get(i).get(j - 1));
        if (i - 1 >= 0 && j < this.matrixOfTemperatures.get(i - 1).size())
            neighbors.add(this.matrixOfTemperatures.get(i - 1).get(j));
        if (j + 1 < this.matrixOfTemperatures.get(i).size())
            neighbors.add(this.matrixOfTemperatures.get(i).get(j + 1));
        if (i + 1 < this.matrixOfTemperatures.size())
            neighbors.add(this.matrixOfTemperatures.get(i + 1).get(j));
        //System.out.println("i=" + i + " j=" + j + " " + neighbors);
        return neighbors;
    }

    @Override
    public List<Double> getNeighboringBorders(int i, int j) {
        List<Double> neighbors = new ArrayList<>();
        if (j - 1 >= 0)
            neighbors.add(STEP_Y);
        if (i - 1 >= 0 && j < this.matrixOfTemperatures.get(i - 1).size())
            neighbors.add(STEP_X);
        if (j + 1 < this.matrixOfTemperatures.get(i).size())
            neighbors.add(STEP_Y);
        if (i + 1 < this.matrixOfTemperatures.size())
            neighbors.add(STEP_X);
        return neighbors;
    }

    @Override
    public List<Double> getNeighboringThermalConductivities(int i, int j) {
        return null;
    }

    @Override
    public boolean isFlux(int i, int j) {
        return false;
    }
}
