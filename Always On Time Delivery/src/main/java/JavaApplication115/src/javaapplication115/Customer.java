package JavaApplication115;

import java.util.ArrayList;

public class Customer {
    int x,y;//used to store x and y coordinate
    int demand_size; // used to store the capacity
    int id; //used to store assigned ID
    boolean visited = false;
    boolean checked = false;
    Customer depot;
    ArrayList<Customer> successor = new ArrayList<>();
    ArrayList<Customer> check = new ArrayList<>();
    ArrayList<Customer> visit = new ArrayList<>();

    public Customer() {
    }

    public Customer(int x, int y, int demand_size, int id) {
        this.x = x;
        this.y = y;
        this.demand_size = demand_size;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getDemand_size() {
        return demand_size;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        Customer a = new Customer(x,y,demand_size,id);
        visit.add(a);
        this.visited = visited;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        Customer a = new Customer(x,y,demand_size,id);
        check.add(a);
        this.checked = checked;
    }

    public ArrayList<Customer> getCheck() {
        return check;
    }

    public ArrayList<Customer> getVisit() {
        return visit;
    }

    public void setSuccessor(ArrayList<Customer> successor) {
        this.successor = successor;
        /*
        this.successor.add(new Customer(29,17,1,1));
        this.successor.add(new Customer(4,50,8,2));
        this.successor.add(new Customer(25,13,6,3));
        this.successor.add(new Customer(67,37,5,4));
*/
    }
    
    public ArrayList<Customer> getSuccessor() {
        return successor;
    }

    @Override
    public String toString() {
        return "Successor{" + "x=" + x + ", y=" + y + ", demand_size=" + demand_size + ", id=" + id;
    }
}
