package task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spaceship {
    private final String name;
    private final List<StoryCharacter> crew = new ArrayList<>();
    private final Map<String, Location> locations = new HashMap<>();

    public Spaceship(String name) {
        this.name = name;
        locations.put("мостик", new Location("мостик"));
        locations.put("каюта", new Location("каюта"));
    }

    public String getName() { return name; }
    public List<StoryCharacter> getCrew() { return crew; }
    public Location getLocation(String name) { return locations.get(name); }
}