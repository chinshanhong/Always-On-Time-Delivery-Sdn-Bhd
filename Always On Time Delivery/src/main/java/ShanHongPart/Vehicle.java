package ShanHongPart;

import java.util.ArrayList;

/**
 * Author Chin Shan Hong
 * Vehicle class to create a Vehicle object
 */
public class Vehicle {

    //Variable to store a Vehicle capacity
    private int capacity;

    //Variable to store the route cost travelled by the Vehicle
    private double cost;

    //Variable to store a Vehicle Id
    private int vehicleID;

    //Variable to store a Vehicle remaining capacity
    private int remainingCapacity;

    //An ArrayList variable to store the route travelled by the Vehicle
    private ArrayList<Integer> routing;

    // A variable that store the route travelled in String
    private String route;

    /**
     * A constructor for Vehicle object
     * @param capacity
     * @param vehicleID
     */
    public Vehicle(int capacity, int vehicleID) {
        routing = new ArrayList<>();
        this.capacity = capacity;
        remainingCapacity = capacity;
        this.vehicleID = vehicleID;
        //Initial route cost is 0
        cost = 0;
    }

    /**
     * Get the capacity of the Vehicle object
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Get the cost of the travelled route
     * @return
     */
    public double getCost() {
        return cost;
    }

    /**
     * Set the cost of the route travelled by the object
     * @param calculatedCost
     */
    public void setCost(double calculatedCost) {
        this.cost = calculatedCost;
    }

    /**
     * Set the capacity of the Vehicle object
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get the route travelled by the Vehicle
     * @return routing in ArrayList
     */
    public ArrayList<Integer> getRoute() {
        return routing;
    }

    /**
     *  Get the Vehicle Id
     * @return vehicleID
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     *  Set the Vehicle Id
     * @param vehicleID
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * Get the remaining capacity of a Vehicle
     * @return remainingCapacity
     */
    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    /**
     * Set the remainingCapacity of the Vehicle object
     * @param remainingCapacity
     */
    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    /**
     * Calculate the route cost travelled
     * @param vertices
     */
    public void calculateRouteCost(Vertex[] vertices) {
        for (int i = 0; i < getRoute().size() - 1; i++) {
            setCost(getCost() + BFS.computeDistance(vertices
                    [(getRoute().get(i))], vertices[(getRoute().get(i + 1))]));
        }
    }

    /**
     * Calculate the remaining capacity of a Vehicle
     * @param customer
     */
    public void calculateRemainingCapacity(Customer customer) {
        setRemainingCapacity(getRemainingCapacity() - customer.getDemand());
    }

    public void setStringRoute(){
        route = "";
            for (int j = 0; j < getRoute().size(); j++) {
                if(j == getRoute().size() - 1){
                    route += getRoute().get(j);
                }
                else{
                    route += getRoute().get(j) + " -> ";
                }
            }
    }

    public String getStringRoute(){
        return route;
    }
}
