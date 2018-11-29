package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkBuilder {

    private final int NEURON_COUNT = 81;

    private final List<InputNeuron> inputNeurons = new ArrayList<>();
    private final List<HiddenNeuron> hiddenNeurons = new ArrayList<>();
    private final OutputNeuron outputNeuron = new OutputNeuron();

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
            hiddenNeurons.get(i).setOutputNeuron(outputNeuron);
        }
    }

    public NeuralNetwork buildNeuralNetwork(){
        createNeurons();
        setConnections();

        return new NeuralNetwork(inputNeurons, hiddenNeurons, outputNeuron);
    }
}
