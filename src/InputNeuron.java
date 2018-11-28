class InputNeuron extends Neuron {

    private double currentValue;

    void setCurrentValue(double currentValue){
        this.currentValue = currentValue;
    }

    double getCurrentValue(){
        return currentValue;
    }
}
