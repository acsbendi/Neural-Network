import java.util.ArrayList;
import java.util.List;

public class NeuronBuilder {

    private final int NEURON_COUNT = 81;

    private List<InputNeuron> inputNeurons = new ArrayList<>();
    private List<HiddenNeuron> hiddenNeurons = new ArrayList<>();
    private OutputNeuron outputNeuron = new OutputNeuron();

    private void createNeurons(){
        for(int i = 0; i < NEURON_COUNT; ++i){
            inputNeurons.add(new InputNeuron());
            hiddenNeurons.add(new HiddenNeuron());
        }
    }

    private void setConnections(){
        for(int i = 0; i < NEURON_COUNT; ++i){
            hiddenNeurons.get(i).addInputConnection(inputNeurons.get(i));
            outputNeuron.addInputConnection(hiddenNeurons.get(i));
        }
    }

    NeuralNetwork buildNeuralNetwork(){
        createNeurons();
        setConnections();

        return new NeuralNetwork(inputNeurons, hiddenNeurons, outputNeuron);
    }
}
