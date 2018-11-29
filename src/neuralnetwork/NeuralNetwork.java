package neuralnetwork;

import java.util.List;

public class NeuralNetwork {

    private final List<InputNeuron> inputNeurons;
    private final List<HiddenNeuron> hiddenNeurons;
    private final OutputNeuron outputNeuron;

    NeuralNetwork(List<InputNeuron> inputNeurons, List<HiddenNeuron> hiddenNeurons, OutputNeuron outputNeuron) {
        this.inputNeurons = inputNeurons;
        this.hiddenNeurons = hiddenNeurons;
        this.outputNeuron = outputNeuron;
    }

    public void setInputs(List<Double> chemicalRepresentations){
        for (int i = 0; i < inputNeurons.size(); i++) {
            inputNeurons.get(i).currentOutput = chemicalRepresentations.get(i);
        }
    }

    private double calculateOutput(){
        for(HiddenNeuron hiddenNeuron : hiddenNeurons){
            hiddenNeuron.calculateCurrentOutput();
        }

        return outputNeuron.getOutput();
    }

    public void doBackPropagation(double target){
        double output = calculateOutput();

        outputNeuron.setLastTarget(target);
        outputNeuron.updateWeights();
    }
}
