package task3;

public class Action {
    private final String type;
    private final Item item;
    private boolean done;

    public Action(String type, Item item) {
        this.type = type;
        this.item = item;
        this.done = false;
    }

    public String getType() { return type; }
    public Item getItem() { return item; }

    public void completeAction(StoryCharacter hero) {
        if (hero.getEnergy() >= item.getEnergyCost()) {
            hero.setEnergy(hero.getEnergy() - item.getEnergyCost());
            this.done = true;
        } else {
            throw new IllegalStateException("Недостаточно энергии для выполнения действия.");
        }
    }

    public void completeAction() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }
}