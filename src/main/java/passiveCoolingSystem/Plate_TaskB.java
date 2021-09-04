package passiveCoolingSystem;

import java.util.ArrayList;
import java.util.List;


//всё что здесь закоменченно, нужно для задачи с воздухом
public class Plate_TaskB extends AbstractPlate {

    private static final double THERMAL_CONDUCTIVITY_OF_ALUMINUM = 1.2;
   // private static final double THERMAL_CONDUCTIVITY_OF_AIR = 0.2;
    private static final double THERMAL_CONDUCTIVITY_OF_SILICON  = 30.0;
    private static final double THERMAL_CONDUCTIVITY_OF_COPPER =40;
    private static final double TEMPERATURE_ON_BORDER = 5.0;
    public static final double THERMAL_INFLUX = 5000.0;
    private static final double LENGTH = 1.0;
    public static final double PART_FOR_PROCESSOR = 0.2;
    public int countOfProcessorsPartsOfLine;
    private int countOfPartsOfLine;
    private double step;
    private enum Element{
        ALUMINUM,
        SILICON,
        COPPER,
        AIR
    }

    public Plate_TaskB(double step) {
        super();
        this.step = step;
        this.countOfProcessorsPartsOfLine = (int) (LENGTH * PART_FOR_PROCESSOR / step);
        this.countOfPartsOfLine = (int) (LENGTH * PART_FOR_PROCESSOR / step) * 5;
        this.matrixOfTemperatures = buildPlate();
    }

    public double getStep() {
        return step;
    }

    @Override
    public List<List<Double>> buildPlate() {
        List<List<Double>> matrixOfTemperatures = new ArrayList<>();
        for (int i = 0; i < this.countOfPartsOfLine; i++) {
            List<Double> lineOfTemperatures = new ArrayList<>();
            for (int j = 0; j < this.countOfPartsOfLine; j++)
                lineOfTemperatures.add(TEMPERATURE_ON_BORDER);
            matrixOfTemperatures.add(lineOfTemperatures);
        }
        return matrixOfTemperatures;
    }

    @Override
    public boolean isElementHeatBorder(int i, int j) {
        return j == 0 || j == this.countOfPartsOfLine - 1;
    }

    @Override
    public List<Double> getNeighboringTemperatures(int i, int j) {
        List<Double> neighbors = new ArrayList<>();
      //  if((i == 0 || i == this.countOfPartsOfLine - 1) && (j == 0 || j == this.countOfPartsOfLine - 1))
        //    neighbors.add(TEMPERATURE_ON_BORDER);
        if(j - 1 >= 0)
            neighbors.add(this.matrixOfTemperatures.get(i).get(j - 1));
        if(i - 1 >= 0)
            neighbors.add(this.matrixOfTemperatures.get(i - 1).get(j));
        if(j + 1 < this.countOfPartsOfLine)
            neighbors.add(this.matrixOfTemperatures.get(i).get(j + 1));
        if(i + 1 < this.countOfPartsOfLine)
            neighbors.add(this.matrixOfTemperatures.get(i + 1).get(j));
        return neighbors;
    }

    @Override
    public List<Double> getNeighboringBorders(int i, int j) {
        List<Double> neighbors = new ArrayList<>();
        if(j - 1 >= 0)
            neighbors.add(this.step);
        if(i - 1 >= 0)
            neighbors.add(this.step);
        if(j + 1 < this.countOfPartsOfLine)
            neighbors.add(this.step);
        if(i + 1 < this.countOfPartsOfLine)
            neighbors.add(this.step);
        return neighbors;
    }

    @Override
    public List<Double> getNeighboringThermalConductivities(int i, int j) {
        List<Double> neighbors = new ArrayList<>();
        /*
        if(j - 1 >= -1)
            addConductivities(neighbors, i, j - 1);
        if(i - 1 >= -1)
            addConductivities(neighbors, i - 1, j);
        if(j + 1 <= this.countOfPartsOfLine)
            addConductivities(neighbors, i, j + 1);
        if(i + 1 <= this.countOfPartsOfLine)
            addConductivities(neighbors, i + 1, j);
         */
        if(j - 1 >= 0)
            addConductivities(neighbors, i, j - 1);
        if(i - 1 >= 0)
            addConductivities(neighbors, i - 1, j);
        if(j + 1 < this.countOfPartsOfLine)
            addConductivities(neighbors, i, j + 1);
        if(i + 1 < this.countOfPartsOfLine)
            addConductivities(neighbors, i + 1, j);
        return neighbors;
    }

    @Override
    public boolean isFlux(int i, int j) {
        return definiteElement(i, j).equals(Element.SILICON);
    }

    private Element definiteElement(int i, int j){
     //   if(i == -1 || i == this.countOfPartsOfLine) return Element.AIR;
       // if(j == -1 || j == this.countOfPartsOfLine) return Element.AIR;
        if(((i >= 0 && i < this.countOfPartsOfLine * 2 / 5) || (i >= this.countOfPartsOfLine * 3 / 5 && i <= this.countOfPartsOfLine)) && j >= 0 && j < this.countOfPartsOfLine) return Element.ALUMINUM;
        if(i >= this.countOfPartsOfLine * 2 / 5  && i < this.countOfPartsOfLine * 3 / 5  && ((j >= 0 && j < this.countOfPartsOfLine * 2 / 5) || (j >= this.countOfPartsOfLine * 3 / 5 && j <= this.countOfPartsOfLine))) return Element.COPPER;
        return Element.SILICON;
    }

    private void addConductivities(List<Double> conductivities, int i, int j){
        switch (definiteElement(i, j)){
            case ALUMINUM: conductivities.add(THERMAL_CONDUCTIVITY_OF_ALUMINUM); break;
            case COPPER: conductivities.add(THERMAL_CONDUCTIVITY_OF_COPPER); break;
            case SILICON: conductivities.add(THERMAL_CONDUCTIVITY_OF_SILICON); break;
           // case AIR: conductivities.add(THERMAL_CONDUCTIVITY_OF_AIR); break;
            default: break;
        }
    }

}
