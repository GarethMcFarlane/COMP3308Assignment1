import java.util.ArrayList;
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
        int counter = 0;
            for (DataRow trainingRow : trainingData) {

            }
    }
}


double euclidian(DataRow a, DataRow b) {
    double d = 0;
    for (int i = 0; i < 8; ++i) {
        d += Math.pow(a.getAttributes(i) - b.getAttributes(i),2);
    }
    return Math.sqrt(d);
}


void getMajority() {

}





}







