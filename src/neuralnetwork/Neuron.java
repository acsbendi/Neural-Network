package neuralnetwork;

import java.util.Random;

class Neuron{
    private static final Random randomGenerator = new Random();
    static final double LEARNING_RATE = 0.5;

    static double getRandomNumber(){
        return (randomGenerator.nextDouble());
    }

    double currentOutput;
}
