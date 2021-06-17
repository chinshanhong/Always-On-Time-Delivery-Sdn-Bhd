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
 */
public class Vehicle {
    int capacityMax;               //store the maximum capacity of vehicle
    int capacityUsed;             //store the current capacity used
    int capacityLeft;             //store the capacity left
    int cost;                     //store the cost between customer and depot
    int id = 0;
    //ArrayList<Customer> route;    //store the list of visited customer
    ArrayList<Route> tour;        //store the list of routes

    public Vehicle(int capacityMax, int capacityUsed, int cost) {
        this.capacityMax = capacityMax;
        this.capacityUsed = capacityUsed;
        this.cost = cost;
        this.capacityLeft = this.capacityMax - this.capacityUsed;
        id++;
    }

    public Vehicle(int capacityMax) {
        this.capacityMax = capacityMax;
        capacityUsed = 0;
        capacityLeft = capacityMax;
        id++;
    }
    
    public int computeCapacityLeft(int amount) {
        capacityLeft -= amount;
        return capacityLeft;
    }
}
