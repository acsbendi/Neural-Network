package neuralnetwork;

import java.util.Map;

class HiddenNeuron extends OutputCalculatorNeuron<InputNeuron>{

    private OutputNeuron outputNeuron;

    @Override
    protected void updateWeights() {
        for(Map.Entry<InputNeuron, Double> inputConnection : inputConnections.entrySet()){

        }
    }

    @Override
    protected void updateBias() {

    }

    public void setOutputNeuron(OutputNeuron outputNeuron) {
        this.outputNeuron = outputNeuron;
    }
}
