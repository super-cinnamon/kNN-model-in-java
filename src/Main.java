import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Instance> trainSet = new ArrayList<>();
        /*
        this was done for testing purposes

        trainSet.add(new Instance(2,2,2,"a"));
        trainSet.add(new Instance(4,4,4,"b"));
        trainSet.add(new Instance(7,7,7,"a"));
        trainSet.add(new Instance(6,6,6,"b"));
        trainSet.add(new Instance(3,3,3,"a"));
        trainSet.add(new Instance(8,8,8,"c"));
        KNN myKNN = new KNN(trainSet);
        System.out.println(myKNN.classify(new Instance(5,5,5), 5));

         */


        Scanner sc = new Scanner(System.in);
        System.out.println("how many values in your training set?");
        int n = sc.nextInt();
        for(int i = 0; i<n; i++){
            System.out.println("test set "+ (i+1));
            System.out.println("type value 1");
            int a = sc.nextInt();
            System.out.println("type value 2");
            int b = sc.nextInt();
            System.out.println("type value 3");
            int c = sc.nextInt();
            System.out.println("type label");
            String label = sc.next();
            trainSet.add(new Instance(a,b,c,label ));
        }
        KNN myKNN = new KNN(trainSet);
        System.out.println("type in the values of your instance");
        System.out.println("type value 1");
        int a = sc.nextInt();
        System.out.println("type value 2");
        int b = sc.nextInt();
        System.out.println("type value 3");
        int c = sc.nextInt();
        System.out.println("type k value");
        int k = sc.nextInt();
        Instance testInstance = new Instance(a,b,c);
        System.out.println("the test instance has been classified : "+myKNN.classify(testInstance,k));


    }
}
