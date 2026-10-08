import java.util.ArrayList;

public class GameMap {
    private Room startRoom;
    private ArrayList<Enemy> allEnemies = new ArrayList<>();
    private Room secretRoom;

    public Room getStartRoom() {
        return startRoom;
    }

    public Room getSecretRoom(){
        return secretRoom;
    }

    public void removeEnemy(Enemy enemy){
        allEnemies.remove(enemy);
    }

    public ArrayList<Enemy> getAllEnemies() {
        return allEnemies;
    }

    public boolean areAllEnemiesDead(){
        for(Enemy enemy : allEnemies){
            if(!enemy.isDead()){
                return false;
            }
        }
        return true;
    }

    public void buildMap() {

        Room room0 = new Room("Exit", "");
        room0.setLocked(true);

        Room room1 = new Room("The Entrance Hall", "A dark entrance hall with two wooden doors and a locked door to your west that seems to lead outside.");
        Item lantern = new Item("lantern", "a shiny brass lantern");
        Weapon shoehorn = new MeleeWeapon("shoehorn", "a dirty smelly shoehorn", 15);
        room1.addItem(lantern);
        room1.addItem(shoehorn);

        Room room2 = new Room("The Library", "Tall bookshelves cover the walls, filled with ancient books and forgotten secrets.");
        Item book = new Item("book", "An old dust covered book");
        Item lighter = new Item("lighter", "An antique Ronson lighter");
        Weapon ruler = new MeleeWeapon("ruler", "A wooden ruler measuring 20 cm", 10);
        Enemy librarian = new Enemy("librarian", "An old librarian", "A dust covered old lady gripping a ruler ready to strike", 30, ruler, room2);
        allEnemies.add(librarian);
        room2.addEnemy(librarian);
        room2.addItem(book);
        room2.addItem(lighter);

        Room room3 = new Room("The Kitchen", "A small, abandoned kitchen with rusty pots, broken chairs, and a cold fireplace.");
        Weapon knife = new MeleeWeapon("knife", "a small kitchen knife", 20);
        Food moldyBread = new Food("bread", "expired bread covered in mold", -100);
        room3.addItem(knife);
        room3.addItem(moldyBread);

        Room room4 = new Room("The Basement", "A cold and damp basement filled with wooden crates and strange noises in the darkness.");
        Weapon crowbar = new MeleeWeapon("crowbar", "a heavy iron crowbar", 17);
        Weapon axe = new MeleeWeapon("axe", "a rusty fire axe", 14);
        Enemy caretaker = new Enemy("caretaker", "The basement caretaker", "A broad-shouldered caretaker blocks the stairs, holding a rusty fire axe.", 65, axe, room4);
        Item rope = new Item("rope", "a long coiled rope");
        room4.addItem(crowbar);
        room4.addItem(rope);
        allEnemies.add(caretaker);
        room4.addEnemy(caretaker);

        secretRoom = new Room("The Secret Room", "A hidden room behind the bookshelf, containing a mysterious table.");
        secretRoom.setLocked(true);
        Food goldenApple = new Food("golden apple", "a glowing golden apple", 75);
        Item secretKey = new Item("key", "a small key with a strange symbol");
        secretRoom.addItem(goldenApple);
        secretRoom.addItem(secretKey);

        Room room6 = new Room("The Bedroom", "A quiet bedroom with an old bed and a cracked mirror.");
        Item locket = new Item("locket", "a silver locket with an engraved symbol");
        Food cookies = new Food("cookies", "a plate with stale cookies", 10);
        Weapon pistol = new RangedWeapon("pistol", "an old pistol from the war", 30, 5);
        room6.addItem(locket);
        room6.addItem(cookies);
        room6.addItem(pistol);

        Room room7 = new Room("The Garden", "An overgrown garden surrounded by high stone walls and strange, glowing flowers.");
        Weapon scythe = new MeleeWeapon("scythe", "a sharp gardening scythe", 16);
        Enemy gardener = new Enemy("gardener", "The deranged gardener", "A silent gardener watches the paths and grips a sharp gardening scythe.", 50, scythe, room7);
        Food apple = new Food("Apple", "a shiny red apple", 25);
        Item shovel = new Item("shovel", "a muddy garden shovel");
        room7.addItem(apple);
        room7.addItem(shovel);
        allEnemies.add(gardener);
        room7.addEnemy(gardener);

        Room room8 = new Room("The Laboratory", "A dusty laboratory filled with strange bottles, old notes, and mysterious equipment.");
        Weapon cleaver = new MeleeWeapon("cleaver", "a stained surgical cleaver", 18);
        Enemy surgeon = new Enemy("surgeon", "The mad surgeon", "A mad surgeon stands by the workbench, raising a stained surgical cleaver.", 75, cleaver, room8);
        Food redShroom = new Food("red mushroom", "a small red mushroom with white spots", 50);
        Food blackShroom = new Food("blue mushroom", "a small black mushroom with a skull on it", -50);
        room8.addItem(redShroom);
        room8.addItem(blackShroom);
        allEnemies.add(surgeon);
        room8.addEnemy(surgeon);

        Room room9 = new Room("The Tower", "A narrow stone tower with a spiral staircase leading to a small room overlooking the land.");
        Item lens = new Item("lens", "a polished telescope lens");
        Item compass = new Item("compass", "a small golden compass");
        room9.addItem(lens);
        room9.addItem(compass);

        startRoom = room1;

        room1.setWest(room0);
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setWest(room1);
        room2.setEast(room3);
        room2.setSouth(secretRoom);
        room3.setWest(room2);
        room3.setSouth(room6);
        room4.setNorth(room1);
        room4.setSouth(room7);
        secretRoom.setNorth(room2);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room7.setNorth(room4);
        room7.setEast(room8);
        room8.setWest(room7);
        room8.setEast(room9);
        room9.setNorth(room6);
        room9.setWest(room8);
    }

}
