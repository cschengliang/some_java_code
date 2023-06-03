package refactoring_guru.builder.example.components;

public class GPSNavigator {
    private final String route;

    public GPSNavigator() {
        this.route = "221b, Baker Street, London to Scotland Yard, 8-10 Broadway, London";
    }

    public GPSNavigator(String manualRouter) {
        this.route = manualRouter;
    }

    public String getRoute() {
        return route;
    }
}
