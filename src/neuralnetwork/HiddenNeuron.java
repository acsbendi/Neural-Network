package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

class HiddenNeuron extends Neuron{

    private List<InputNeuron> inputConnections = new ArrayList<>();
    private List<Double> currentWeights = new ArrayList<>();
    private OutputNeuron outputNeuron;

    void addInputConnection(InputNeuron inputNeuron){
        inputConnections.add(inputNeuron);
        currentWeights.add(randomGenerator.nextDouble());
    }

    void calculateCurrentOutput(){

    }
}
