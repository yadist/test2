import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main3 {

    public static void main(String[] args) {
        String path1 = args[0];
        String path2 = args[1];
        String pathResult = args[2];

        Map<Long, String> valueById = new HashMap<>();
        StringBuilder resultString = new StringBuilder();

        long id = -1;
        String value = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            String line = br.readLine();
            while (line != null) {

                if (line.contains("\"id\"")) {
                    int startIdIndex = line.lastIndexOf(":");
                    id = Long.parseLong(line.substring(startIdIndex + 1, line.length() - 1)
                            .strip());
                    if (value != null) {
                        valueById.put(id, value);
                        id = -1;
                        value = null;
                    }

                } else if (line.contains("\"value\"")) {
                    int startIdIndex = line.lastIndexOf(":");
                    value = line.substring(startIdIndex + 1, line.length() - 1)
                            .replace("\"", "")
                            .strip();
                    if (id != -1) {
                        valueById.put(id, value);
                        id = -1;
                        value = null;
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        id = -1;
        value = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
            String line = br.readLine();
            while (line != null) {

                if (line.contains("\"id\"")) {
                    int startIdIndex = line.lastIndexOf(":");
                    id = Long.parseLong(line.substring(startIdIndex + 1, line.length() - 1)
                            .strip());
                    if (value != null) {
                        resultString.append(value.replace("\"\"", "\""
                                + Optional.ofNullable(valueById.get(id)).orElse("")
                                + "\"")).append("\n");
                        value = null;
                        id = -1;
                    }
                    resultString.append(line).append("\n");

                } else if (line.contains("\"value\"")) {
                    if (id != -1) {
                        resultString.append(line.replace("\"\"", "\""
                                + Optional.ofNullable(valueById.get(id)).orElse("")
                                + "\"")).append("\n");
                        value = null;
                        id = -1;
                    } else {
                        value = String.copyValueOf(line.toCharArray());
                    }
                } else {
                    resultString.append(line).append("\n");
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathResult))) {
            bw.write(resultString.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}