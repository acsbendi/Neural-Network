import neuralnetwork.NeuralNetwork;
import neuralnetwork.NeuralNetworkBuilder;

class Main {

    public static void main(String[] args) {
        InputParser parser = new InputParser();
        NeuralNetworkBuilder neuralNetworkBuilder = new NeuralNetworkBuilder();
        NeuralNetwork neuralNetwork = neuralNetworkBuilder.buildNeuralNetwork();

        Trainer trainer = new Trainer(neuralNetwork, parser);
        trainer.train();

        TestEvaluater testEvaluater = new TestEvaluater(parser, neuralNetwork);
        testEvaluater.evaluateTests();
    }
}
