import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private final ArrayList<Item> inventory;
    private int health = 100;
    private Weapon equipped;
    private String lastEnemyKilledName;

    public Player(Room startRoom) {
        currentRoom = startRoom;
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getHealth() {
        return health;
    }

    public String getLastEnemyKilledName() {
        return lastEnemyKilledName;
    }

    public Weapon getEquipped() {
        return equipped;
    }

    public Item findItem(String shortName) {
        for (Item item : inventory) {

            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }

    public EatOutcome eat(String shortName) {
        Item item = findItem(shortName); //Checker inventory
        if (item == null) {
            item = currentRoom.findItem(shortName); //Checker room
        }

        if (item == null) {
            return new EatOutcome(EatResult.NOT_FOUND, null, 0);
        }
        if (!(item instanceof Food food)) {
            return new EatOutcome(EatResult.NOT_FOOD, item.getLongName(), 0);
        }

        health += food.getHealthPoints();
        removeItem(food);
        currentRoom.removeItem(food);
        return new EatOutcome(EatResult.EATEN, food.getLongName(), food.getHealthPoints());
    }

    public void equip(String shortName) {
        Item item = findItem(shortName);
        if (item instanceof Weapon weapon) {
            equipped = weapon;
        }
    }

    public AttackResult attack(String shortName) {
        Enemy enemy = currentRoom.findEnemy(shortName);
        if (enemy == null) {
            return AttackResult.NO_ENEMY;
        }
        if (equipped == null) {
            return AttackResult.NO_WEAPON;
        }
        if (!equipped.canUse()) {
            return AttackResult.WEAPON_EMPTY;
        }

        enemy.hit(equipped.getDamage());
        equipped.use();

        if (enemy.getHealth() <= 0) {
            lastEnemyKilledName = enemy.getShortName();
            enemy.die(enemy);
            return AttackResult.ENEMY_DIED;
        }

        health = enemy.attack(health);

        if (health <= 0) {
            return AttackResult.PLAYER_DIED;
        }

        return AttackResult.ENEMY_HIT;
    }

    public AttackResult attack() {
        Enemy enemy = null;
        if (!(currentRoom.getEnemies().isEmpty())) {
            enemy = currentRoom.getEnemies().getFirst();
        }
        if (enemy == null) {
            return AttackResult.NO_ENEMY;
        }
        if (equipped == null) {
            return AttackResult.NO_WEAPON;
        }
        if (!equipped.canUse()) {
            return AttackResult.WEAPON_EMPTY;
        }

        enemy.hit(equipped.getDamage());
        equipped.use();

        if (enemy.getHealth() <= 0) {
            lastEnemyKilledName = enemy.getShortName();
            enemy.die(enemy);
            return AttackResult.ENEMY_DIED;
        }

        health = enemy.attack(health);

        if (health <= 0) {
            return AttackResult.PLAYER_DIED;
        }

        return AttackResult.ENEMY_HIT;
    }

    public RoomResult move(String direction) {
        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "east" -> currentRoom.getEast();
            case "south" -> currentRoom.getSouth();
            case "west" -> currentRoom.getWest();
            default -> null;
        };

        if(desiredRoom.getName().equalsIgnoreCase("exit") && !desiredRoom.isLocked()){
            return RoomResult.EXIT;
        } else if(desiredRoom != null && !desiredRoom.isLocked()) {
            currentRoom = desiredRoom;
            return RoomResult.OPEN;
        } else if(desiredRoom != null && desiredRoom.isLocked()){
            if(findItem("key") != null){
                removeItem(findItem("key"));
                desiredRoom.setLocked(false);
                return RoomResult.UNLOCKED;
            }
            return RoomResult.LOCKED;
        } else {
            return RoomResult.CANNOT;
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public ArrayList<Item> getInv() {
        return inventory;
    }

    public Item takeItem(String shortName) {
        Item desiredItem = currentRoom.findItem(shortName);
        if (desiredItem != null) {
            addItem(desiredItem);
            currentRoom.removeItem(desiredItem);
        }

        return desiredItem;

    }

    public Item dropItem(String shortName) {
        Item desiredItem = null;
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                currentRoom.addItem(item);
                desiredItem = item;
                if (equipped == desiredItem) {
                    equipped = null;
                }
            }
        }
        removeItem(desiredItem);
        return desiredItem;
    }

}
