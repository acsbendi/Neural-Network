package neuralnetwork;

import java.util.HashMap;
import java.util.Map;

abstract class OutputCalculatorNeuron<InputNeuronType extends Neuron> extends Neuron {
     final Map<InputNeuronType, Double> inputConnections = new HashMap<>();
     double bias = getRandomNumber();

    void addInputConnection(InputNeuronType inputNeuron){
        inputConnections.put(inputNeuron, getRandomNumber());
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

    private void updateWeights() {
        for(Map.Entry<InputNeuronType, Double> inputConnection : inputConnections.entrySet()){
            double weightGradient = getErrorWeightGradientFor(inputConnection.getKey());
            double currentWeight = inputConnection.getValue();
            double newWeight = currentWeight - LEARNING_RATE * weightGradient;
            onWeightUpdated(inputConnection.getKey(), currentWeight);

            inputConnection.setValue(newWeight);
        }
    }

    void onWeightUpdated(InputNeuronType inputNeuron, double oldWeight){

    }

    protected abstract double getErrorWeightGradientFor(InputNeuronType inputNeuron);

    private void updateBias(){
        double biasGradient = getErrorBiasGradient();
        bias -= LEARNING_RATE * biasGradient;
    }

    protected abstract double getErrorBiasGradient();

    double getOutputNetGradient(){
        return currentOutput * (1 - currentOutput);
    }
}
