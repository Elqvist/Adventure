public class EatOutcome {
    private final EatResult result;
    private final String itemName;
    private final int healthPoints;

    public EatOutcome(EatResult result, String itemName, int healthPoints) {
        this.result = result;
        this.itemName = itemName;
        this.healthPoints = healthPoints;
    }

    public EatResult getResult() {
        return result;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
