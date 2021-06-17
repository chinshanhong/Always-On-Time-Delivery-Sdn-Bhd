///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package javaapplication115;
//
//import JavaApplication115.Customer;
//import JavaApplication115.DataFiltering;
//import JavaApplication115.MCTs;
//import JavaApplication115.Tour;
//import java.util.ArrayList;
//import java.util.Scanner;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.StackPane;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
///* Simple graph drawing class
//Bert Huang
//COMS 3137 Data Structures and Algorithms, Spring 2009
//
//This class is really elementary, but lets you draw 
//reasonably nice graphs/trees/diagrams. Feel free to 
//improve upon it!
// */
//
//import java.util.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class NewFXMain extends JFrame {
//    int width;
//    int height;
//
//    ArrayList<Node> nodes;
//    ArrayList<edge> edges;
//
//    public NewFXMain() { //Constructor
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	nodes = new ArrayList<Node>();
//	edges = new ArrayList<edge>();
//	width = 20;
//	height = 20;
//    }
//
//    public NewFXMain(String name) { //Construct with label
//	this.setTitle(name);
//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	nodes = new ArrayList<Node>();
//	edges = new ArrayList<edge>();
//	width = 20;
//	height = 20;
//    }
//
//    class Node {
//	int x, y;
//	String name;
//	
//	public Node(String myName, int myX, int myY) {
//	    x = myX;
//	    y = myY;
//	    name = myName;
//	}
//    }
//    
//    class edge {
//	int i,j;
//	
//	public edge(int ii, int jj) {
//	    i = ii;
//	    j = jj;	    
//	}
//    }
//    
//    public void addNode(String name, int x, int y) { 
//	//add a node at pixel (x,y)
//	nodes.add(new Node(name,x,y));
//	this.repaint();
//    }
//    public void addEdge(int i, int j) {
//	//add an edge between nodes i and j
//	edges.add(new edge(i,j));
//	this.repaint();
//    }
//    
//    public void paint(Graphics g) { // draw the nodes and edges
//	FontMetrics f = g.getFontMetrics();
//	int nodeHeight = Math.max(height, f.getHeight());
//
//	g.setColor(Color.black);
//	for (edge e : edges) {
//	    g.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
//		     nodes.get(e.j).x, nodes.get(e.j).y);
//	}
//
//	for (Node n : nodes) {
//	    int nodeWidth = Math.max(width, f.stringWidth(n.name)+width/2);
//	    g.setColor(Color.white);
//	    g.fillOval(n.x-nodeWidth/2, n.y-nodeHeight/2, 
//		       nodeWidth, nodeHeight);
//	    g.setColor(Color.black);
//	    g.drawOval(n.x-nodeWidth/2, n.y-nodeHeight/2, 
//		       nodeWidth, nodeHeight);
//	    
//	    g.drawString(n.name, n.x-f.stringWidth(n.name)/2,
//			 n.y+f.getHeight()/2);
//	}
//    }
//}
//
//class testGraphDraw {
//    //Here is some example syntax for the GraphDraw class
//    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        String filePath = "instances//testerfile.txt";
//        
//        int i = 1;
//        int j = 1;
//        DataFiltering filter = new DataFiltering();
//        ArrayList<Customer> c = new ArrayList<>();
//        ArrayList<String> data = new ArrayList<String>();
//        data = filter.filterData(filePath);
//        // System.out.println("MIX Solution Group");
//        // System.out.println("Number Of Customer + Depot: " + filter.getNumOfCus());
//        // System.out.println("Capacity for Vehicle: " + filter.getCapacity());
//        int NumOfCustomer = Integer.parseInt(filter.getNumOfCus());
//        int vehicleCapacity = Integer.parseInt(filter.getCapacity());
//
//        String line = data.get(j);
//        String[] arrLine = line.split(" ");
//        int X = Integer.parseInt(arrLine[0]);
//        int Y = Integer.parseInt(arrLine[1]);
//        Customer cs = new Customer(X,Y,0,0);
//        c.add(cs);
//        // cs.setDepot(new Customer(X, Y, 0, 0));
//        // System.out.println(cs.getDepot().toString());
//        
//        j = 2;
//        while (j <= NumOfCustomer) {
//            line = data.get(j);
//            arrLine = line.split(" ");
//
//            int x = Integer.parseInt(arrLine[0]);
//            int y = Integer.parseInt(arrLine[1]);
//            int ds = Integer.parseInt(arrLine[2]);
//            cs = new Customer(x, y, ds, i);
//            c.add(cs);
//            // System.out.println(cs.toString());
//
////        customer.add(data.get(1));
//            i++;
//            j++;
//        }
//        
//        System.out.println("Applying MCTS please wait...");
//        
//        MCTs a = new MCTs(NumOfCustomer, c, vehicleCapacity);
//        
//        
//             Tour bestTour = a.search(3, 150);
//             
//        System.out.println(bestTour);
//       
//    
//	NewFXMain frame = new NewFXMain("Always on Time");
//
//	frame.setSize(800,500);
//	
//	frame.setVisible(true);
//        
//        
//   for ( i = 0; i < bestTour.getSize()+1; i++) {
//       System.out.println("Route "+ i);
//            for ( j = 0; j < bestTour.getRoute(i).getSize(); j++) {
//                 if (j == bestTour.getRoute(i).getSize() - 1) {
//                     frame.addNode(Integer.toString(bestTour.getRoute(i).getCustomer(j).getId()), bestTour.getRoute(i).getCustomer(j).getX()*5,bestTour.getRoute(i).getCustomer(j).getY()*5);
//                } else {
//                     
//                    frame.addNode(Integer.toString(bestTour.getRoute(i).getCustomer(j).getId()), bestTour.getRoute(i).getCustomer(j).getX()*5,bestTour.getRoute(i).getCustomer(j).getY()*5);
//                    frame.addEdge(bestTour.getRoute(i).getCustomer(j).getId(),bestTour.getRoute(i).getCustomer(j+1).getId());
//                    
//                    
//                }
//                    
//
//            }
//            
//        }
//	
//	
//    }
//}
package JavaApplication115;

