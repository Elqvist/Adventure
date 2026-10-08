import java.util.ArrayList;

public class Room {
    private final String name;
    private final String description;

    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private boolean isLocked = false;

    private final ArrayList<Item> items;
    private final ArrayList<Enemy> enemies;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public Item findItem(String shortName) {
        for (Item item : items) {

            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }

        return null;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public Enemy findEnemy(String shortName) {
        for (Enemy enemy : enemies) {

            if (enemy.getShortName().equalsIgnoreCase(shortName)) {
                return enemy;
            }
        }

        return null;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }


}
