import java.util.ArrayList;
import java.util.List;

class OutputNeuron {

    private List<HiddenNeuron> inputConnections = new ArrayList<>();
    private List<Double> currentWeights = new ArrayList<>();

    void addInputConnection(HiddenNeuron hiddenNeuron){
        inputConnections.add(hiddenNeuron);
    }

    double getOutput(){

    }
}
