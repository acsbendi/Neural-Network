package neuralnetwork;

import java.util.HashMap;
import java.util.Map;

class OutputNeuron extends OutputCalculatorNeuron<HiddenNeuron> {

    private double lastTarget = 0;
    private Map<HiddenNeuron, Double> previousWeights = new HashMap<>();

    @Override
    void addInputConnection(HiddenNeuron inputNeuron){
        super.addInputConnection(inputNeuron);
        previousWeights.put(inputNeuron, 0d);
    }

    double getOutput(){
        calculateCurrentOutput();

        return currentOutput;
    }

    private double getErrorOutputGradient(double target){
        return -(target - currentOutput);
    }

    private double getOutputNetGradient(){
        return currentOutput * (1 - currentOutput);
    }

    private double getNetWeightGradientFor(HiddenNeuron hiddenNeuron){
        return hiddenNeuron.currentOutput;
    }

    private double getErrorWeightGradient(HiddenNeuron hiddenNeuron){
        return getErrorOutputGradient(lastTarget) * getOutputNetGradient() * getNetWeightGradientFor(hiddenNeuron);
    }

    private double getDelta(){
        return getErrorOutputGradient(lastTarget) * getOutputNetGradient();
    }

    private double getErrorBiasGradient(){
        return getDelta();
    }

    void setLastTarget(double target){
        lastTarget = target;
    }

    void updateWeightsAndBias(){
        updateWeights();
        updateBias();
    }

    @Override
    protected void updateWeights(){
        for(Map.Entry<HiddenNeuron, Double> inputConnection : inputConnections.entrySet()){
            double weightGradient = getErrorWeightGradient(inputConnection.getKey());
            double currentWeight = inputConnection.getValue();
            double newWeight = currentWeight - LEARNING_RATE * weightGradient;
            previousWeights.replace(inputConnection.getKey(), currentWeight);

            inputConnection.setValue(newWeight);
        }
    }

    @Override
    protected void updateBias(){
        double biasGradient = getErrorBiasGradient();
        bias -= LEARNING_RATE * biasGradient;
    }

    double getErrorOutputGradientFor(HiddenNeuron hiddenNeuron){
        return getDelta() * previousWeights.get(hiddenNeuron);
    }
}
