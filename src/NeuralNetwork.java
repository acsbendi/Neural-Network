import java.util.List;

class NeuralNetwork {

    private final List<InputNeuron> inputNeurons;
    private final List<HiddenNeuron> hiddenNeurons;
    private final OutputNeuron outputNeuron;

    NeuralNetwork(List<InputNeuron> inputNeurons, List<HiddenNeuron> hiddenNeurons, OutputNeuron outputNeuron) {
        this.inputNeurons = inputNeurons;
        this.hiddenNeurons = hiddenNeurons;
        this.outputNeuron = outputNeuron;
    }
}
