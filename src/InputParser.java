import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InputParser {

    private final Scanner scanner = new Scanner(System.in);

    List<Double> getChemicalRepresentation(){
        String line = scanner.nextLine();
        String[] splitLine = line.split("\t");
        List<Double> result = new ArrayList<>();
        for(String word : splitLine){
            result.add(Double.parseDouble(word));
        }

        return result;
    }

    double getTemperature(){
        String line = scanner.nextLine();
        return Double.parseDouble(line);
    }
}