import JavaApplication115.Customer;
import JavaApplication115.DataFiltering;
import JavaApplication115.MCTs;
import JavaApplication115.Tour;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import static javafx.application.Application.launch;

/**
 * Author Chin Shan Hong
 */
public class NewFXMain extends Application {
    
    

//    private static TableView<VehicleData> table;
//    private static ObservableList<VehicleData> data;


    /**
     * Add data into a table
     * @param vehicles
     */
//    public void populateData(Tour bestTour){
//        int totalCapacity;
//           
//             
//             for (int i = 0; i < bestTour.getSize(); i++) {
//            System.out.println("Vehicle " + (i + 1));
//           
//            for (int j = 0; j < bestTour.getRoute(i).getSize(); j++) {
//                if (j == bestTour.getRoute(i).getSize() - 1) {
//                    System.out.print(bestTour.getRoute(i).getCustomer(j).getId() + "\n");
//                } else {
//                    System.out.print(bestTour.getRoute(i).getCustomer(j).getId() + " -> ");
//                }
//                totalCapacity += bestTour.getRoute(i).getCustomer(j).getDemand_size();
//            }
//            System.out.println("Capacity: " + totalCapacity);
//            System.out.println("Cost: " + bestTour.getRoute(i).getRouteCost());
//        }
//        for(int i = 0; i < bestTour.getRoute(i).getSize(); i++){
//            String load = bestTour.getRoute(i).getCustomer(j).getDemand_size() + "";
//            vehicledata.add(new VehicleData((i + 1)+"",
//                    vehicles.get(i).getStringRoute()+"", vehicles.get(i).getCapacity() + "", load,
//                    vehicles.get(i).getRemainingCapacity() + "",bestTour.getRoute(i).getRouteCost()+""));
//
//        }
//        data = FXCollections.observableArrayList(vehicledata);
//    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//        table = new TableView<>();
        
         String filePath = "instances//testerfile.txt";
        
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
        
         int totalCapacity = 0;
//        ArrayList<VehicleData> vehicledata = new ArrayList<>();
        String route = "";
        
             Tour bestTour = a.search(3, 150);

//        //Get the vertex array from Main class
//        Vertex[] vertices = BFS3.getVertices();
//
//        //Convert vertex array into vertex List
//        List<Vertex> list = Arrays.asList(vertices);
//
//        // Convert vertex List into vertex LinkedList
//        LinkedList<Vertex> vertexLinkedList = new LinkedList<>(list);
//
//        depot = BFS3.getLowestCostDepot();
//
//        //Get vehicle ArrayList from depot
//        ArrayList<Vehicle> vehicles = depot.getVehicles();

