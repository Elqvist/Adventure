public class GameMap {
    private Room startRoom;

    public Room getStartRoom() {
        return startRoom;
    }

    public void buildMap() {
        Room room1 = new Room("The Entrance Hall", "A dark entrance hall with two wooden doors and dusty paintings on the walls.");
        Item lantern = new Item("lantern", "a shiny brass lantern");
        Item key = new Item("key", "an old rusty key");
        room1.addItem(lantern);
        room1.addItem(key);

        Room room2 = new Room("The Library", "Tall bookshelves cover the walls, filled with ancient books and forgotten secrets.");
        Item book = new Item("book", "An old dust covered book");
        Item lighter = new Item("lighter", "An antique Ronson lighter");
        room2.addItem(book);
        room2.addItem(lighter);

        Room room3 = new Room("The Kitchen", "A small, abandoned kitchen with rusty pots, broken chairs, and a cold fireplace.");
        Item knife = new Item("knife", "a small kitchen knife");
        Food moldyBread = new Food("bread", "expired bread covered in mold", -25);
        room3.addItem(knife);
        room3.addItem(moldyBread);

        Room room4 = new Room("The Basement", "A cold and damp basement filled with wooden crates and strange noises in the darkness.");
        Item crowbar = new Item("crowbar", "a heavy iron crowbar");
        Item rope = new Item("rope", "a long coiled rope");
        room4.addItem(crowbar);
        room4.addItem(rope);

        Room room5 = new Room("The Secret Room", "A hidden room behind a bookshelf, containing a mysterious table.");
        Food goldenApple = new Food("golden apple", "a glowing golden apple", 50);
        Item secretKey = new Item("secret key", "a small key with a strange symbol");
        room5.addItem(goldenApple);
        room5.addItem(secretKey);

        Room room6 = new Room("The Bedroom", "A quiet bedroom with an old bed and a cracked mirror.");
        Item locket = new Item("locket", "a silver locket with an engraved symbol");
        Food cookies = new Food("cookies", "a plate with stale cookies", 10);
        room6.addItem(locket);
        room6.addItem(cookies);

        Room room7 = new Room("The Garden", "An overgrown garden surrounded by high stone walls and strange, glowing flowers.");
        Food apple = new Food("Apple", "a shiny red apple", 25);
        Item shovel = new Item("shovel", "a muddy garden shovel");
        room7.addItem(apple);
        room7.addItem(shovel);

        Room room8 = new Room("The Laboratory", "A dusty laboratory filled with strange bottles, old notes, and mysterious equipment.");
        Food redShroom = new Food("red mushroom", "a small red mushroom with white spots", 50);
        Food blackShroom = new Food("blue mushroom", "a small black mushroom with a skull on it", -50);
        room8.addItem(redShroom);
        room8.addItem(blackShroom);

        Room room9 = new Room("The Tower", "A narrow stone tower with a spiral staircase leading to a small room overlooking the land.");
        Item lens = new Item("lens", "a polished telescope lens");
        Item compass = new Item("compass", "a small golden compass");
        room9.addItem(lens);
        room9.addItem(compass);

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
