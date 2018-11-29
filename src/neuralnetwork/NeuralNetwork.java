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

    public double calculateOutput(){
        for(HiddenNeuron hiddenNeuron : hiddenNeurons){
            hiddenNeuron.calculateCurrentOutput();
        }

        return outputNeuron.getOutput();
    }

    public void doBackPropagation(double target){
        calculateOutput();

        outputNeuron.setLastTarget(target);
        /*System.out.println("Current output " + outputNeuron.currentOutput);
        System.out.println("Delta: " + outputNeuron.getDelta());
        System.out.println("Weights before " + outputNeuron.inputConnections.values().toString());
        System.out.println("bias before " + outputNeuron.bias);*/
        outputNeuron.updateWeightsAndBias();
       /* System.out.println("Weights after " + outputNeuron.inputConnections.values().toString());
        System.out.println("bias after " + outputNeuron.bias);*/

        for(HiddenNeuron hiddenNeuron : hiddenNeurons)
            hiddenNeuron.updateWeightsAndBias();
    }
}
