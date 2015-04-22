
//Class for each row in the CSV file.
public class DataRow {
    private String input;
    private double attributes[];
    private String name; //Row's class.
    private int rowLength = 0;

    //Constructor for a DataRow.  Takes a single input string and converts it into attributes.
    public DataRow(String line) {
        input = line;
        //Splits string into comma-delimited array.
        String[] tokens = line.split(",",-1);
        rowLength = tokens.length;
        attributes = new double[rowLength];

        //Gets all attributes and finds the class name.
        for (int i = 0; i < rowLength; ++i) {
            //If it detects a class, assign it.
            if (tokens[i].equals("yes") || tokens[i].equals("no")) {
                name = tokens[i];
                rowLength = rowLength-1;
            } else {
                //Else add it to the attributes.
                attributes[i] = Double.parseDouble(tokens[i]);
            }
        }
    }

    public double getAttributes(int index) {
        return attributes[index];
    }

    public double[] getAttributes() {return attributes;}

    public String getClassName() {
        return name;
    }


    public int getRowLength() {
        return rowLength;
    }
}