package ShanHongPart;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Scanner;


public class BFS {

    //Varaible for storing the currentLoad for a vehicle
    private int currentLoad;

    //Variable for storing the vertices number
    private int verticesNum;

    //An array of vertices
    private static Vertex[] vertices;

    //A queue for the BFS operation
    private Queue<Integer> queue = new LinkedList<>();

    //A Depot variable
    private Depot depot;

    //A variable that used to create an Id for a Vehicle object
    private int id = 0;

    //A variable for storing the capacity of a Vehicle object
    private int capacity;

    //An ArrayList for storing the route of a vehicle temporarily
    private ArrayList<Integer> route;

    //A variable for counting the time 60s
    private int timer = 0;

    //Variable for storing the shortest path
    private double shortestPath = Double.POSITIVE_INFINITY;

    //A variable for storing array of depots, no of depots = no of customers
    private Depot[] depots;

    //A variable for storing the depot with lowest cost
    private static Depot lowestCostDepot;

    //A variable for storing the file path;
    private String filePath;


    public BFS(String filePath) {
        this.filePath = filePath;
                    //Declare a variable 'vertexInfo' to store the vertex information read from the text file
                    ArrayList<String> vertexInfo = new ArrayList<>();
                    try{
                        Scanner scan = new Scanner(new FileInputStream(filePath));
                        int index = 0;
                        while(scan.hasNextLine()){
                            if(index == 0) {
                                String[] temp = scan.nextLine().split(" ");
                    this.verticesNum = Integer.parseInt(temp[0]);
                    this.capacity = Integer.parseInt(temp[1]);
                }

                //If index != 0, store vertex information into the vertexInfo ArrayList
                else{
                    vertexInfo.add(scan.nextLine());
                }
                index++;

            }
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }



        //Initialise the vertices array
        this.vertices = new Vertex[verticesNum];

        //Create vertex objects with information read from the vertexInfo ArrayList
        for(int i = 0; i < vertexInfo.size(); i++){
            if(i == 0){
                String[] temp = vertexInfo.get(i).split(" ");
                vertices[i] = new Depot(Integer.parseInt(temp[0]),
                        Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), i);
            }
            else{
                String[] temp = vertexInfo.get(i).split(" ");
                vertices[i] = new Customer(Integer.parseInt(temp[0]),
                        Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), i);
            }
        }

        //Set relationship for each vertices with other vertices
        setRelationShip(vertices);

        //Calculate the distance of the vertex with each of its neighbors
        setNeighborDistance(vertices);

        this.depot = (Depot) vertices[0];
        this.depots = new Depot[verticesNum - 1];
        route = new ArrayList<>();
        currentLoad = 0;

        for (int i = 0; i < depots.length; i++) {
            depots[i] = new Depot(vertices[0].getX_coordinate(), vertices[0].getX_coordinate(),
                    ((Depot) vertices[0]).getCapacity(), vertices[0].getId());
        }

    }

    /**
     * Recursively search the customer while the capacity are still enough
     *
     * @param s
     * @param depot
     */
    public void BFS(int s, Depot depot) {

        //Set the current customer as visited
        vertices[s].setVisited(true);

        //Add the customer as part of the route
        route.add(s);

        //Get the neighbors of current customer
        LinkedList<Vertex> customers = vertices[s].getNeighbors();

        //Find the next customer with shortest distance from current customer and demand lower than current capacity
        int temp = 0;
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = (Customer) customers.get(i);
            if (!customer.isVisited() && (currentLoad + customer.getDemand()) <= capacity) {
                if (shortestPath > vertices[s].getNeighborsDistance().get(i)) {
                    shortestPath = vertices[s].getNeighborsDistance().get(i);
                    temp = customer.getId();
                }
            }
        }

        //If temp == 0, thats mean the current route has ended and the vehicle need to return to the depot
        if (temp == 0) {
            //Adding 0 means returning to depot
            route.add(0);

            //Create a Vehicle object to store the route
            Vehicle vehicle = new Vehicle(capacity, ++id);
            for (int i = 0; i < route.size(); i++) {
                vehicle.getRoute().add(route.get(i));
            }

            //Set the vehicle remaining capacity according to the current load
            vehicle.setRemainingCapacity(vehicle.getRemainingCapacity() - currentLoad);
//            vehicle.setCapacity(vehicle.getCapacity() - vehicle.getRemainingCapacity());

            //Calculate the route cost
            vehicle.calculateRouteCost(vertices);

            //Convert the route into a string
            vehicle.setStringRoute();

            //Depot store the vehicle
            depot.getVehicles().add(vehicle);
            return;

            //If temp != 0, thats mean we still have enough capacity to travel to the next customer
            //Call the BFS method again
        } else {
            currentLoad += ((Customer) vertices[temp]).getDemand();
            BFS(temp, depot);
        }
    }


    /**
     * Set all the vertices as not visited
     *
     * @param vertices
     */
    public void setNotVisited(Vertex[] vertices) {
        for (int i = 1; i < vertices.length; i++) {
            vertices[i].setVisited(false);
        }
    }

    /***
     * A method for starting the BFS search
     * @return Depot
     */
    public void BFSearch() {

        //Initialise startime variable to calculate elapsed time
        long startTime = System.nanoTime();

        //A for loop to start the BFS search, if we have 5 customers,
        // we will have 5 search. We will return the best result
        for (int i = 0; i < depots.length; i++) {

            id = 0;

            //Set all vertices as unvisited at the beginning of each search
            setNotVisited(vertices);
            if ((System.nanoTime() - startTime) / 1000000000 == timer) {
                System.out.print("\r");
                System.out.print("Time elapsed: |");
                for (int j = 0; j < timer; j++) {
                    System.out.print("=");
                }
                System.out.print("| " + timer + "s");
                timer++;
            }
            if ((System.nanoTime() - startTime) / 1000000000 > 60) {
                System.out.println(" (Searching is forced to stop)");
                break;
            }

            //Start the searching process
            startSearch(i + 1, depots[i]);

        }

        //Variable to store the depot with lowest tour cost
        Depot depotWithLowestTourCost = findDepotWithLowestTourCost(depots);

        //Display to tour details
        display(depotWithLowestTourCost);

        lowestCostDepot = depotWithLowestTourCost;

    }

    /**
     * A method for starting the BFS searching process
     *
     * @param startingCustomerID
     * @param depot
     */
    public void startSearch(int startingCustomerID, Depot depot) {

        //Add the startingCustomerID as the first element in the queue. We search the startingCustomer first
        queue.add(startingCustomerID);

        //Add other customer ID into the queue
        for (int i = 1; i < vertices.length; i++) {
            if (!queue.contains(vertices[i].getId())) {
                queue.add(vertices[i].getId());
            }
        }

        while (!queue.isEmpty()) {

            if (vertices[queue.peek()].isVisited()) {
                queue.remove();

            } else {
                //Clear the route travelled by previous vehicle
                route.clear();
                //Add the depot location which is 0 for a new journey
                route.add(0);
                //Reset the current load to 0
                currentLoad = 0;
                //Set the shortestPath to infinity for finding the shortest path later
                shortestPath = Double.POSITIVE_INFINITY;

                //Get the next customer to traverse
                int s = queue.remove();

                //Add the demand of the customer to the current load
                currentLoad += ((Customer) vertices[s]).getDemand();

                //Reach the customer
                BFS(s, depot);
            }

        }
    }


    /**
     * Display the tour detail
     *
     * @param depot
     */
    public void display(Depot depot) {
        System.out.println();
        System.out.println("Basic Simulation");
        System.out.println("Tour");
//            depot.calculateTourCost();

        //Print the tour cost, vehicle ID, route, capacity, and route cost
        System.out.println("Tour Cost: " + depot.getTourCost());
        for (int i = 0; i < depot.getVehicles().size(); i++) {
            System.out.println("Vehicle " + depot.getVehicles().get(i).getVehicleID());
            for (int j = 0; j < depot.getVehicles().get(i).getRoute().size(); j++) {
                if (j == depot.getVehicles().get(i).getRoute().size() - 1) {
                    System.out.print(depot.getVehicles().get(i).getRoute().get(j));
                } else {
                    System.out.print(depot.getVehicles().get(i).getRoute().get(j) + "->");
                }
            }
            System.out.println("");
            System.out.println("Capacity: " + (depot.getVehicles().get(i).getCapacity() -
                    depot.getVehicles().get(i).getRemainingCapacity()));
            System.out.println("Cost: " + depot.getVehicles().get(i).getCost());
        }
    }

    /**
     * Find the depot with the lowest tour cost and return it
     *
     * @param depots
     * @return
     */
    public Depot findDepotWithLowestTourCost(Depot[] depots) {
        double minCost = Double.POSITIVE_INFINITY;
        int minCostDepotIndex = 0;

        for (int i = 0; i < depots.length; i++) {
            depots[i].calculateTourCost();
//                System.out.println();
//                System.out.println(depots[i].getTourCost());
            if (minCost > depots[i].getTourCost()) {
                minCost = depots[i].getTourCost();
                minCostDepotIndex = i;
            }
        }
        return depots[minCostDepotIndex];
    }

    /**
     * A method used to compute the distance between two vertices
     *
     * @param v1
     * @param v2
     * @return distance between the two vertices
     */
    public static Double computeDistance(Vertex v1, Vertex v2) {
        double delta_X, delta_Y;

        delta_X = v1.getX_coordinate() - v2.getX_coordinate();
        delta_Y = v1.getY_coordinate() - v2.getY_coordinate();

        double distance = Math.sqrt(((Math.pow(delta_X, 2) + Math.pow(delta_Y, 2))));
        return distance;

    }

    /**
     * A method used to set up the relationship between each vertices
     * @param vertices
     */
    public void setRelationShip(Vertex[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (i == 0 && j >= 1) {
                    vertices[i].getNeighbors().add(vertices[j]);
                } else if (i >= 1) {
                    if (j == 0 || j == i) {
                        continue;
                    } else {
                        vertices[i].getNeighbors().add(vertices[j]);
                    }
                }
            }
        }
    }

    /**
     * A method used to set the neighbors distance for each vertex
     * @param vertices
     */
    public void setNeighborDistance(Vertex[] vertices){
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices[i].getNeighbors().size(); j++) {

                vertices[i].getNeighborsDistance()
                        .add(computeDistance(vertices[i], vertices[i].getNeighbors().get(j)));
            }
        }
    }

    public static Vertex[] getVertices() {
        return vertices;
    }


    public static Depot getLowestCostDepot(){
        return lowestCostDepot;
    }
}




