package task3;

public class Item {
    private final String name;
    private final String property;
    private final int energyCost; // Стоимость энергии для использования
    private boolean isUsed;

    public Item(String name, String property, int energyCost) {
        this.name = name;
        this.property = property;
        this.energyCost = energyCost;
        this.isUsed = false;
    }

    public String getName() { return name; }
    public String getProperty() { return property; }
    public int getEnergyCost() { return energyCost; }

    public void useItem() {
        if (!isUsed) {
            isUsed = true;
        } else {
            throw new IllegalStateException(name + " уже был использован.");
        }
    }

    public boolean isUsed() {
        return isUsed;
    }
}