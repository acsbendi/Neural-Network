package neuralnetwork;

import java.util.Map;

class OutputNeuron extends OutputCalculatorNeuron<HiddenNeuron> {

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

    private double getErrorWeightGradient(double target, HiddenNeuron hiddenNeuron){
        return getErrorOutputGradient(target) * getOutputNetGradient() * getNetWeightGradientFor(hiddenNeuron);
    }

    private double getErrorBiasGradient(double target){
        return getErrorOutputGradient(target) * getOutputNetGradient();
    }

    void updateWeightsAndBias(double target){
        updateWeights(target);
        updateBias(target);
    }

    private void updateWeights(double target){
        for(Map.Entry<HiddenNeuron, Double> inputConnection : inputConnections.entrySet()){
            double weightGradient = getErrorWeightGradient(target, inputConnection.getKey());
            double currentWeight = inputConnection.getValue();
            double newWeight = currentWeight - LEARNING_RATE * weightGradient;

            inputConnection.setValue(newWeight);
        }
    }

    private void updateBias(double target){
        double biasGradient = getErrorBiasGradient(target);
        bias -= LEARNING_RATE * biasGradient;
    }
}
