public class Main1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder result = new StringBuilder();

        int j = 0;
        int endIndex;
        int i;
        do {
            j++;
            endIndex = getEndIntervalElement(m, j);
            i = getByIndex(endIndex, n);
            result.append(getByIndex(getStartIntervalElement(m, j), n));
        } while (i != 1);

        System.out.println(result);
    }

    private static int getByIndex(int index, int n) {
        return index % n + 1;
    }

    private static int getEndIntervalElement(int m, int j) {
        return (m - 1) * j;
    }

    private static int getStartIntervalElement(int m, int j) {
        return (m - 1) * (j - 1);
    }
}