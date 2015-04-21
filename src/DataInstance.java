import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Gareth McFarlane on 19/04/2015.
 */


//This class will be used for storing both training and testing data.  As the testing data does not
//contain classes, we need to check this in the following method.  Similarly, when access attributes externally,
// training data classes will use the first n-1 indexes to access items while testing data classes will use
// all indexes to access attributes.
public class DataInstance implements Iterable<DataInstance.DataRow>, Iterator<DataInstance.DataRow> {
List<DataRow> instanceList;
private int count = 0;

    @Override
    public Iterator<DataRow> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (count < instanceList.size()) {
            return true;
        }
        return false;
    }

    @Override
    public DataRow next() {
        if (count == instanceList.size()) {
            throw new NoSuchElementException();
        }
        count++;
        return instanceList.get(count -1);
    }


    //Class for each row in the CSV file.
    public class DataRow {
        private String input;
        private double attributes[];
        public String name; //Row's class.

        //Constructor for a DataRow.  Takes a single input string and converts it into attributes.
        public DataRow(String line) {
            input = line;
            //Splits string into comma-delimited array.
            String[] tokens = line.split(",",-1);
            int dataSize = tokens.length;
            attributes = new double[dataSize];

            //Gets all attributes and finds the class name.
            for (int i = 0; i < dataSize; ++i) {
                //If it detects a class, assign it.
                if (tokens[i].equals("yes") || tokens[i].equals("no")) {
                    name = tokens[i];
                } else {
                    //Else add it to the attributes.
                    attributes[i] = Double.parseDouble(tokens[i]);
                }
            }
        }
    }

    //Constructor for the entire data structure.
    public DataInstance(String filename) {
        instanceList = new ArrayList<DataRow>();

        //Reads file line by line.
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                instanceList.add(new DataRow(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    //Getters

    public String getClass(int index) {
        return instanceList.get(index).name;
    }

    public void getData(int index) {
        System.out.println(instanceList.get(index).input);
    }
}


