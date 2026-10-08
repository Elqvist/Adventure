public class MeleeWeapon extends Weapon {
    public MeleeWeapon(String shortName, String longName, int damage) {
        super(shortName, longName, damage);
    }

    @Override
    public boolean canUse() {
        return true;                    // et sværd løber aldrig tør
    }

    @Override
    public String getUsesLeftText() {
        return "";
    }

    @Override
    public String getAttackVerb() {
        return "attacked";
    }

    @Override
    public void use() {
        // ingenting – sværdet slides ikke
    }
}
