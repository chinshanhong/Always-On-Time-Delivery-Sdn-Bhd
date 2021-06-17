/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplication115;

import JavaApplication115.Customer;
import JavaApplication115.DataFiltering;
import JavaApplication115.MCTs;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Always_on_timev6 {

  public static void main(String[] args) {
        System.out.println("Welcome to Always On Time company");
        System.out.println("Program created by MIX group");
        System.out.print("Enter text file name to apply (instances//testerfile.txt):");
        Scanner s = new Scanner(System.in);
        String filePath = s.next();
        
        int i = 1;
        int j = 1;
        DataFiltering filter = new DataFiltering();
        ArrayList<Customer> c = new ArrayList<>();
        ArrayList<String> data = new ArrayList<String>();
        data = filter.filterData(filePath);
        // System.out.println("MIX Solution Group");
        // System.out.println("Number Of Customer + Depot: " + filter.getNumOfCus());
        // System.out.println("Capacity for Vehicle: " + filter.getCapacity());
        int NumOfCustomer = Integer.parseInt(filter.getNumOfCus());
        int vehicleCapacity = Integer.parseInt(filter.getCapacity());

        String line = data.get(j);
        String[] arrLine = line.split(" ");
        int X = Integer.parseInt(arrLine[0]);
        int Y = Integer.parseInt(arrLine[1]);
        Customer cs = new Customer(X,Y,0,0);
        c.add(cs);
        // cs.setDepot(new Customer(X, Y, 0, 0));
        // System.out.println(cs.getDepot().toString());
        
        j = 2;
        while (j <= NumOfCustomer) {
            line = data.get(j);
            arrLine = line.split(" ");

            int x = Integer.parseInt(arrLine[0]);
            int y = Integer.parseInt(arrLine[1]);
            int ds = Integer.parseInt(arrLine[2]);
            cs = new Customer(x, y, ds, i);
            c.add(cs);
            // System.out.println(cs.toString());

//        customer.add(data.get(1));
            i++;
            j++;
        }
        
        System.out.println("Applying MCTS please wait...");
        
        MCTs a = new MCTs(NumOfCustomer, c, vehicleCapacity);
        
        
             a.display(a.search(3, 150));
             
        
       
    }
    
}
