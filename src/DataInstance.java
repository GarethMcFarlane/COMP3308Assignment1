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
public class DataInstance implements Iterable<DataRow>, Iterator<DataRow> {
List<DataRow> instanceList;
private int count = 0;
    private int rowNum = 0;

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



    //Constructor for the entire data structure.
    public DataInstance(String filename) {
        instanceList = new ArrayList<DataRow>();

        //Reads file line by line.
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                instanceList.add(new DataRow(line));
                rowNum++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    //Getters

    public DataRow getRow(int index) {
        return instanceList.get(index);
    }

    public int getRowNum() { return rowNum;};
}


