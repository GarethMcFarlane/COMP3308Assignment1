
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