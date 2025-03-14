package task3;

import java.util.HashSet;
import java.util.Set;

public class Location {
    private final String name;
    private final Set<String> requiredActions = new HashSet<>();
    private final Set<String> uniqueVisitors = new HashSet<>(); // Уникальные посетители

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addAccessRequirement(String actionType) {
        requiredActions.add(actionType);
    }

    public boolean canAccess(StoryCharacter character) {
        Action action = character.getCurrentAction();
        return action != null && requiredActions.contains(action.getType()) && action.isDone();
    }

    public void visit(StoryCharacter character) {
        uniqueVisitors.add(character.getName());
    }

    public int getUniqueVisitorCount() {
        return uniqueVisitors.size();
    }
}