public class ArctanTaylorSeries {
    public static double arctan(double x) {
        if (x < -1 || x > 1) {
            throw new IllegalArgumentException("x must be in the range [-1, 1]");
        }

        double t = x;
        double sum = x;
        int i = 1;
        double term;

        while (Math.abs(t) > 1e-9) {
            term = Math.pow(-1, i) * Math.pow(x, 2 * i + 1) / (2 * i + 1);
            sum += term;
            t = term;
            i++;
        }
        return sum;
    }
}
