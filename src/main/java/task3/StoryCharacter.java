package task3;

public class StoryCharacter {
    private final String name;
    private Location currentLocation;
    private Action currentAction;

    public StoryCharacter(String name) {
        this.name = name;
    }

    public void setCurrentLocation(Location location) { this.currentLocation = location; }
    public void setCurrentAction(Action action) { this.currentAction = action; }
    public String getName() { return name; }
    public Location getCurrentLocation() { return currentLocation; }
    public Action getCurrentAction() { return currentAction; }
}