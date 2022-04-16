import java.util.ArrayList;

public class Instance {
    //this class represents an element, in other words the data X and the label Y if there is any
    private ArrayList<Integer> vals = new ArrayList<>();
    private String label;
    //constructor for test instance
    public Instance(int a, int b, int c) {
        vals.add(a);
        vals.add(b);
        vals.add(c);
        label = null;
    }
    //constructor for training instance
    public Instance(int a, int b, int c, String label) {
        vals.add(a);
        vals.add(b);
        vals.add(c);
        this.label = label;
    }
    //constructor for faster creation
    public Instance(ArrayList<Integer> vals, String label) {
        this.vals = vals;
        this.label = label;
    }
    //getters and setters because all attributes are private
    public ArrayList<Integer> getVals() {
        return vals;
    }

    public String getLabel() {
        return label;
    }

    public void setVals(ArrayList<Integer> vals) {
        this.vals = vals;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    //toString to have a proper print
    @Override
    public String toString() {
        return "Instance[" +
                "values=" + vals +
                ", label='" + label + '\'' +
                ']';
    }
}
