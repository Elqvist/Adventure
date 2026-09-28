public class Adventure {

    private GameMap gameMap;
    private Player player;

    public Adventure() {
        gameMap = new GameMap();
        gameMap.buildMap();
        player = new Player(gameMap.getStartRoom());
    }

    public boolean go(String direction){
        return player.move(direction);
    }

    public String printInv(){
        String result = "\nInventory: ";

        if(player.getInv().isEmpty()){
            result = "\nThere are no items in your inventory";
        }

        for(Item item : player.getInv()){
            result += "\n- " + item.getLongName();
        }

        return result;
    }

    public String look(){
        String result = "You are in " + player.getCurrentRoom().getName() + "\n"
                + player.getCurrentRoom().getDescription();

        if(player.getCurrentRoom().getItems().isEmpty()){
            result += "\nThere are no items in this room.";
        }
        else{
            result += "\nItems:";
        }

        for (Item item : player.getCurrentRoom().getItems()) {
            result += "\n- " + item.getLongName();
        }

        return result;
    }

    public String take(String shortName){
        Item item = player.takeItem(shortName);

        if(item != null){
            return "You picked up the " + item.getShortName();
        }
        else{
            return "There are no items like " + shortName + " in the room";
        }

    }

    public String drop(String shortName){
        Item item = player.dropItem(shortName);

        if(item != null){
            return "You dropped the " + item.getShortName();
        }
        else{
            return "There are no items like " + shortName + " in your inventory";
        }

    }

}
