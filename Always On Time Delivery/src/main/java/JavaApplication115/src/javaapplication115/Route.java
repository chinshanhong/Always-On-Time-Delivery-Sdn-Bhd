/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplication115;

import java.util.ArrayList;

/**
 *
 * @author Acer
 * Sequence of visited customers by a certain vehicle, starting and ending at a depot
 */
public class Route {
    Customer a;
    Customer depot;
    int size;
    double routeCost;
    ArrayList<Customer> route = new ArrayList<>();      //store the list of visited customer

    public Route() {
        size = 0;
    }

    public Route(Customer a, int size) {
        this.a = a;
        this.size = size;
    }

    public void addCustomer(Customer a){
        route.add(a);       //add customer to the end of the list
        size++;
    }
    
    public int getSize(){
        return this.size;       //return the size of the list
    }

    public ArrayList<Customer> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Customer> routes) {
        this.route = routes;
    }
    
    public Customer getCustomer(int i) {
        return route.get(i);
    }

    public double getRouteCost() {
        return routeCost;
    }

    public void setRouteCost(double routeCost) {
        this.routeCost = routeCost;
    }
    
    
    public void setDepot(Customer depot) {
        this.depot = depot;
    }

    public Customer getDepot() {
        return depot;
    } 
    

    
    @Override
    public String toString() {
        return "Route=" + route + '}';
    }

}
