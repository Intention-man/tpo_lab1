package task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spaceship {
    private final String name;
    private final List<StoryCharacter> crew = new ArrayList<>();
    private final Map<String, Location> locations = new HashMap<>();
    private final int capacity;
    private int size;

    public Spaceship(String name, int capacity) {
        this.name = name;
        this.size = 0;
        this.capacity = capacity;
        if (name.contains("Золотое")){
            locations.put("мостик", new Location("мостик"));
            locations.put("каюта", new Location("каюта"));
        } else {
            locations.put("борт", new Location("борт"));
        }
    }

    public String getName() { return name; }
    public List<StoryCharacter> getCrew() { return crew; }
    public Location getLocation(String name) { return locations.get(name); }

    public boolean addPerson(StoryCharacter person){
        if (size < capacity){
            crew.add(person);
            size++;
            return true;
        }
        return false;
    }
}