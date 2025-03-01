import org.junit.jupiter.api.Test;
import task3.Action;
import task3.Item;
import task3.Spaceship;
import task3.StoryCharacter;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceshipTest {

    @Test
    void testSpaceshipInitialization() {
        Spaceship ship = new Spaceship("Золотое Сердце");
        assertEquals("Золотое Сердце", ship.getName());
        assertNotNull(ship.getLocation("мостик"));
        assertNotNull(ship.getLocation("каюта"));
    }

    @Test
    void testStoryCharacterActions() {
        Spaceship ship = new Spaceship("Золотое Сердце");

        // Создание персонажей
        StoryCharacter zaphod = new StoryCharacter("Зафод");
        StoryCharacter arthur = new StoryCharacter("Артур");
        ship.getCrew().add(zaphod);
        ship.getCrew().add(arthur);

        // Назначение локаций и действий
        zaphod.setCurrentLocation(ship.getLocation("мостик"));
        arthur.setCurrentLocation(ship.getLocation("каюта"));

        Item blast = new Item("пангалактический бульк-бластер", "алкогольный напиток");
        zaphod.setCurrentAction(new Action("пить", blast));

        Item guide = new Item("Путеводитель по Галактике", "электронная книга");
        arthur.setCurrentAction(new Action("читать", guide));

        // Проверки для Зафода
        assertEquals("Зафод", zaphod.getName());
        assertEquals("мостик", zaphod.getCurrentLocation().getName());
        assertEquals("пить", zaphod.getCurrentAction().getType());
        assertEquals("алкогольный напиток", zaphod.getCurrentAction().getItem().getDescription());

        // Проверки для Артура
        assertEquals("каюта", arthur.getCurrentLocation().getName());
        assertEquals("электронная книга", arthur.getCurrentAction().getItem().getDescription());
    }

    @Test
    void testItemCreation() {
        Item item = new Item("Книга", "бумажная");
        assertEquals("Книга", item.getName());
        assertEquals("бумажная", item.getDescription());
    }
}