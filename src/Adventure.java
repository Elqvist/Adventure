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

    public String eat(String shortName){
        EatOutcome outcome = player.eat(shortName);

        switch (outcome.getResult()){
            case NOT_FOUND -> {
                return "There is nothing like " + shortName + " to eat around here";
            }
            case NOT_FOOD -> {
                return "You cannot eat " + outcome.getItemName() + ".";
            }
            case EATEN -> {
                String result = "You ate " + outcome.getItemName() + ".";

                if(outcome.getHealthPoints() > 0){
                    result += " You feel a little better.";
                }
                else{
                    result += " That was a mistake.";
                }

                return result;
            }
            default -> {
                return " ";
            }
        }
    }

    public String getEquipped(){
        if(player.getEquipped() == null){
            return "You currently have no weapons equipped";
        }
        else{
            return "You have the " + player.getEquipped().getShortName() + " equipped";
        }
    }

    public String getHealth(){
        if(player.getHealth() >= 100){
            return player.getHealth() + " - You are in perfect health.";
        }
        else if(player.getHealth() >= 50 && player.getHealth() < 100){
            return player.getHealth() + " - You are in good health, but avoid fighting right now.";
        }
        else if(player.getHealth() >= 25 && player.getHealth() < 50){
            return player.getHealth() + " - You are wounded - find something healthy to eat.";
        }
        else if(player.getHealth() >= 1 && player.getHealth() < 25){
            return player.getHealth() + " - You are barely alive.";
        }
        else{
            return player.getHealth() + " - You should be dead.";
        }
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

    public String equip(String shortName){
        Item item = player.findItem(shortName);

        if(item == null){
            return "You do not have " + shortName + " in your inventory";
        }

        if(!(item instanceof Weapon)){
            return "The " + shortName + " is not a weapon";
        }

        player.equip(shortName);
        return "You have equipped " + item.getLongName();

    }

    public String attack(){
        if(player.getEquipped() == null){
            return "You have nothing to attack with";
        }

        if(!player.getEquipped().canUse()){
            return "You have no more ammunition for this weapon";
        }

        player.getEquipped().use();
        return "You " + player.getEquipped().getAttackVerb() + " the monster for " + player.getEquipped().getDamage() + " HP. \n" + player.getEquipped().getUsesLeftText();
    }

    public String take(String shortName){

        if(player.getInv().size() < 5){
            Item item = player.takeItem(shortName);
            if(item != null){
                return "You picked up the " + item.getShortName();
            }
            else{
                return "There are no items like " + shortName + " in the room";
            }
        }
        else{
            return "There is not enough space in your inventory";
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
