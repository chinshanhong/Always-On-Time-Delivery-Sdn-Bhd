package JavaApplication115;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class MCTs {

    private int N;
    private double[][][] policy = new double[100][100][100];
    private double[][] globalPolicy = new double[100][100];

    private int level = 3;
    private int iterations = 100;
    private int ALPHA = 1;

    private int vehicleMaxCapacity;
    private ArrayList<Customer> customerList;
    private static int numOfRoutes = 1;
    private static double totalTourCost = 0.0;
    private Customer depot;
    private Tour best_tour = new Tour(Double.MAX_VALUE);
    private double best_tour_cost = Double.MAX_VALUE;
    private Tour new_tour;
    private static int timer = 1;

    public MCTs(int N, ArrayList<Customer> customerList, int vehicleMaxCapacity) {
        this.N = N;
        this.customerList = customerList;
        this.vehicleMaxCapacity = vehicleMaxCapacity;
    }

    public Tour search(int level, int iterations) {
        long startTime = System.nanoTime();
        
        double new_tour_cost;
        if (level == 0) {
            return rollout();
        } else {
            // System.out.println(customerList);
            // System.out.println("search");
            policy[level] = globalPolicy;
            for (int i = 0; i < iterations; i++) {
                if((System.nanoTime()-startTime)/1000000000 == timer) {
                    System.out.print("\r");
                    System.out.print("Time elapsed: |");
                    for(int j=0; j<timer; j++) {
                        System.out.print("=");
                    }
                   
                    System.out.print("| " + timer + "s");
                    timer++;
                }
                new_tour = search(level - 1, iterations);
                // System.out.println("new tour: " + new_tour.getTour());
                // System.out.println(new_tour.getTotalCost());
                new_tour_cost = totalTourCost;
                totalTourCost = 0;
                // System.out.println(new_tour_cost);
                if (new_tour_cost < best_tour_cost && new_tour_cost != 0) {
                    // System.out.println("Before: " + best_tour_cost);
                    best_tour = new_tour;
                    best_tour_cost = new_tour_cost;
                    // System.out.println("After: " + best_tour_cost);
                    adapt(best_tour, level);
                }
                if ((System.nanoTime() - startTime) / 1000000000 > 60) {
                    System.out.println(" (Searching is forced to stop)");
                    return best_tour;
                }
            }
            globalPolicy = policy[level];
        }
        // System.out.println("done search");
        return best_tour;
    }

    public void adapt(Tour a_tour, int level) {
        for (int i = 0; i < a_tour.getSize(); i++) {
            for (int j = 0; j < a_tour.getRoute(i).getSize(); j++) {
                policy[level][j][j + 1] += ALPHA;
                double z = 0.0;
                z += Math.exp(globalPolicy[i][j]);
                policy[level][i][j] -= ALPHA * (Math.exp(globalPolicy[i][j]) / z);
            }
        }
    }

    public Tour rollout() {
        Route currentRoute = new Route();
        new_tour = new Tour();
        // System.out.println("customer list : " + customerList);
        depot = customerList.get(0);
        currentRoute.addCustomer(depot);
        new_tour.addRoute(currentRoute);

        Vehicle a = new Vehicle(vehicleMaxCapacity);
        Route lastRoute = new_tour.getRoute(new_tour.getSize() - 1);
        Customer currentStop = lastRoute.getCustomer(lastRoute.getSize() - 1);

        ArrayList<Customer> successorList = new ArrayList<>();
        for (int i = 1; i < customerList.size(); i++) {
            successorList.add(customerList.get(i));
        }

        currentStop.setSuccessor(successorList);
        ArrayList<Customer> possible_successors = currentStop.getSuccessor();
        ArrayList<Customer> check = new ArrayList<>();
        ArrayList<Customer> visit = new ArrayList<>();

        while (true) {
            lastRoute = new_tour.getRoute(new_tour.getSize() - 1);
            currentStop = lastRoute.getCustomer(lastRoute.getSize() - 1);

            // System.out.println(possible_successors);
            // System.out.println(check);
            // System.out.println(visit);
            for (int i = 0; i < possible_successors.size(); i++) {
                if (check.contains(possible_successors.get(i))) {
                    possible_successors.remove(i);
                } else {
                    // System.out.println("wrong remove");
                }
            }

            if (possible_successors.size() == 0) {
                double cost = 0;
                currentRoute.addCustomer(depot);
                /*
                System.out.println("Route " + numOfRoutes);
                for (int i = 0; i < currentRoute.getSize(); i++) {
                    if (i == currentRoute.getSize() - 1) {
                        System.out.print(currentRoute.getCustomer(i).getId() + "\n");
                    } else {
                        System.out.print(currentRoute.getCustomer(i).getId() + "->");
                    }
                }
                 */
                for (int i = 0; i < currentRoute.getSize() - 1; i++) {
                    cost += computeDistance(currentRoute.getCustomer(i), currentRoute.getCustomer(i + 1));
                }
                currentRoute.setRouteCost(cost);
                totalTourCost += cost;
                new_tour.setTotalCost(totalTourCost);
                // System.out.println(cost);

                if (check.size() == 0) {
                    break;
                }

                currentRoute = new Route();
                currentRoute.addCustomer(depot);
                new_tour.addRoute(currentRoute);
                a = new Vehicle(vehicleMaxCapacity);
                possible_successors = check;
                check = new ArrayList<>();
                visit = new ArrayList<>();
                numOfRoutes++;
                // System.out.println(numOfRoutes);
                continue;
            }

            Customer nextStop = select_next_move(currentStop, possible_successors);

            if (a.capacityLeft >= nextStop.getDemand_size()) {
                currentRoute.addCustomer(nextStop);
                // nextStop.setVisited(true);
                visit.add(nextStop);
                a.computeCapacityLeft(nextStop.getDemand_size());
                // System.out.println(a.capacityLeft);
                possible_successors.remove(nextStop);
                // System.out.println(nextStop);
            } else {
                // nextStop.setChecked(true);
                check.add(nextStop);
                // System.out.println(nextStop);
            }
            // System.out.println("\nrollout");
        }
        return new_tour;
    }

    public Customer select_next_move(Customer currentStop, ArrayList<Customer> possible_successors) {
        double[] probability = new double[possible_successors.size()];
        double sum = 0;
        for (int i = 0; i < possible_successors.size(); i++) {
            probability[i] = Math.exp(globalPolicy[currentStop.getId()][possible_successors.get(i).getId()]);
            sum += probability[i];
        }
        double mrand = new Random().nextDouble() * sum;
        int i = 0;
        sum = probability[0];
        while (sum < mrand) {
            sum += probability[++i];
        }
        // System.out.println("select next move");
        // System.out.println(probability[0]);
        // System.out.println(sum);
        // System.out.println(possible_successors.get(i));
        return possible_successors.get(i);
    }

    public double computeDistance(Customer v1, Customer v2) {
        double delta_X, delta_Y;

        delta_X = v1.getX() - v2.getX();
        delta_Y = v1.getY() - v2.getY();

        double distance = Math.sqrt(((Math.pow(delta_X, 2) + Math.pow(delta_Y, 2))));
        return distance;
    }

    public void display(Tour bestTour) {
        System.out.println(bestTour);
        System.out.println("");
        System.out.println("Tour");
        System.out.println("Tour Cost: " + bestTour.getTotalCost());

        for (int i = 0; i < bestTour.getSize(); i++) {
            System.out.println("Vehicle " + (i + 1));
            int totalCapacity = 0;
            for (int j = 0; j < bestTour.getRoute(i).getSize(); j++) {
                if (j == bestTour.getRoute(i).getSize() - 1) {
                    System.out.print(bestTour.getRoute(i).getCustomer(j).getId() + "\n");
                } else {
                    System.out.print(bestTour.getRoute(i).getCustomer(j).getId() + " -> ");
                }
                totalCapacity += bestTour.getRoute(i).getCustomer(j).getDemand_size();
            }
            System.out.println("Capacity: " + totalCapacity);
            System.out.println("Cost: " + bestTour.getRoute(i).getRouteCost());
        }
    }

}
