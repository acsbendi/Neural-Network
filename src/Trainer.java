import neuralnetwork.NeuralNetwork;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Trainer extends InputEvaluater {

    private static final int TRAINING_SET_SIZE = 17011;
    private final NeuralNetwork neuralNetworkToTrain;
    private final InputParser inputParser;
    private final Queue<List<Double>> chemicalRepresentations = new LinkedList<>();

    Trainer(NeuralNetwork neuralNetworkToTrain, InputParser inputParser) {
        this.neuralNetworkToTrain = neuralNetworkToTrain;
        this.inputParser = inputParser;
    }

    void train(){
        readChemicalRepresentations();
        normalizeChemicalRepresentations();

        for (int i = 0; i < TRAINING_SET_SIZE; i++) {
            trainOnce();
        }
    }

    private void readChemicalRepresentations(){
        for (int i = 0; i < TRAINING_SET_SIZE; i++) {
            readOneChemicalRepresentation();
        }
    }

    private void normalizeChemicalRepresentations(){
        setMinimums();
        setMaximums();

        for(List<Double> chemicalRepresentation : chemicalRepresentations)
            normalizeChemicalRepresentation(chemicalRepresentation);
    }

    private void setMinimums(){
        for(List<Double> chemicalRepresentation : chemicalRepresentations){
            for (int i = 0; i < chemicalRepresentation.size(); i++) {
                if(minimums.size() <= i)
                    minimums.add(chemicalRepresentation.get(i));
                else if (minimums.get(i) > chemicalRepresentation.get(i))
                    minimums.set(i, chemicalRepresentation.get(i));
            }
        }
    }

    private void setMaximums(){
        for(List<Double> chemicalRepresentation : chemicalRepresentations){
            for (int i = 0; i < chemicalRepresentation.size(); i++) {
                if(maximums.size() <= i)
                    maximums.add(chemicalRepresentation.get(i));
                else if(maximums.get(i) < chemicalRepresentation.get(i))
                    maximums.set(i, chemicalRepresentation.get(i));
            }
        }
    }

    private void readOneChemicalRepresentation(){
        chemicalRepresentations.add(inputParser.getNextChemicalRepresentation());
    }

    private void trainOnce(){
        setInputs();
        double targetOutput = inputParser.getNextTemperature();

        neuralNetworkToTrain.doBackPropagation(targetOutput);
    }

    private void setInputs(){
        List<Double> currentChemicalRepresentation = chemicalRepresentations.remove();

        neuralNetworkToTrain.setInputs(currentChemicalRepresentation);
    }
}
