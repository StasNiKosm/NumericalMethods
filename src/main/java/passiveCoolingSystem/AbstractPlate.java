package passiveCoolingSystem;

import java.util.List;

public abstract class AbstractPlate {

    protected List<List<Double>> matrixOfTemperatures;

    public AbstractPlate() {
        this.matrixOfTemperatures = buildPlate();
    }

    public List<List<Double>> getMatrixOfTemperatures() {
        return matrixOfTemperatures;
    }

    public void setMatrixOfTemperatures(List<List<Double>> matrixOfTemperatures) {
        this.matrixOfTemperatures = matrixOfTemperatures;
    }

    public abstract List<List<Double>> buildPlate();
    public abstract boolean isElementHeatBorder(int i, int j);
    public abstract List<Double> getNeighboringTemperatures(int i, int j);
    public abstract List<Double> getNeighboringBorders(int i, int j);
    public abstract List<Double> getNeighboringThermalConductivities(int i, int j);
    public abstract boolean isFlux(int i, int j);

}