        // Call populateData() method to add data into the table created
//        populateData(vehicles);

        //Initialise Group object to create GUI
        Group group = new Group();

        //Create Circle objects for graph visualization
        Circle[] circles = initialiseCircleArray(bestTour);

        //Add the Circle object into Group object
        getCircle(group, circles, bestTour);

        //Add the Arrow objects into Group objects
        getArrows(group, bestTour);

        //Set the label for the display table
        Label label = new Label("Tour Details");
//
//        //Set the font type and size of the label
        label.setFont(new Font("Arial", 20));
//
//        // Creating TableColumn objects
//        TableColumn vehicleColumn = new TableColumn("Vehicle ID");
//        vehicleColumn.setMinWidth(200);
//        vehicleColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleID"));
//
//        TableColumn pathColumn = new TableColumn("Path");
//        pathColumn.setMinWidth(200);
//        pathColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleRoute"));
//
//        TableColumn originalCapacityColumn = new TableColumn("Capacity");
//        originalCapacityColumn.setMinWidth(200);
//        originalCapacityColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("capacity"));
//
//        TableColumn loadColumn = new TableColumn("Load");
//        loadColumn.setMinWidth(200);
//        loadColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("load"));
//
//        TableColumn remainingCapacityColumn = new TableColumn("Remaining Capacity");
//        remainingCapacityColumn.setMinWidth(200);
//        remainingCapacityColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("remainingCapacity"));
//
//        TableColumn routeCostColumn = new TableColumn("Route Cost");
//        routeCostColumn.setMinWidth(200);
//        routeCostColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleCost"));
//
//        //Add the data into the table
//        table.setItems(data);

        //Add the columns into the table
//        table.getColumns().addAll(vehicleColumn, pathColumn,
//                originalCapacityColumn, loadColumn, remainingCapacityColumn, routeCostColumn);

        //Create a Text object to display the tour cost in GUI
        Text tourCostDisplay = new Text("Tour Cost: " + bestTour.getTotalCost());

        //Set the font size and type of the text
        tourCostDisplay.setFont(new Font("Arial", 20));

        //Create a Text object to display the name of the simulation
        Text simulationTitle = new Text("Basic Simulation");
        Font font = Font.font("Arial", FontWeight.BOLD, 30);
        simulationTitle.setFont(font);
        simulationTitle.setFill(Color.BLUE);


