package task3;

public class Action {
    private String type;
    private Item item;

    public Action(String type, Item item) {
        this.type = type;
        this.item = item;
    }

    public String getType() { return type; }
    public Item getItem() { return item; }
}