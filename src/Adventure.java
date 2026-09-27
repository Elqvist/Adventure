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

    public String look(){
        return "You are in " + player.getCurrentRoom().getName() + "\n" + player.getCurrentRoom().getDescription();
    }


}
