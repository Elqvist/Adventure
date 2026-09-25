public class Player {

    private Room currentRoom;

    public Player(Room startRoom){
        currentRoom = startRoom;
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

}
