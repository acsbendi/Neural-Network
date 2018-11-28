package neuralnetwork;

import java.util.HashMap;
import java.util.Map;

abstract class OutputCalculatorNeuron extends Neuron {
    private Map<InputNeuron, Double> inputConnections = new HashMap<>();
    private double bias = randomGenerator.nextDouble();
    private double currentOutput;

    void addInputConnection(InputNeuron inputNeuron){
        inputConnections.put(inputNeuron, randomGenerator.nextDouble());
    }

    void calculateCurrentOutput(){
        double currentNet = getNet();
        currentOutput = applyOutputFunction(currentNet);
    }

    private double getNet(){
        double net = 0;
        for(Map.Entry<InputNeuron, Double> inputConnection : inputConnections.entrySet()){
            net += inputConnection.getKey().getCurrentValue() * inputConnection.getValue();
        }
        net += bias;

        return net;
    }

    private double applyOutputFunction(double net){
        return 1/(1 + Math.exp(-net));
    }
}
