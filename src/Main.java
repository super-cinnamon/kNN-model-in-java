import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static String ReadFile(String fileName) throws FileNotFoundException {
        BufferedReader IDF_READER = new BufferedReader(new FileReader(fileName));
        Boolean valid = true;
        String IDF_STR = "";

        while(valid){
            try{
                IDF_STR=IDF_STR.concat(IDF_READER.readLine()).concat("\n");
            }catch(Exception e){
                valid = false;
            }

        }
        return IDF_STR;
    }

    public static String[][] sepTable(String dataset){
        String[][] datasetTable = new String[150][5];
        String[] temp = dataset.split("\n");
        for(int i = 0; i < temp.length; i++) {
            String[] temp2 = temp[i].split(",");
            for (int j = 0; j < temp2.length; j++) {
                datasetTable[i][j] = temp2[j];
                //System.out.println(datasetTable[i][j]);
            }
        }
        return datasetTable;
    }

    public static Double[] StringtoDouble(String[] tableau){
        Double[] table=new Double[150];
        for(int i=0;i<tableau.length;i++){
            table[i]=Double.parseDouble(tableau[i]);
        }
        return table;
    }

    public static void trier(Double[] tableau){
        Double x;
        for(int i=0;i<tableau.length-1;i++){
            for(int j=i+1;j<tableau.length;j++){
                if(tableau[i]>tableau[j]){
                    x=tableau[i];
                    tableau[i]=tableau[j];
                    tableau[j]=x;
                }
            }
        }
    }
    public static Double moyenne(ArrayList<Double> list){
        Double somme=0.0;
        for (int i=0;i<list.size();i++){
            somme=somme+list.get(i);
        }
        return somme/list.size();
    }


    public static void main(String[] args) {
        ArrayList<Instance> trainSet = new ArrayList<>();
        try {
            String[][] datasetTable= sepTable(ReadFile("Dataset-Exos.txt"));
            String[] attribut1=new String[150];
            String[] attribut2=new String[150];
            String[] attribut3=new String[150];
            String[] attribut4=new String[150];
            String[] label=new String[150];

            for(int i=0;i< datasetTable.length;i++){
                for(int j=0;j< datasetTable[0].length;j++){
                    if(j==0){
                        attribut1[i]=datasetTable[i][0];
                    }
                    if(j==1){
                        attribut2[i]=datasetTable[i][1];
                    }
                    if(j==2){
                        attribut3[i]=datasetTable[i][2];
                    }
                    if(j==3){
                        attribut4[i]=datasetTable[i][3];
                    }
                    if(j==4){
                        label[i]=datasetTable[i][4];
                    }
                }
            }
            Double[] attributs1=StringtoDouble(attribut1);
            Double[] attributs2=StringtoDouble(attribut2);
            Double[] attributs3=StringtoDouble(attribut3);
            Double[] attributs4=StringtoDouble(attribut4);
            for(int i = 0; i< datasetTable.length; i++){
                trainSet.add(new Instance(attributs1[i],attributs2[i],attributs3[i],attributs4[i],label[i]));
            }
            KNN myKNN = new KNN(trainSet);

            Instance testInstance = new Instance(5.2,3.5,1.41,0.25);
            System.out.println("the test instance has been classified : "+myKNN.classify(testInstance,1));
            System.out.println("the test instance has been classified : "+myKNN.classify(testInstance,3));
            System.out.println("the test instance has been classified : "+myKNN.classify(testInstance,5));

        }
        catch(Exception e){
            System.out.println(e);
            }







        /*
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

        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("how many values in your training set?");
        int n = sc.nextInt();
        for(int i = 0; i<n; i++){
            System.out.println("test set "+ (i+1));
            System.out.println("type value 1");
            Double a = sc.nextDouble();
            System.out.println("type value 2");
            Double b = sc.nextDouble();
            System.out.println("type value 3");
            Double c = sc.nextDouble();

            System.out.println("type value 4");
            Double d = sc.nextDouble();
            System.out.println("type label");
            String label = sc.next();
            trainSet.add(new Instance(a,b,c,d,label ));
        }
        KNN myKNN = new KNN(trainSet);
        System.out.println("type in the values of your instance");
        System.out.println("type value 1");
        Double a = sc.nextDouble();
        System.out.println("type value 2");
        Double b = sc.nextDouble();
        System.out.println("type value 3");
        Double c = sc.nextDouble();
        System.out.println("type value 4");
        Double d = sc.nextDouble();
        System.out.println("type k value");
        int k = sc.nextInt();
        Instance testInstance = new Instance(a,b,c,d);
        System.out.println("the test instance has been classified : "+myKNN.classify(testInstance,k));*/


    }
}
