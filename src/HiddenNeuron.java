import java.util.ArrayList;
import java.util.List;

class HiddenNeuron {

    private List<InputNeuron> inputConnections = new ArrayList<>();
    private List<Double> currentWeights = new ArrayList<>();
    private OutputNeuron outputNeuron;

    void addInputConnection(InputNeuron inputNeuron){
        inputConnections.add(inputNeuron);
    }

    void calculateCurrentOutput(){

    }
}
