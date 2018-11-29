import neuralnetwork.NeuralNetwork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Trainer extends InputEvaluater {

    private static final int TRAINING_SET_SIZE = 17011;
    private final NeuralNetwork neuralNetworkToTrain;
    private final InputParser inputParser;
    private final Queue<List<Double>> chemicalRepresentations = new LinkedList<>();
    private Queue<Double> temperatures = new LinkedList<>();

    Trainer(NeuralNetwork neuralNetworkToTrain, InputParser inputParser) {
        this.neuralNetworkToTrain = neuralNetworkToTrain;
        this.inputParser = inputParser;
    }

    void train(){
        readChemicalRepresentations();
        normalizeChemicalRepresentations();

        readTemperatures();
        normalizeTemperatures();

        for (int i = 0; i < TRAINING_SET_SIZE; i++) {
            trainOnce();
        }
    }

    private void readChemicalRepresentations(){
        for (int i = 0; i < TRAINING_SET_SIZE; i++) {
            readOneChemicalRepresentation();
        }
    }

    private void readTemperatures(){
        for (int i = 0; i < TRAINING_SET_SIZE; i++) {
            readOneTemperature();
        }
    }

    private void normalizeChemicalRepresentations(){
        setMinimums();
        setMaximums();

        for(List<Double> chemicalRepresentation : chemicalRepresentations)
            normalizeChemicalRepresentation(chemicalRepresentation);
    }

    private void normalizeTemperatures(){
        temperatureMinimum = temperatures.stream().min(Double::compareTo).orElse(0d);
        temperatureMaximum = temperatures.stream().max(Double::compareTo).orElse(100d);

        Queue<Double> normalizedTemperatures = new LinkedList<>();
        for (double temperature : temperatures) {
            double normalizedTemperature = normalizeTemperature(temperature);
            normalizedTemperatures.add(normalizedTemperature);
        }
        temperatures = normalizedTemperatures;
    }

    private void setMinimums(){
        for(List<Double> chemicalRepresentation : chemicalRepresentations){
            for (int i = 0; i < chemicalRepresentation.size(); i++) {
                if(chemicalRepresentationMinimums.size() <= i)
                    chemicalRepresentationMinimums.add(chemicalRepresentation.get(i));
                else if (chemicalRepresentationMinimums.get(i) > chemicalRepresentation.get(i))
                    chemicalRepresentationMinimums.set(i, chemicalRepresentation.get(i));
            }
        }
    }

    private void setMaximums(){
        for(List<Double> chemicalRepresentation : chemicalRepresentations){
            for (int i = 0; i < chemicalRepresentation.size(); i++) {
                if(chemicalRepresentationMaximums.size() <= i)
                    chemicalRepresentationMaximums.add(chemicalRepresentation.get(i));
                else if(chemicalRepresentationMaximums.get(i) < chemicalRepresentation.get(i))
                    chemicalRepresentationMaximums.set(i, chemicalRepresentation.get(i));
            }
        }
    }

    private void readOneChemicalRepresentation(){
        chemicalRepresentations.add(inputParser.getNextChemicalRepresentation());
    }

    private void readOneTemperature(){
        temperatures.add(inputParser.getNextTemperature());
    }

    private void trainOnce(){
        setInputs();
        double targetOutput = temperatures.remove();

        neuralNetworkToTrain.doBackPropagation(targetOutput);
    }

    private void setInputs(){
        List<Double> currentChemicalRepresentation = chemicalRepresentations.remove();

        neuralNetworkToTrain.setInputs(currentChemicalRepresentation);
    }
}
