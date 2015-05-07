/**
 * Created by garethmcfarlane on 21/04/15.
 *
 * Perfected by yozza on the 29th of April
 */

public class naiveBayes {
    DataInstance trainingData;
    DataInstance testingData;

    double [] probsy,probsn,colmeany,colmeann,coldevy,coldevn;
    double nprobtotal,yprobtotal;



    public naiveBayes(String training, String testing) {
        this.trainingData = new DataInstance(training);
        this.testingData = new DataInstance(testing);
        int columns = trainingData.getColumns();

        colmeany = new double[columns];
        colmeann = new double[columns];

        coldevy = new double[columns];
        coldevn = new double[columns];

        probsy = new double[columns];
        probsn = new double[columns];

        yprobtotal = nprobtotal = 0;

    }

    public void classify() {

        //Get the number of yes and no instances.
        for (DataRow dr : trainingData) {
            if (dr.getClassName().equals("yes")) {
                yprobtotal++;
            } else {
                nprobtotal++;
            }
        }



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
        //Calculate probability
        for (int i = 0; i < yProbs.length; i ++) {
            if (i == 0) yes = yProbs[i];
            else {
                yes *= yProbs[i];
            }
        }
        //Calculate yes probability
        yes *= yprobtotal/ (yprobtotal + nprobtotal);

        for (int j = 0; j < nProbs.length; j++) {
            if (j == 0) no = nProbs[j];
            else {
                no *= nProbs[j];
            }
        }
        //Calculate no probability
        no *= nprobtotal/ (yprobtotal + nprobtotal);


        if (yes >= no) {
            return "yes";
        } else return "no";
    }

    //Calculate PDF for each value.
    double pdf (double val, int column, String cls) {
        double part1 = 0, part2 = 0;
        if (cls.equals("yes")) {
            double divisor = (coldevy[column] * Math.sqrt(2 * Math.PI));
            double exponent = -((square(val - colmeany[column]))/(2 * square(coldevy[column])));
            double result = Math.exp(exponent) / divisor;
            return result;
        } else {
            double divisor = (coldevn[column] * Math.sqrt(2 * Math.PI));
            double exponent = -((square(val - colmeann[column]))/(2 * square(coldevn[column])));
            double result = Math.exp(exponent) / divisor;
            return result;
        }



    }
    //Calculate mean
    double meanCalc (int column, String cls)  {

        double sum = 0;
        int n = 0;

        for (DataRow i : trainingData) {
            if (cls.equals(i.getClassName())) {
                sum += i.getAttributes(column);
                n++;
            }
        }
        return (sum / n);
    }

    //Calculate standard deviation.
    double devCalc (int column, double mean, String cls) {
        double altSum = 0;
        int n = 0;
            for (DataRow i : trainingData) {
                if (cls.equals(i.getClassName())) {
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