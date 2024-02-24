import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main4 {

    public static void main(String[] args) {
        String path = args[0];

        List<Integer> array = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                if (line.isBlank()) {
                    continue;
                }
                array.add(Integer.parseInt(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(array);
        int mediana = array.get(array.size() / 2);

        int result = 0;

        for (Integer i: array) {
            result += Math.abs(mediana - i);
        }

        System.out.println(result);
    }
}