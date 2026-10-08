public class Enemy {
    private final String shortName;
    private final String longName;
    private final String description;
    private int health;
    private final Weapon weapon;
    private final Room room;

    public Enemy(String shortName, String longName, String description, int health, Weapon weapon, Room room) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.room = room;
    }

    public boolean isDead(){
        return this.health <= 0;
    }

    public void hit(int damage) {
        this.health -= damage;
    }

    public int attack(int health) {
        return health -= this.weapon.getDamage();
    }

    public void die() {
        room.addItem(this.getWeapon());
        room.removeEnemy(this);
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
