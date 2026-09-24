public class Player {

    private Room currentRoom;

    public Player(Room startRoom){
        currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void action(String decision){
        switch(decision.toLowerCase()){
            case "go north", "north" -> move("north");
            case "go south", "south" -> move("south");
            case "go west", "west" -> move("west");
            case "go east", "east" -> move("east");
            case "look", "look around" -> System.out.println("You are in " + currentRoom.getName() + ".\n" + currentRoom.getDescription() + "\n");
            default -> System.out.println("Command not found! Write help for instructions.");
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

}
