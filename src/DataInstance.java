import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;

/**
 * Created by Gareth McFarlane on 19/04/2015.
 */


//This class will be used for storing both training and testing data.  As the testing data does not
//contain classes, we need to check this in the following method.  Similarly, when access attributes externally,
// training data classes will use the first n-1 indexes to access items while testing data classes will use
// all indexes to access attributes.
public class DataInstance implements Iterable<DataRow>, Iterator<DataRow> {
private List<DataRow> instanceList;
private int count = 0;
private int size = 0;

    @Override
    public Iterator<DataRow> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (count < instanceList.size()) {
            return true;
        }
        count = 0;
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

    @Override
    public void remove() {

    }



    //Constructor for the entire data structure.
    public DataInstance(String filename) {
        instanceList = new ArrayList<DataRow>();

        //Reads file line by line.
        try (BufferedReader br = new BufferedReader(new FileReader("..//src//" + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                instanceList.add(new DataRow(line));
                size++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    //Getters
    public int getSize() { return size;};

    public int getColumns() { return instanceList.get(0).getRowLength();}

}


