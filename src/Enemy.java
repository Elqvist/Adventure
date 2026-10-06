public class Enemy {
    private String shortName;
    private String longName;
    private String description;
    private int health;
    private Weapon weapon;
    private Room room;

    public Enemy(String shortName, String longName, String description, int health, Weapon weapon) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
    }

    public void hit(int damage){
        this.health -= damage;
    }

    public String getShortName() {
        return shortName;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }
}
