import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class Destination {
    private String name;
    private String startDate;
    private String endDate;
    private double budget;

    public Destination(String name, String startDate, String endDate, double budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getBudget() {
        return budget;
    }
}

class WeatherService {
    public static String getWeather(String destination) {
        // Simulate fetching weather data
        return "Sunny, 25°C";
    }
}

class MapService {
    public static String getMap(String destination) {
        try {
            // URL encode the destination name to handle spaces and special characters
            String encodedDestination = URLEncoder.encode(destination, "UTF-8");
            return "https://maps.example.com/?q=" + encodedDestination;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Invalid destination";
        }
    }
}

class BudgetCalculator {
    public static double calculateTotalBudget(List<Destination> destinations) {
        double totalBudget = 0;
        for (Destination destination : destinations) {
            totalBudget += destination.getBudget();
        }
        return totalBudget;
    }
}

public class TravelItineraryPlanner {
    private List<Destination> destinations;

    public TravelItineraryPlanner() {
        this.destinations = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public void generateItinerary() {
        System.out.println("Travel Itinerary:");
        for (Destination destination : destinations) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Travel Dates: " + destination.getStartDate() + " to " + destination.getEndDate());
            System.out.println("Weather: " + WeatherService.getWeather(destination.getName()));
            System.out.println("Map: " + MapService.getMap(destination.getName()));
            System.out.println("Allocated Budget: $" + destination.getBudget());
            System.out.println();
        }
        double totalBudget = BudgetCalculator.calculateTotalBudget(destinations);
        System.out.println("Total Budget for the trip: $" + totalBudget);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelItineraryPlanner planner = new TravelItineraryPlanner();
        boolean addMoreDestinations = true;

        while (addMoreDestinations) {
            System.out.print("Enter destination name: ");
            String name = scanner.nextLine();
            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();
            System.out.print("Enter end date (YYYY-MM-DD): ");
            String endDate = scanner.nextLine();
            System.out.print("Enter budget for this destination: ");
            double budget = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            Destination destination = new Destination(name, startDate, endDate, budget);
            planner.addDestination(destination);

            System.out.print("Do you want to add another destination? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                addMoreDestinations = false;
            }
        }

        planner.generateItinerary();
        scanner.close();
    }
}
