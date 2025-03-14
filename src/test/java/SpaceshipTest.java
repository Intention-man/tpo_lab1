import org.junit.jupiter.api.Test;
import task3.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceshipTest {

    @Test
    void testSpaceshipInitialization() {
        Spaceship ship = new Spaceship("Золотое Сердце", 1);
        assertEquals("Золотое Сердце", ship.getName());
        assertNotNull(ship.getLocation("мостик"));
        assertNotNull(ship.getLocation("каюта"));

        Spaceship ship2 = new Spaceship("Крейсер", 1);
        assertEquals("Крейсер", ship2.getName());
        assertNotNull(ship2.getLocation("борт"));
    }

    @Test
    void testStoryCharacterActions() {
        Spaceship ship = new Spaceship("Золотое Сердце", 2);

        StoryCharacter zaphod = new StoryCharacter("Зафод", 10);
        StoryCharacter arthur = new StoryCharacter("Артур", 10);
        StoryCharacter smb = new StoryCharacter("Кто-то", 10);
        assertTrue(ship.addPerson(zaphod));
        assertTrue(ship.addPerson(arthur));
        assertFalse(ship.addPerson(smb));

        assertTrue(ship.getCrew().contains(zaphod));
        assertTrue(ship.getCrew().contains(arthur));
        assertFalse(ship.getCrew().contains(smb));

        zaphod.setCurrentLocation(ship.getLocation("мостик"));
        arthur.setCurrentLocation(ship.getLocation("каюта"));

        Item blast = new Item("пангалактический бульк-бластер", "алкогольный напиток", 1);
        Action zaphodAction = new Action("пить", blast);
        zaphod.setCurrentAction(zaphodAction);

        Item guide = new Item("Путеводитель по Галактике", "электронная книга", 1);
        Action arthurAction = new Action("читать", guide);
        arthur.setCurrentAction(arthurAction);

        assertEquals("Зафод", zaphod.getName());
        assertEquals("мостик", zaphod.getCurrentLocation().getName());
        assertEquals("пить", zaphod.getCurrentAction().getType());
        assertEquals("алкогольный напиток", zaphod.getCurrentAction().getItem().getProperty());

        assertFalse(zaphodAction.isDone());
        zaphodAction.completeAction();
        assertTrue(zaphodAction.isDone());

        assertEquals("каюта", arthur.getCurrentLocation().getName());
        assertEquals("электронная книга", arthur.getCurrentAction().getItem().getProperty());

        assertFalse(arthurAction.isDone());
        arthurAction.completeAction();
        assertTrue(arthurAction.isDone());
    }

    @Test
    void testItemCreation() {
        Item item = new Item("Книга", "бумажная", 1);
        assertEquals("Книга", item.getName());
        assertEquals("бумажная", item.getProperty());
//        assertEquals("Книга: бумажная", item.getDescription());
    }

    @Test
    void testItemUsage() {
        Item item = new Item("Книга", "бумажная", 1);
        assertFalse(item.isUsed());
        item.useItem();
        assertTrue(item.isUsed());
        assertThrows(IllegalStateException.class, item::useItem);
    }

    @Test
    void testFullLocationAccessConditions() {
        StoryCharacter ford = new StoryCharacter("Форд", 10);
        Location secretRoom = new Location("секретная комната");

        secretRoom.addAccessRequirement("открыть дверь");

        ford.setCurrentAction(null);
        assertFalse(secretRoom.canAccess(ford));

        Action unrelatedAction = new Action("прыгать", null);
        ford.setCurrentAction(unrelatedAction);
        assertFalse(secretRoom.canAccess(ford));

        Action unlockAction = new Action("открыть дверь", null);
        ford.setCurrentAction(unlockAction);
        assertFalse(secretRoom.canAccess(ford));

        unlockAction.completeAction();
        assertTrue(secretRoom.canAccess(ford));
    }

    @Test
    void testCharacterMovement() {
        StoryCharacter zaphod = new StoryCharacter("Зафод");
        Location bridge = new Location("мостик");
        Location secretRoom = new Location("секретная комната");
        Action hack = new Action("взломать", null);

        secretRoom.addAccessRequirement("взломать");

        zaphod.setCurrentLocation(bridge);
        zaphod.setCurrentAction(hack);

        assertThrows(IllegalStateException.class, () -> zaphod.moveTo(secretRoom));

        hack.completeAction();
        assertTrue(zaphod.moveTo(secretRoom));
    }


    @Test
    void testEnergyConsumptionAndActionChoice() {
        Item sword = new Item("Меч", "острый", 20);
        Item potion = new Item("Зелье", "восстановление", 15);

        StoryCharacter hero = new StoryCharacter("Герой", 25);

        hero.chooseActionBasedOnItems(Arrays.asList(sword, potion));

        assertEquals("использовать Меч", hero.getCurrentAction().getType());

        hero.getCurrentAction().completeAction(hero);

        assertThrows(IllegalStateException.class, () -> {
            Action fight = new Action("использовать Зелье", potion);
            fight.completeAction(hero);
        });

        assertThrows(IllegalStateException.class, () -> {
            hero.chooseActionBasedOnItems(Arrays.asList(sword, potion));
        });


        assertEquals(5, hero.getEnergy());
    }

    @Test
    void testLocationUniqueVisitors() {
        StoryCharacter hero1 = new StoryCharacter("Герой1", 50);
        StoryCharacter hero2 = new StoryCharacter("Герой2", 40);
        StoryCharacter hero3 = new StoryCharacter("Герой3", 30);

        Location temple = new Location("Храм");

        assertEquals(0, temple.getUniqueVisitorCount());

        hero1.setCurrentLocation(temple);
        hero2.setCurrentLocation(temple);
        hero3.setCurrentLocation(temple);

        assertEquals(3, temple.getUniqueVisitorCount());

        hero1.setCurrentLocation(temple);
        hero2.setCurrentLocation(temple);

        assertEquals(3, temple.getUniqueVisitorCount());
    }

    @Test
    void testAccessWithEnergyAndActionCompletion() {
        StoryCharacter thief = new StoryCharacter("Вор", 5);
        Location vault = new Location("Сокровищница");
        Action pickLock = new Action("взломать замок", new Item("Отмычка", "инструмент", 5));

        vault.addAccessRequirement("взломать замок");
        thief.setCurrentAction(pickLock);

        assertFalse(vault.canAccess(thief));

        pickLock.completeAction(thief);
        assertTrue(vault.canAccess(thief));

        assertTrue(thief.moveTo(vault));
        assertEquals(thief.getCurrentLocation(), vault);
    }
}