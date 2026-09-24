public class Adventure {

    private GameMap gameMap = new GameMap();
    private Player player = new Player(gameMap.getStartRoom());

    public boolean go(String direction){
        return player.move(direction);
    }

    public String look(){
        return "You are in " + player.getCurrentRoom().getName() + "\n" + player.getCurrentRoom().getDescription();
    }


}
