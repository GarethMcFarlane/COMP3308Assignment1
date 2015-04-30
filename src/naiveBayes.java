/**
 * Created by garethmcfarlane on 21/04/15.
 *
 * Perfected by yozza on the 29th of April
 */

public class naiveBayes {
    DataInstance trainingData;
    DataInstance testingData;

    double [] probsy;
    double [] probsn;

    double [] colmeany;
    double [] colmeann;

    double [] coldevy;
    double [] coldevn;


    public naiveBayes(String training, String testing) {
        this.trainingData = new DataInstance(training);
        this.testingData = new DataInstance(testing);

        colmeany = new double[trainingData.getColumns()];
        colmeann = new double[trainingData.getColumns()];

        coldevy = new double[trainingData.getColumns()];
        coldevn = new double[trainingData.getColumns()];

        probsy = new double[trainingData.getColumns()];
        probsn = new double[trainingData.getColumns()];

    }

    public void classify() {

        // First we need to build a model of the training data

        // Get mean and averages
        for (int i = 0; i < trainingData.getColumns(); i++) {
            colmeany[i] = meanCalc(i, "yes");
            colmeann[i]  = meanCalc(i, "no");

            coldevy[i] = devCalc(i, colmeany[i], "yes");
            coldevn[i] = devCalc(i, colmeann[i], "no");
        }

        // Now use the model to classify test data
        for (DataRow row : testingData) {

            // calculate yes
            for (int i = 0; i < row.getRowLength(); i++) {
                probsy [i] = pdf(row.getAttributes(i), i, "yes");
            }
            // calculate no
            for (int i = 0; i < row.getRowLength(); i++) {
                probsn[i] = pdf(row.getAttributes(i), i, "no");
            }

            // Now that we have probabilities, we can use the Bayes function
            System.out.println(bayes(probsy, probsn));
        }

    }

    String bayes(double[] yProbs, double[] nProbs) {
        double yes = 0, no = 0;
        for (int i = 0; i < yProbs.length; i ++) {
            if (i == 0) yes = yProbs[i];
            else {
                yes *= yProbs[i];
            }
        }

        for (int j = 0; j < nProbs.length; j++) {
            if (j == 0) no = nProbs[j];
            else {
                no *= nProbs[j];
            }
        }

        if (yes >= no) {
            return "yes";
        } else return "no";
    }

    double pdf (double val, int column, String cls) {
        double part1 = 0, part2 = 0;
        if (cls.equals("yes")) {
            part1 = (1 / (coldevy[column] * Math.sqrt(2 * Math.PI)));
            part2 = Math.pow(Math.exp(1.0), (-1 * ((square(val - colmeany[column]))/(2 * square(coldevy[column])))));

            return part1 * part2;
        } else {
            part1 = (1 / (coldevn[column] * Math.sqrt(2 * Math.PI)));
            part2 = Math.pow(Math.exp(1.0), (-1 * ((square(val - colmeann[column]))/(2 * square(coldevn[column])))));

            return part1 * part2;
        }
    }

    double meanCalc (int column, String cls)  {

        double sum = 0;
        int n = 0;

        for (DataRow i : trainingData) {
            if (cls.equals("yes") && i.getClassName().equals("yes")) {
                sum += i.getAttributes(column);
                n++; // TODO: Check if N gives the correct result!
            } else if (cls.equals("no") && i.getClassName().equals("no")) {
                sum += i.getAttributes(column);
                n++; // TODO: Check if N gives the correct result!
            }
        }
        return (sum / n);
    }

    double devCalc (int column, double mean, String cls) {
        double altSum = 0;
        int n = 0;


            for (DataRow i : trainingData) {

                if (cls.equals("yes") && i.getClassName().equals("yes")) {
                    altSum += square((i.getAttributes(column) - mean));
                    n++;
                } else if (cls.equals("no") && i.getClassName().equals("no")) {
                    altSum += square((i.getAttributes(column) - mean));
                    n++;
                }
            }

        return Math.sqrt(altSum / (n - 1));
    }

    double square (double i) {
        return i * i;
    }

}