public class GameMap {
    private Room startRoom;

    public Room getStartRoom() {
        return startRoom;
    }

    public GameMap buildMap;{
        Room room1 = new Room("The Entrance Hall", "A dark entrance hall with two wooden doors and dusty paintings on the walls.");
        Room room2 = new Room("The Library", "Tall bookshelves cover the walls, filled with ancient books and forgotten secrets.");
        Room room3 = new Room("The Kitchen", "A small, abandoned kitchen with rusty pots, broken chairs, and a cold fireplace.");
        Room room4 = new Room("The Basement", "A cold and damp basement filled with wooden crates and strange noises in the darkness.");
        Room room5 = new Room("The Secret Room", "A hidden room behind a bookshelf, containing a mysterious table.");
        Room room6 = new Room("The Bedroom", "A quiet bedroom with an old bed and a cracked mirror.");
        Room room7 = new Room("The Garden", "An overgrown garden surrounded by high stone walls and strange, glowing flowers.");
        Room room8 = new Room("The Laboratory", "A dusty laboratory filled with strange bottles, old notes, and mysterious equipment.");
        Room room9 = new Room("The Tower", "A narrow stone tower with a spiral staircase leading to a small room overlooking the land.");

        startRoom = room1;


        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setWest(room1);
        room2.setEast(room3);
        room3.setWest(room2);
        room3.setSouth(room6);
        room4.setNorth(room1);
        room4.setSouth(room7);
        room5.setSouth(room8);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room7.setNorth(room4);
        room7.setEast(room8);
        room8.setWest(room7);
        room8.setEast(room9);
        room8.setNorth(room5);
        room9.setNorth(room6);
        room9.setWest(room8);
    }

}
