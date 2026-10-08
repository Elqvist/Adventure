public class RangedWeapon extends Weapon {
    private int ammunition;

    public RangedWeapon(String shortName, String longName, int damage, int ammunition) {
        super(shortName, longName, damage);
        this.ammunition = ammunition;
    }

    @Override
    public String getAttackVerb() {
        return "shot";
    }

    @Override
    public String getUsesLeftText() {
        return "You have " + ammunition + " bullets left";
    }

    @Override
    public boolean canUse() {
        return ammunition > 0;
    }

    @Override
    public void use() {
        ammunition--;
    }
}
