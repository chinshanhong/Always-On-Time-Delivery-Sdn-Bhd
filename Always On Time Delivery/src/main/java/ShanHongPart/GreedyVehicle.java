package ShanHongPart;
import java.util.ArrayList;

class GreedyVehicle {
    public int VehId;
    public ArrayList<Node> Route = new ArrayList<Node>();
    public int capacity;
    public int load;
    public int CurLoc;
    public double cost;
    public GreedyVehicle(int id, int cap) {
        this.VehId = id;
        this.capacity = cap;
        this.load = 0;
        this.CurLoc = 0; // In depot Initially
        this.cost = 0.0;
    }

    public void AddNode(Node Customer, double dist)// Add Customer to vehicle Route
    {
        Route.add(Customer);
        this.load += Customer.demand;
        this.CurLoc = Customer.NodeId;
        this.cost += dist;
    }

    public boolean CheckIfFits(int dem) // Check Capacity Violation
    {
        return ((load + dem <= capacity));

    }

}