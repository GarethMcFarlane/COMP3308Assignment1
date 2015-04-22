import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Gareth McFarlane on 19/04/2015.
 */
public class kNearestNeighbour {
DataInstance trainingData;
DataInstance testingData;
int k;


    public kNearestNeighbour(String training, String testing, int k) {
        trainingData = new DataInstance(training);
        testingData = new DataInstance(testing);
        this.k = k;

    }

void classify() {

    for (DataRow testingRow : testingData) {
        sortedNStack sns = new sortedNStack();
            for (DataRow trainingRow : trainingData) {
                sns.push(euclidian(trainingRow,testingRow),trainingRow.getClassName());
            }
        sns.outputClass();
    }
}


double euclidian(DataRow a, DataRow b) {
    double d = 0;
    for (int i = 0; i < 8; ++i) {
        d += Math.pow(a.getAttributes(i) - b.getAttributes(i),2);
    }
    return Math.sqrt(d);
}




    public class sortedNStack {
        private ArrayList<Double> distances;
        private ArrayList<String> classNames;

        public sortedNStack() {
            distances = new ArrayList<Double>(Collections.nCopies(k, 0.0));
            classNames = new ArrayList<String>(Collections.nCopies(k,""));
        }

        public void push(double distance, String classname) {
            for (int i = 0; i < k; ++i) {
                if (distance > distances.get(k-1)) {
                    classNames.remove(k-1);
                    distances.add(i, distance);
                    classNames.add(i,classname);
                    return;
                }
            }
        }


        public void outputClass() {
            int yesCount = 0;
            int noCount = 0;
            for (String s : classNames) {
                if (s.equals("yes")) {
                    yesCount++;
                } else {
                    noCount++;
                }
            }
            if (yesCount > noCount) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

    }




}







