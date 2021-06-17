package ShanHongPart;
import java.util.LinkedList;


/**
 * Author Chin Shan Hong
 * The Customer class is the child of Vertex class
 */
public class Customer extends Vertex {

    //Customer have demand attribute
    private int demand;


    /**
     * A constructor for Customer class object
     * @param x_coordinate
     * @param y_coordinate
     * @param demand
     * @param id
     */
    public Customer(int x_coordinate, int y_coordinate, int demand, int id) {
        super(x_coordinate, y_coordinate, id);
        this.demand = demand;
    }


    /**
     * return a Customer object information
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" + "id=" + getId() + '}';
    }

    /**
     * Get the demand of a Customer objext
     * @return demand
     */
    public int getDemand() {
        return demand;
    }

}
