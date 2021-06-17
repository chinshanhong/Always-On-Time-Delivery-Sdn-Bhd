/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplication115;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class DataFiltering {

    private String filePath;
    private String NumOfCus;
    private String Capacity;

    public DataFiltering() {
    }

    public DataFiltering(String filePath) {
        this.filePath = filePath;
    }

    public String getNumOfCus() {
        return NumOfCus;
    }

    public void setNumOfCus(String NumOfCus) {
        this.NumOfCus = NumOfCus;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }

    public ArrayList filterData(String filePath) {
        ArrayList<String> data = new ArrayList<String>();
        int i = 0;
        int j = 0;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");

        }

        String impdetail = data.get(0);
        String[] arrOfStr = impdetail.split(" ");
        setNumOfCus(arrOfStr[0]);
        setCapacity(arrOfStr[1]);

        int NumOfCustomer = Integer.parseInt(NumOfCus);
        j = 1;
        while (j <= NumOfCustomer) {

            impdetail = data.get(j);         
            j++;
        }
        return data;
    }
}
