import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;

    public Player(Room startRoom){
        currentRoom = startRoom;
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
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
        if(inventory != null){
            for(Item item : inventory){
                if(item.getShortName().equalsIgnoreCase(shortName)){
                    currentRoom.addItem(item);
                    desiredItem = item;
                }
            }
            removeItem(desiredItem);
        }

        return desiredItem;

    }

}
