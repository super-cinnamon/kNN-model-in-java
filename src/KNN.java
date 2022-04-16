import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class KNN {
    //for security make everything private, it will have getters and setters later
    private ArrayList<Instance> trainSet;
    private ArrayList<Neighbor> sortedNeighbors = new ArrayList<>();

    //simple constructor since all we need is the training set
    public KNN(ArrayList<Instance> trainSet){
        this.trainSet = trainSet;
    }

    //this method calculates the euclidean distance between 2 instances
    public Neighbor EuclideanDistance(Instance test, Instance train){
        //init the distance to 0
        float distance=0;
        //loop through each element of the instance
        for(int i = 0; i < test.getVals().size(); i++){
            //the distance is a sum of (x-y)²
            distance+= pow(test.getVals().get(i)-train.getVals().get(i), 2);
        }
        //the final distance is the squared root of the sum of all the distances calculated in the previous loop
        distance = (float) sqrt(distance);
        //we return a neighbor object, which is an object that has 2 attributes: the distance, and its label
        //this object structure will come in very handy later on for classification
        return new Neighbor(distance, train.getLabel());
    }

    //this is simply a method that loops through all the training set and calculates the distances
    public void calculateDistances(Instance myInstance){
        for (Instance current: trainSet) {
            //we save the neighbor objects (all distances and their labels) into an array
            sortedNeighbors.add(EuclideanDistance(myInstance,current));
        }
        //we sort that array to have the shortest distances first
        Collections.sort(sortedNeighbors);
    }

    //this method will take the sorted array of neighbors, and take the nearest k neighbors
    public ArrayList<Neighbor> kNearest(int k){
        ArrayList<Neighbor> nearestNeighbors = new ArrayList<>();
        for(int i = 0; i<k; i++){
            nearestNeighbors.add(sortedNeighbors.get(i));
        }
        return nearestNeighbors;
    }

    //this method is the majority vote method, it will return the most recurrent label
    public String majorityVote(ArrayList<Neighbor> neighbors, int n){
        ArrayList<Integer> counters = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            int count = 1;
            for (int j = i + 1; j < n; j++)
            {
                if (neighbors.get(i).getLabel() == neighbors.get(j).getLabel()) {
                    count++;
                }
            }
            if(labels.contains(neighbors.get(i).getLabel())){
                counters.set(labels.indexOf(neighbors.get(i).getLabel()),counters.get(labels.indexOf(neighbors.get(i).getLabel())));
            }else{
                counters.add(count);
                labels.add(neighbors.get(i).getLabel());
            }
            //if the number of votes is over half of the amount of total labels, then it wins majority by default
            if (count > n/2) {
                return neighbors.get(i).getLabel();
            }
        }
        //we get the majority
        if(!labels.isEmpty()){
            int max = Collections.max(counters);
            return labels.get(counters.indexOf(max));
        }
        return null;
    }
    //we classify the instance with the specified k value and return the result
    public Instance classify(Instance myInstance, int k){
        calculateDistances(myInstance);
        ArrayList<Neighbor> kNearestNeighbors = kNearest(k);
        Instance result = new Instance(myInstance.getVals(), majorityVote(kNearestNeighbors, k));
        return result;
    }

    //getters and setters for private attributes


    public ArrayList<Instance> getTrainSet() {
        return trainSet;
    }

    public void setTrainSet(ArrayList<Instance> trainSet) {
        this.trainSet = trainSet;
    }

    public ArrayList<Neighbor> getSortedNeighbors() {
        return sortedNeighbors;
    }

    public void setSortedNeighbors(ArrayList<Neighbor> sortedNeighbors) {
        this.sortedNeighbors = sortedNeighbors;
    }
}
 class Neighbor implements Comparable<Neighbor>{
    private float distance;
    private String label;

     public Neighbor(float distance, String label) {
         this.distance = distance;
         this.label = label;
     }

     public float getDistance() {
         return distance;
     }

     public void setDistance(float distance) {
         this.distance = distance;
     }

     public String getLabel() {
         return label;
     }

     public void setLabel(String label) {
         this.label = label;
     }

     @Override
     public int compareTo(Neighbor o) {
         if(this.distance>o.distance){
             return 1;
         }else if(this.distance==o.distance){
             return 0;
         }else{
             return -1;
         }
     }
 }