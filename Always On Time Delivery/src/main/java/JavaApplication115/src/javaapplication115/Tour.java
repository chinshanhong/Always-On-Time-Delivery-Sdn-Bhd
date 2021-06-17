package JavaApplication115;

import java.util.ArrayList;

public class Tour {
    
    double totalCost;
    Customer depot;
    ArrayList<Route> tour = new ArrayList<>();
            
    public Tour() {
        
    }

    public Tour(double totalCost) {
        this.totalCost = totalCost;
    }
    
    public void addRoute(Route r) {
        tour.add(r);
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    public double getTotalCost() {
        return totalCost;
    }
    
    public ArrayList<Route> getTour() {
        return tour;
    }
    
    public int getSize() {
        return tour.size();
    }
    
    public Route getRoute(int i) {
        return tour.get(i);
    }
    
    public void setDepot(Customer depot) {
        this.depot = depot;
    }

    public Customer getDepot() {
        return depot;
    } 

    @Override
    public String toString() {
        return "Tour{" + "totalCost=" + totalCost + ", tour=" + tour + '}';
    }
}
