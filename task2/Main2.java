import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class Main2 {
    public static void main(String[] args) {
        String path1 = args[0];
        String path2 = args[1];

        BigDecimal xr;
        BigDecimal yr;
        BigDecimal rr;

        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            String line = br.readLine();

            int whitespaceIndex = line.indexOf(" ");
            xr = new BigDecimal(line.substring(0, whitespaceIndex));
            yr = new BigDecimal(line.substring(whitespaceIndex + 1));

            line = br.readLine();
            rr = new BigDecimal(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path2))) {
            String line = br.readLine();
            while (line != null) {
                int whitespaceIndex = line.indexOf(" ");
                BigDecimal x = new BigDecimal(line.substring(0, whitespaceIndex));
                BigDecimal y = new BigDecimal(line.substring(whitespaceIndex + 1));
                int check = checkPoint(x, y, xr, yr, rr);
                switch (check) {
                    case 0: {
                        System.out.println("0");
                        break;
                    }
                    case 1: {
                        System.out.println("2");
                        break;
                    }
                    case -1: {
                        System.out.println("1");
                        break;
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int checkPoint(BigDecimal x, BigDecimal y, BigDecimal xr, BigDecimal yr, BigDecimal r) {
        BigDecimal r2 = x.subtract(xr).pow(2).add(y.subtract(yr).pow(2));
        return r2.compareTo(r.pow(2));
    }

}