        //Create a return button
        Image buttonImage = new Image("//icons//redButton.png");
        ImageView buttonImageView = new ImageView(buttonImage);
        buttonImageView.setFitHeight(30);
        buttonImageView.setFitWidth(30);
        Button returnButton = new Button("Return", buttonImageView);
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ;
            }
        });



        //Create a VBox object for the display layout
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
//        vbox.getChildren().addAll(simulationTitle, group,label,table, tourCostDisplay, returnButton);
        vbox.getChildren().addAll(simulationTitle, group,label,tourCostDisplay, returnButton);


        //Create a Scene object
        Scene scene = new Scene(vbox, 300, 300);

        //Set the properties for Stage object
        primaryStage.setTitle("Always On Time Sdn Bhd");
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("//icons//truck.png"));
        primaryStage.show();

    }

    /**
     * Create an array of Circle object according to the number of vertex
     * @param vertices
     * @return
     */
    public static Circle[] initialiseCircleArray(Tour bestTour){
        Circle[] circles = new Circle[bestTour.getSize()];

   for ( int i = 0; i < bestTour.getSize()+1; i++) {
            for ( int j = 0; j < bestTour.getRoute(i).getSize(); j++) {
                
                     
                      if(i == 0){
                circles[i] = new Circle();
                circles[i].setCenterX(bestTour.getRoute(i).getCustomer(j).getX() * 2.5);
                circles[i].setCenterY(bestTour.getRoute(i).getCustomer(j).getY() * 2.5);
                circles[i].setRadius(10);
                circles[i].setFill(Color.RED);
            }
            //If i != 0, it is customer
            else{
                circles[i] = new Circle();
                circles[i].setCenterX(bestTour.getRoute(i).getCustomer(j).getX() * 2.5);
                circles[i].setCenterY(bestTour.getRoute(i).getCustomer(j).getY() * 2.5);
                circles[i].setRadius(10);
                circles[i].setFill(Color.CYAN);
            }
                    
                    
               
                    

            }
            
        }
   return circles;
    }

    /**
     * Add the Circle and Text object into the Group object
     * @param group
     * @param circles
     * @param vertices
     */
    public static void getCircle(Group group, Circle[] circles, Tour bestTour){
        
           for ( int i = 0; i < bestTour.getSize()+1; i++) {
            for ( int j = 0; j < bestTour.getRoute(i).getSize(); j++) {
                
                     
                      if(i == 0){
            group.getChildren().add(circles[i]);
            group.getChildren().add(new Text(bestTour.getRoute(i).getCustomer(j).getX() * 2.5 - 15,
                    bestTour.getRoute(i).getCustomer(j).getY() * 2.5 - 15, "Depot"));
            }
            //If i != 0, it is customer
            else{
              group.getChildren().add(circles[i]);
               group.getChildren().add(new Text(bestTour.getRoute(i).getCustomer(j).getX() * 2.5 - 15,
                       bestTour.getRoute(i).getCustomer(j).getY() * 2.5 - 15 , "C" + bestTour.getRoute(i).getCustomer(j).getId()));
            }
                    
                    
               
                    

            }
            
        }
        
    }

    /**
     * Create arrows according to the tour
     * @param group
     * @param vehicles
     * @param vertices
     */
    public static void getArrows(Group group, Tour bestTour){
        
          for ( int i = 0; i < bestTour.getSize()+1; i++) {
            for ( int j = 0; j < bestTour.getRoute(i).getSize(); j++) {
                
                     
                int vertexID1 = bestTour.getRoute(i).getCustomer(j).getId();
                int vertexID2 = bestTour.getRoute(i).getCustomer(j+1).getId();
                Arrow arrow = new Arrow(bestTour.getRoute(i).getCustomer(j).getX() * 2.5,
                        bestTour.getRoute(i).getCustomer(j).getY() * 2.5,
                        bestTour.getRoute(i).getCustomer(j+1).getX() * 2.5,
                        bestTour.getRoute(i).getCustomer(j+1).getY() * 2.5, 10);
                group.getChildren().add(arrow);   
                    
               
                    

            }
            
        }
    }
}


   


    /***
     * A static class used to create data for the table
     */
//    public static class VehicleData {
//        private final SimpleStringProperty vehicleID;
//        private final SimpleStringProperty vehicleRoute;
//        private final SimpleStringProperty vehicleCost;
//        private final SimpleStringProperty capacity;
//        private final SimpleStringProperty remainingCapacity;
//        private final SimpleStringProperty load;
//
//        public VehicleData(String ID,
//                           String Route,
//                           String capacityInput,
//                           String loadInput,
//                           String remainingCapacityInput,
//                           String Cost) {
//            this.vehicleID = new SimpleStringProperty(ID);
//            this.vehicleRoute = new SimpleStringProperty(Route);
//            this.vehicleCost = new SimpleStringProperty(Cost);
//            this.capacity = new SimpleStringProperty(capacityInput);
//            this.remainingCapacity = new SimpleStringProperty(remainingCapacityInput);
//            this.load = new SimpleStringProperty(loadInput);
//        }
//
//        public String getVehicleID() {
//            return vehicleID.get();
//        }
//
//        public SimpleStringProperty vehicleIDProperty() {
//            return vehicleID;
//        }
//
//        public void setVehicleID(String vehicleID) {
//            this.vehicleID.set(vehicleID);
//        }
//
//        public String getVehicleCost(){
//            return vehicleCost.get();
//        }
//
//        public String getVehicleRoute(){
//            return vehicleRoute.get();
//        }
//
//        public SimpleStringProperty vehicleRouteProperty() {
//            return vehicleRoute;
//        }
//        public void setVehicleRoute(String vehicleRoute) {
//            this.vehicleRoute.set(vehicleRoute);
//        }
//        public String getRemainingCapacity(){
//            return remainingCapacity.get();
//        }
//        public String getCapacity(){
//            return capacity.get();
//        }
//        public SimpleStringProperty capacityProperty() {
//            return capacity;
//        }
//
//        public SimpleStringProperty remainingCapacityProperty() {
//            return remainingCapacity;
//        }
//        public SimpleStringProperty vehicleCostProperty() {
//            return vehicleCost;
//        }
//
//        public String getLoad() {
//            return load.get();
//        }
//
//        public SimpleStringProperty loadProperty() {
//            return load;
//        }
//
//        public void setLoad(String load) {
//            this.load.set(load);
//        }
//    }
//}


