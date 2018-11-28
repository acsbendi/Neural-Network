package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

class OutputNeuron extends Neuron {

    private List<HiddenNeuron> inputConnections = new ArrayList<>();
    private List<Double> currentWeights = new ArrayList<>();

    void addInputConnection(HiddenNeuron hiddenNeuron){
        inputConnections.add(hiddenNeuron);
        currentWeights.add(randomGenerator.nextDouble());
    }

    double getOutput(){

    }
}
