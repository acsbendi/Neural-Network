package neuralnetwork;

import java.util.HashMap;
import java.util.Map;

abstract class OutputCalculatorNeuron<InputNeuronType extends Neuron> extends Neuron {
    Map<InputNeuronType, Double> inputConnections = new HashMap<>();
    double bias = randomGenerator.nextDouble();

    void addInputConnection(InputNeuronType inputNeuron){
        inputConnections.put(inputNeuron, randomGenerator.nextDouble());
    }

    void calculateCurrentOutput(){
        double currentNet = getNet();
        currentOutput = applyOutputFunction(currentNet);
    }

    private double getNet(){
        double net = 0;
        for(Map.Entry<InputNeuronType, Double> inputConnection : inputConnections.entrySet()){
            net += inputConnection.getKey().currentOutput * inputConnection.getValue();
        }
        net += bias;

        return net;
    }

    private double applyOutputFunction(double net){
        return 1/(1 + Math.exp(-net));
    }

    void updateWeightsAndBias(){
        updateWeights();
        updateBias();
    }

    protected abstract void updateWeights();

    protected abstract void updateBias();
}
