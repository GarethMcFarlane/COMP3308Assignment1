/**
 * Created by garethmcfarlane on 21/04/15.
 */
public class naiveBayes {
    DataInstance trainingData;
    DataInstance testingData;

    public naiveBayes(String training, String testing) {
        this.trainingData = new DataInstance(training);
        this.testingData = new DataInstance(testing);
    }

    public void classify() {


    }
}
