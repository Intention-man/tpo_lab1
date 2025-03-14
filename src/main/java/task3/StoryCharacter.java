package task3;

import java.util.List;

public class StoryCharacter {
    private final String name;
    private Location currentLocation;
    private Action currentAction;
    private int energy;

    public StoryCharacter(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    public StoryCharacter(String name) {
        this.name = name;
        this.energy = 10;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
        location.visit(this); // Передаем текущего персонажа для учета уникальных посетителей
    }

    public void setCurrentAction(Action action) { this.currentAction = action; }
    public String getName() { return name; }
    public Location getCurrentLocation() { return currentLocation; }
    public Action getCurrentAction() { return currentAction; }
    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy; }

    public boolean moveTo(Location newLocation) {
        if (newLocation.canAccess(this)) {
            currentLocation = newLocation;
            newLocation.visit(this);
            return true;
        } else {
            throw new IllegalStateException(name + " не может переместиться в " + newLocation.getName());
        }
    }

    public Item chooseActionBasedOnItems(List<Item> availableItems) {
        for (Item item : availableItems) {
            if (energy >= item.getEnergyCost()) {
                this.currentAction = new Action("использовать " + item.getName(), item);
                System.out.println(name + " выбрал действие: " + this.currentAction.getType());
                return item;
            }
        }
        throw new IllegalStateException(name + " не может выбрать действие из-за нехватки энергии или доступных предметов.");
    }
}