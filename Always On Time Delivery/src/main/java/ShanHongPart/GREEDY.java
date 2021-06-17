package ShanHongPart;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class GREEDY {
    private static Node[] Nodes;
    double[][] distanceMatrix;
    int NumberOfCustomers;
    int VechCap;
    private static ArrayList<ArrayList<GreedyVehicle>> vehicles = new ArrayList<>();
    Double[] Cost;
    String labelling = "Greedy Simulation";
    private static double TourCost  = 0;
    //A variable for calling the GUI class main method
    private static int count = 0;
    private int timer = 0;
    public GREEDY(String filePath) {
        File file = new File(filePath); // Give a path of file.
        ArrayList<ArrayList<Integer>> coor = new ArrayList<ArrayList<Integer>>(); // Multidimensional Arraylist
        int n = 0,c = 0,i;
        try{
            Scanner sc = new Scanner(file); // scanner to scan the file.
            n = sc.nextInt(); // variable n
            c = sc.nextInt(); // variable c
            i = 0;

            while (sc.hasNextInt()) { // loop through file to read all values line by line.
                coor.add(new ArrayList<Integer>());
                coor.get(i).add(0, sc.nextInt());
                coor.get(i).add(1, sc.nextInt());
                coor.get(i).add(2, sc.nextInt());
                i++;
            }
            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }

//        System.out.println("N = " + n + " C = " + c);
//        System.out.println("Depot[" + 0 + "] = (" + coor.get(0).get(0) + "," + coor.get(0).get(1) + ")");
//        for (int id = 1; id < coor.size(); id++) { // print the coordinates.
//
//            System.out.print("Customer[" + id + "] = (" + coor.get(id).get(0) + "," + coor.get(id).get(1) + ")");
//            System.out.print(", C" + id + " = " + coor.get(id).get(2));
//            System.out.println();
//
//        }

        int Depot_X = coor.get(0).get(0);
        int Depot_Y = coor.get(0).get(1);
        NumberOfCustomers = n - 1;
        VechCap = c;


        Nodes = new Node[n];
        Node depot = new Node(Depot_X, Depot_Y);
        Nodes[0] = depot;

        for (int id = 1; id <=NumberOfCustomers; id++) {
            Nodes[id] = new Node(id, coor.get(id).get(0), coor.get(id).get(1), coor.get(id).get(2));
        }

        distanceMatrix = new double[NumberOfCustomers + 1][NumberOfCustomers + 1];
        double Delta_x, Delta_y;
        for (int p = 0; p<= NumberOfCustomers; p++) {
            for (int q = p + 1; q <= NumberOfCustomers; q++) //The table is summetric to the first diagonal
            {

                Delta_x = (Nodes[p].Node_X - Nodes[q].Node_X);
                Delta_y = (Nodes[p].Node_Y - Nodes[q].Node_Y);

                double distance = Math.sqrt((Delta_x * Delta_x) + (Delta_y * Delta_y));

                distanceMatrix[p][q] = distance;
                distanceMatrix[q][p] = distance;
            }
        }
        for(i = 0; i < Nodes.length; i++){
            Nodes[i].IsRouted = false;
        }
    }
    public boolean UnassignedCustomerExists(Node[] Nodes)
    {
        for (int i = 1; i < Nodes.length; i++)
        {
            if (!Nodes[i].IsRouted)
                return true;
        }
        return false;
    }
    /**
     * Basic Simulation
     */
    public void algorithmRun(){
        greedy();
        TourCost = Cost[0];
        display();
    }
    /**
     * Greedy algorithm
     */
    public void greedy() {
        long startTime = System.nanoTime();
        for (int i = 0; i < NumberOfCustomers; i++) {
            if ((System.nanoTime() - startTime) / 1000000000 == timer) {
                System.out.print("\r");
                System.out.print("Time elapsed: |");
                for (int j = 0; j < timer; j++) {
                    System.out.print("=");
                }
                System.out.print("| " + timer + "s");
                System.out.println();
                timer++;
            }
            if ((System.nanoTime() - startTime) / 1000000000 > 60) {
                System.out.println(" (Searching is forced to stop)");
                break;
            }
        }

        double CandCost;
        int VehIndex = 0;
        vehicles.clear();
        vehicles.add(new ArrayList<>());
        Cost = new Double[1];
        Cost[0] = 0.0;
        while (UnassignedCustomerExists(Nodes)) {
            vehicles.get(0).add(new GreedyVehicle(VehIndex,this.VechCap));
            vehicles.get(0).get(VehIndex).AddNode(Nodes[0],0);
            // select the first customer in this route
            boolean first = true;
            Node Candidate = null;
            double minCost = (double) Double.MAX_VALUE;
            int CustIndex = 0;
            while(Candidate != Nodes[0]){
                Candidate = null;
                minCost = (double) Double.MAX_VALUE;
                for (int i = 1; i <= NumberOfCustomers; i++) {
                    if (!Nodes[i].IsRouted) {
                        if (vehicles.get(0).get(VehIndex).CheckIfFits(Nodes[i].demand)) {
                            CandCost = distanceMatrix[vehicles.get(0).get(VehIndex).CurLoc][i];
                            if (minCost > CandCost) {
                                minCost = CandCost;
                                CustIndex = i;
                                Candidate = Nodes[i];
                            }
                        }
                    }
                }
                // go back to the depot if Candidate is null
                if(!first && (Candidate == null ) ){
                    minCost = distanceMatrix[vehicles.get(0).get(VehIndex).CurLoc][0];
                    CustIndex = 0;
                    Candidate = Nodes[0];
                }
                if(first){
                    first = false;
                }
                vehicles.get(0).get(VehIndex).AddNode(Candidate, minCost);
                if(Candidate != Nodes[0]){
                    Nodes[CustIndex].IsRouted = true;
                }
                Cost[0] += minCost;
            }
            VehIndex++;
        }
    }


    /**
     * Display the tour detail
     */
    public void display() {
        System.out.println(labelling);
        System.out.println("Tour");
        System.out.println("Tour Cost: " + Cost[0]);
        for (int j=0 ; j < vehicles.get(0).size(); j++)
        {
            if (!vehicles.get(0).get(j).Route.isEmpty())
            {
                System.out.println("vehicle " + (j + 1));
                int RoutSize = vehicles.get(0).get(j).Route.size();
                for (int k = 0; k < RoutSize ; k++) {
                    if (k == RoutSize-1)
                    { System.out.print(vehicles.get(0).get(j).Route.get(k).NodeId );  }
                    else
                    { System.out.print(vehicles.get(0).get(j).Route.get(k).NodeId+ " -> "); }
                }
                System.out.println();
                System.out.println("Capacity: " + vehicles.get(0).get(j).load);
                System.out.println("Cost: " + vehicles.get(0).get(j).cost);
            }
        }
    }

    public static Node[] getVertices() {
        return Nodes;
    }
    public static ArrayList<GreedyVehicle> getVehicles(){
        return vehicles.get(0);
    }
    public static double getTourCost(){
        return TourCost;
    }

}




