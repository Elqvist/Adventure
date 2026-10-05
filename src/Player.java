import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health = 100;
    private Weapon equipped;

    public Player(Room startRoom){
        currentRoom = startRoom;
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getHealth() {
        return health;
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
        if (!(item instanceof Food)) {
            return new EatOutcome(EatResult.NOT_FOOD, item.getLongName(), 0);
        }

        Food food = (Food) item;
        health += food.getHealthPoints();
        removeItem(food);
        currentRoom.removeItem(food);
        return new EatOutcome(EatResult.EATEN, food.getLongName(), food.getHealthPoints());
    }

    public void equip(String shortName){
        Item item = findItem(shortName);
        if (item instanceof Weapon weapon) {
            equipped = weapon;
        }
    }


    public boolean move(String direction){
        Room desiredRoom = switch (direction){
            case "north" -> currentRoom.getNorth();
            case "east" -> currentRoom.getEast();
            case "south" -> currentRoom.getSouth();
            case "west" -> currentRoom.getWest();
            default -> null;
        };

        if(desiredRoom != null){
            currentRoom = desiredRoom;
            return true;
        }
        else{
            return false;
        }
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    public ArrayList<Item> getInv(){
        return inventory;
    }

    public Item takeItem(String shortName){
        Item desiredItem = currentRoom.findItem(shortName);
        if(desiredItem != null){
            addItem(desiredItem);
            currentRoom.removeItem(desiredItem);
        }

        return desiredItem;

    }

    public Item dropItem(String shortName){
        Item desiredItem = null;
            for(Item item : inventory){
                if(item.getShortName().equalsIgnoreCase(shortName)){
                    currentRoom.addItem(item);
                    desiredItem = item;
                    if(equipped == desiredItem){
                        equipped = null;
                    }
                }
            }
            removeItem(desiredItem);
            return desiredItem;
    }

}
