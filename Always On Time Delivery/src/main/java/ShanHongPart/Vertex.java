package ShanHongPart;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Author Chin Shan Hong
 * Vertex class to create vertices
 */
public class Vertex {

    //A variable used to store the x coordinate of a vertex object
    private int x_coordinate;

    //A variable used to store the y coordinate of a vertex object
    private int y_coordinate;

    //A variable used to store the id of a vertex object
    private int id;

    //A variable used to store the neighbors of a vertex object
    private LinkedList<Vertex> neighbors;

    //A variable used to store the neighbors distance of a vertex object
    private ArrayList<Double> neighborsDistance;

    //A variable used to verify whether the Vertex object has been visited;
    private boolean isVisited;


    /**
     * A constructor for Vertex object which will be inherited for its child classes
     *
     * @param x_coordinate
     * @param y_coordinate
     * @param id
     */
    public Vertex(int x_coordinate, int y_coordinate, int id) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.id = id;
        this.neighborsDistance = new ArrayList<>();
        this.neighbors = new LinkedList<>();
        this.isVisited = false;
    }


    /**
     * Get the x coordinate of a Vertex object
     *
     * @return x_coordinate
     */
    public int getX_coordinate() {
        return x_coordinate;
    }

    /**
     * Get the y coordinate of a Vertex object
     *
     * @return y_coordinate
     */
    public int getY_coordinate() {
        return y_coordinate;
    }

    /**
     * Get the neighbor of a Vertex object
     *
     * @return neighbors in LinkedList
     */
    public LinkedList<Vertex> getNeighbors() {
        return neighbors;
    }

    /**
     * Get the Id of a Vertex object
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the neighbors distance of a Vertex object
     *
     * @return neighbors distance in ArrayList
     */
    public ArrayList<Double> getNeighborsDistance() {
        return neighborsDistance;
    }

    /**
     * A method for checking whether a vertex has been visited
     *
     * @return isVisited in boolean
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * Set a Vertex as visited
     *
     * @param visited
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}








