package ShanHongPart;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Author Chin Shan Hong
 * Depot is a child class of Vertex Class
 */
public class Depot extends Vertex {
    // A variable to store the capacity of the Depot
    private int capacity;

    // A variable to store the tourCost of the Depot
    private double tourCost;

    // A variable to stor the vehicles inside the Depot
    private ArrayList<Vehicle> vehicles;

    /**
     * A constructor for the depot
     * @param x_coordinate
     * @param y_coordinate
     * @param capacity
     * @param id
     */
    public Depot(int x_coordinate, int y_coordinate,int capacity, int id) {
        super(x_coordinate, y_coordinate, id);
        this.capacity = capacity;
        vehicles = new ArrayList<>();

        //Tour cost are initialy zero
        this.tourCost = 0;
    }

    /**
     * Get the capacity of the Depot object
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Get information of the depot
     * @return
     */
    @Override
    public String toString() {
        return "Depot{" + "id=" + getId() + "}";
    }

    /**
     * Get the tour cost
     * @return
     */
    public double getTourCost() {
        return tourCost;
    }

    /**
     * Set the tour cost
     * @param tourCost
     */
    public void setTourCost(double tourCost) {
        this.tourCost = tourCost;
    }

    /**
     * Get the vehicles stored inside the Depot object
     * @return vehicles in ArrayList
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Calculate the tour cost
     */
    public void calculateTourCost() {
        for (int i = 0; i < vehicles.size(); i++) {
            setTourCost(getTourCost() + vehicles.get(i).getCost());
        }
    }
}
