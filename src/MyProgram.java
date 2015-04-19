public class MyProgram {
    public static void main(String args[]) {
        if (args.length == 3) {
            String trainingData = args[0];
            String testingData = args[1];
            int algorithm = checkAlgorithm(args[2]);
            if (algorithm > -1) {
                //Start classifying.
            }
        }
    }

    // Returns 0 for NB algorithm.
    // Returns the K value for KNN algorithm.
    // Or returns -1 for anything else not recognised.
    private static int checkAlgorithm(String algorithm) {
        if (algorithm.equals("NB")) {
            return 0;
        }
        if (algorithm.charAt(algorithm.length() - 1) == 'N'
                && algorithm.charAt(algorithm.length() - 2) == 'N'
                && algorithm.length() > 2) {
            String number = algorithm.substring(0, algorithm.length() - 2);
            return Integer.parseInt(number);

        }
        return -1;
    }
}