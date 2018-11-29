import java.util.ArrayList;
import java.util.List;

class InputEvaluater {
    static final List<Double> minimums = new ArrayList<>();
    static final List<Double> maximums = new ArrayList<>();

    void normalizeChemicalRepresentation(List<Double> chemicalRepresentation){
        for (int i = 0; i < chemicalRepresentation.size(); i++) {
            double currentValue = chemicalRepresentation.get(i);
            double normalizedValue = currentValue - minimums.get(i);
            normalizedValue /= maximums.get(i);

            chemicalRepresentation.set(i, normalizedValue);
        }
    }
}
