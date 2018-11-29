package neuralnetwork;

class HiddenNeuron extends OutputCalculatorNeuron<InputNeuron>{

    private OutputNeuron outputNeuron;

    @Override
    protected double getErrorWeightGradientFor(InputNeuron inputNeuron){
        return outputNeuron.getErrorOutputGradientFor(this) * getOutputNetGradient() * getNetWeightGradientFor(inputNeuron);
    }

    @Override
    protected double getErrorBiasGradient() {
        return outputNeuron.getDelta();
    }

    private double getNetWeightGradientFor(InputNeuron inputNeuron){
        return inputNeuron.currentOutput;
    }

    void setOutputNeuron(OutputNeuron outputNeuron) {
        this.outputNeuron = outputNeuron;
    }
}
