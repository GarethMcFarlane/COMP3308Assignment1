import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gareth McFarlane on 19/04/2015.
 */
public class DataInstance {
List<DataRow> instanceList;
String atrributeNames[];

//Class for each row in the CSV file.
    public class DataRow {
        double attributes[];
        String name; //Row's class.

        //Constructor for a DataRow.  Takes a single input string and converts it into attributes.
        public DataRow(String line) {
            //Splits string into comma-delimited array.
            String[] tokens = line.split(",",-1);
            int dataSize = tokens.length;
            attributes = new double[dataSize];

            //Gets all attributes then finds the class name.
            for (int i = 0; i < dataSize - 2; ++i) {
                attributes[i] = Double.parseDouble(tokens[i]);
            }
            name = tokens[dataSize-1];
        }
    }

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

}


