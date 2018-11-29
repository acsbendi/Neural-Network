import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
class InputEvaluater {
    static final List<Double> chemicalRepresentationMinimums = new ArrayList<>();
    static final List<Double> chemicalRepresentationMaximums = new ArrayList<>();
    static double temperatureMinimum;
    static double temperatureMaximum;

    void normalizeChemicalRepresentation(List<Double> chemicalRepresentation){
        for (int i = 0; i < chemicalRepresentation.size(); i++) {
            double currentValue = chemicalRepresentation.get(i);
            double normalizedValue = currentValue - chemicalRepresentationMinimums.get(i);
            normalizedValue /= (chemicalRepresentationMaximums.get(i) - chemicalRepresentationMinimums.get(i));

            chemicalRepresentation.set(i, normalizedValue);
        }
    }

    double normalizeTemperature(double original){
        return (original - temperatureMinimum)/(temperatureMaximum - temperatureMinimum);
    }

    @SuppressWarnings("SpellCheckingInspection")
    double denormalizeTemperature(double normalized){
        return normalized*(temperatureMaximum - temperatureMinimum) + temperatureMinimum;
    }
}
