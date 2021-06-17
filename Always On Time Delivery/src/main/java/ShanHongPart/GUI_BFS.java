package ShanHongPart;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
 * Author Chin Shan Hong
 */
public class GUI_BFS{

    private static TableView<VehicleData> table;
    private static ObservableList<VehicleData> data;
    private static Depot depot;
    private static String filePath;

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        GUI_BFS.filePath = filePath;
    }

    /**
     * Add data into a table
     * @param vehicles
     */
    public void populateData(ArrayList<Vehicle> vehicles){
        ArrayList<VehicleData> vehicledata = new ArrayList<>();
        for(int i = 0; i < vehicles.size(); i++){
            String load = vehicles.get(i).getCapacity() - vehicles.get(i).getRemainingCapacity() + "";
            vehicledata.add(new VehicleData(vehicles.get(i).getVehicleID()+"",
                    vehicles.get(i).getStringRoute()+"", vehicles.get(i).getCapacity() + "", load,
                    vehicles.get(i).getRemainingCapacity() + "",vehicles.get(i).getCost()+""));

        }
        data = FXCollections.observableArrayList(vehicledata);
    }



    public void display() {

        table = new TableView<>();

        Stage primaryStage = new Stage();

        BFS bfs = new BFS(getFilePath());

        bfs.BFSearch();

        //Get the vertex array from Main class
        Vertex[] vertices =bfs.getVertices();

        //Convert vertex array into vertex List
        List<Vertex> list = Arrays.asList(vertices);

        // Convert vertex List into vertex LinkedList
        LinkedList<Vertex> vertexLinkedList = new LinkedList<>(list);

        depot = bfs.getLowestCostDepot();

        //Get vehicle ArrayList from depot
        ArrayList<Vehicle> vehicles = depot.getVehicles();

        // Call populateData() method to add data into the table created
        populateData(vehicles);

        //Initialise Group object to create GUI
        Group group = new Group();

        //Create Circle objects for graph visualization
        Circle[] circles = initialiseCircleArray(vertices);

        //Add the Circle object into Group object
        getCircle(group, circles, vertices);

        //Add the Arrow objects into Group objects
        getArrows(group, vehicles, vertices);

        //Set the label for the display table
        Label label = new Label("Tour Details");

        //Set the font type and size of the label
        label.setFont(new Font("Arial", 20));

        // Creating TableColumn objects
        TableColumn vehicleColumn = new TableColumn("Vehicle ID");
        vehicleColumn.setMinWidth(200);
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleID"));

        TableColumn pathColumn = new TableColumn("Path");
        pathColumn.setMinWidth(200);
        pathColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleRoute"));

        TableColumn originalCapacityColumn = new TableColumn("Capacity");
        originalCapacityColumn.setMinWidth(200);
        originalCapacityColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("capacity"));

        TableColumn loadColumn = new TableColumn("Load");
        loadColumn.setMinWidth(200);
        loadColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("load"));

        TableColumn remainingCapacityColumn = new TableColumn("Remaining Capacity");
        remainingCapacityColumn.setMinWidth(200);
        remainingCapacityColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("remainingCapacity"));

        TableColumn routeCostColumn = new TableColumn("Route Cost");
        routeCostColumn.setMinWidth(200);
        routeCostColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleCost"));

        //Add the data into the table
        table.setItems(data);

        //Add the columns into the table
        table.getColumns().addAll(vehicleColumn, pathColumn,
                originalCapacityColumn, loadColumn, remainingCapacityColumn, routeCostColumn);

        //Create a Text object to display the tour cost in GUI
        Text tourCostDisplay = new Text("Tour Cost: " + depot.getTourCost());

        //Set the font size and type of the text
        tourCostDisplay.setFont(new Font("Arial", 20));

        //Create a Text object to display the name of the simulation
        Text simulationTitle = new Text("Basic Simulation");
        Font font = Font.font("Arial", FontWeight.BOLD, 30);
        simulationTitle.setFont(font);
        simulationTitle.setFill(Color.BLUE);


        //Create a return button
        Image buttonImage = new Image("/icons/redButton.png");
        ImageView buttonImageView = new ImageView(buttonImage);
        buttonImageView.setFitHeight(30);
        buttonImageView.setFitWidth(30);
        Button returnButton = new Button("Return", buttonImageView);
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });



        //Create a VBox object for the display layout
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(simulationTitle, group,label,table, tourCostDisplay, returnButton);


        //Create a Scene object
        Scene scene = new Scene(vbox, 300, 300);

        //Set the properties for Stage object
        primaryStage.setTitle("Always On Time Sdn Bhd");
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/icons/truck.png"));
        primaryStage.show();

    }

    /**
     * Create an array of Circle object according to the number of vertex
     * @param vertices
     * @return
     */
    public static Circle[] initialiseCircleArray(Vertex[] vertices){
        Circle[] circles = new Circle[vertices.length];
        for(int i = 0; i < vertices.length; i++){
            //If i == 0, it is depot
            if(i == 0){
                circles[i] = new Circle();
                circles[i].setCenterX(vertices[i].getX_coordinate() * 2.5);
                circles[i].setCenterY(vertices[i].getY_coordinate() * 2.5);
                circles[i].setRadius(10);
                circles[i].setFill(Color.RED);
            }
            //If i != 0, it is customer
            else{
                circles[i] = new Circle();
                circles[i].setCenterX(vertices[i].getX_coordinate() * 2.5);
                circles[i].setCenterY(vertices[i].getY_coordinate() * 2.5);
                circles[i].setRadius(10);
                circles[i].setFill(Color.CYAN);
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
    public static void getCircle(Group group, Circle[] circles, Vertex[] vertices){
        for(int i = 0 ; i < circles.length; i++){
            //If i == 0, it is depot
            if(i == 0){
                group.getChildren().add(circles[i]);
                group.getChildren().add(new Text(vertices[i].getX_coordinate() * 2.5 - 15,
                        vertices[i].getY_coordinate() * 2.5 - 15, "Depot"));

            }
            //If i != 0, it is customer
            else{
                group.getChildren().add(circles[i]);
                group.getChildren().add(new Text(vertices[i].getX_coordinate() * 2.5 - 15,
                        vertices[i].getY_coordinate() * 2.5 - 15 , "C" + vertices[i].getId()));
            }
        }
    }

    /**
     * Create arrows according to the tour
     * @param group
     * @param vehicles
     * @param vertices
     */
    public static void getArrows(Group group, ArrayList<Vehicle> vehicles, Vertex[] vertices){

        for(int i = 0; i < vehicles.size(); i++){
            for(int j = 0; j < vehicles.get(i).getRoute().size() - 1; j++){
                int vertexID1 = vehicles.get(i).getRoute().get(j);
                int vertexID2 = vehicles.get(i).getRoute().get(j + 1);
                Arrow arrow = new Arrow(vertices[vertexID1].getX_coordinate() * 2.5,
                        vertices[vertexID1].getY_coordinate() * 2.5,
                        vertices[vertexID2].getX_coordinate() * 2.5,
                        vertices[vertexID2].getY_coordinate() * 2.5, 10);
                group.getChildren().add(arrow);
            }
        }
    }


    /***
     * A static class used to create data for the table
     */
    public static class VehicleData {
        private final SimpleStringProperty vehicleID;
        private final SimpleStringProperty vehicleRoute;
        private final SimpleStringProperty vehicleCost;
        private final SimpleStringProperty capacity;
        private final SimpleStringProperty remainingCapacity;
        private final SimpleStringProperty load;

        public VehicleData(String ID,
                           String Route,
                           String capacityInput,
                           String loadInput,
                           String remainingCapacityInput,
                           String Cost) {
            this.vehicleID = new SimpleStringProperty(ID);
            this.vehicleRoute = new SimpleStringProperty(Route);
            this.vehicleCost = new SimpleStringProperty(Cost);
            this.capacity = new SimpleStringProperty(capacityInput);
            this.remainingCapacity = new SimpleStringProperty(remainingCapacityInput);
            this.load = new SimpleStringProperty(loadInput);
        }

        public String getVehicleID() {
            return vehicleID.get();
        }

        public SimpleStringProperty vehicleIDProperty() {
            return vehicleID;
        }

        public void setVehicleID(String vehicleID) {
            this.vehicleID.set(vehicleID);
        }

        public String getVehicleCost(){
            return vehicleCost.get();
        }

        public String getVehicleRoute(){
            return vehicleRoute.get();
        }

        public SimpleStringProperty vehicleRouteProperty() {
            return vehicleRoute;
        }
        public void setVehicleRoute(String vehicleRoute) {
            this.vehicleRoute.set(vehicleRoute);
        }
        public String getRemainingCapacity(){
            return remainingCapacity.get();
        }
        public String getCapacity(){
            return capacity.get();
        }
        public SimpleStringProperty capacityProperty() {
            return capacity;
        }

        public SimpleStringProperty remainingCapacityProperty() {
            return remainingCapacity;
        }
        public SimpleStringProperty vehicleCostProperty() {
            return vehicleCost;
        }

        public String getLoad() {
            return load.get();
        }

        public SimpleStringProperty loadProperty() {
            return load;
        }

        public void setLoad(String load) {
            this.load.set(load);
        }
    }
}
