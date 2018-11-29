import neuralnetwork.NeuralNetwork;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
class TestEvaluater extends InputEvaluater{

    private static final int TEST_SET_SIZE = 4252;

    private final InputParser parser;
    private final NeuralNetwork neuralNetwork;

    TestEvaluater(InputParser parser, NeuralNetwork neuralNetwork) {
        this.parser = parser;
        this.neuralNetwork = neuralNetwork;
    }

    void evaluateTests(){
        for (int i = 0; i < TEST_SET_SIZE; i++) {
            evaluateTest();
        }
    }

    private void evaluateTest(){
        List<Double> chemicalRepresentation = parser.getNextChemicalRepresentation();
        normalizeChemicalRepresentation(chemicalRepresentation);

        neuralNetwork.setInputs(chemicalRepresentation);

        double result = neuralNetwork.calculateOutput();
        System.out.println(String.valueOf(result));
    }
}
