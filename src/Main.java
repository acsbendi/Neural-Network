import neuralnetwork.NeuralNetwork;
import neuralnetwork.NeuralNetworkBuilder;

public class Main {

    public static void main(String[] args) {
        InputParser parser = new InputParser();
        NeuralNetworkBuilder neuralNetworkBuilder = new NeuralNetworkBuilder();
        NeuralNetwork neuralNetwork = neuralNetworkBuilder.buildNeuralNetwork();

    }
}
