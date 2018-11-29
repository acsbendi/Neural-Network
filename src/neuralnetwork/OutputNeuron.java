package neuralnetwork;

import java.util.HashMap;
import java.util.Map;

class OutputNeuron extends OutputCalculatorNeuron<HiddenNeuron> {

    private final double INITIAL_WEIGHT_DIVISOR = 25;
    private double lastTarget = 0;
    private final Map<HiddenNeuron, Double> previousWeights = new HashMap<>();

    OutputNeuron(){
        bias /= INITIAL_WEIGHT_DIVISOR;
    }

    @Override
    void addInputConnection(HiddenNeuron inputNeuron){
        inputConnections.put(inputNeuron, getRandomNumber()/ INITIAL_WEIGHT_DIVISOR);
        previousWeights.put(inputNeuron, 0d);
    }

    double getOutput(){
        calculateCurrentOutput();

        return currentOutput;
    }

    private double getErrorOutputGradient(){
        return -(lastTarget - currentOutput);
    }

    private double getNetWeightGradientFor(HiddenNeuron hiddenNeuron){
        return hiddenNeuron.currentOutput;
    }

    @Override
    protected double getErrorWeightGradientFor(HiddenNeuron hiddenNeuron){
        return getErrorOutputGradient() * getOutputNetGradient() * getNetWeightGradientFor(hiddenNeuron);
    }

    double getDelta(){
        return getErrorOutputGradient() * getOutputNetGradient();
    }

    @Override
    protected double getErrorBiasGradient(){
        return getDelta();
    }

    void setLastTarget(double target){
        lastTarget = target;
    }

    @Override
    protected void onWeightUpdated(HiddenNeuron inputNeuron, double oldWeight) {
        previousWeights.replace(inputNeuron, oldWeight);
    }

    double getErrorOutputGradientFor(HiddenNeuron hiddenNeuron){
        return getDelta() * previousWeights.get(hiddenNeuron);
    }
}
