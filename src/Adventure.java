public class Adventure {

    private final GameMap gameMap;
    private final Player player;
    private Boolean gameRunning = true;


    public Adventure() {
        gameMap = new GameMap();
        gameMap.buildMap();
        player = new Player(gameMap.getStartRoom());
    }

    public Boolean getGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(Boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public int getPlayerHealth() {
        return player.getHealth();
    }

    public String go(String direction) {

        switch(player.move(direction)){
            case OPEN -> {
                return look();
            }
            case LOCKED -> {
                return "The door seems to be locked. You might need a key to open it";
            }
            case UNLOCKED -> {
                return "The key turned and it seems that the door is now unlocked";
            }
            case CANNOT -> {
                return "You can't go this way";
            }
            case EXIT -> {
                gameRunning = false;
                return "Congratulations! You won the game";
            }
        }
        return null;
    }

    public String printInv() {
        String result = "\nInventory: ";

        if (player.getInv().isEmpty()) {
            result = "\nThere are no items in your inventory";
        }

        for (Item item : player.getInv()) {
            result += "\n- " + item.getLongName();
        }

        if (player.getEquipped() == null) {
            result += "\nYou currently have no weapons equipped";
        } else {
            result += "\nYou have the " + player.getEquipped().getShortName() + " equipped";
        }

        return result;
    }

    public String eat(String shortName) {
        EatOutcome outcome = player.eat(shortName);

        switch (outcome.getResult()) {
            case NOT_FOUND -> {
                return "There is nothing like " + shortName + " to eat around here";
            }
            case NOT_FOOD -> {
                return "You cannot eat " + outcome.getItemName() + ".";
            }
            case EATEN -> {
                String result = "You ate " + outcome.getItemName() + ".";

                if (outcome.getHealthPoints() > 0) {
                    result += " You feel a little better.";
                } else {
                    result += " That was a mistake.";
                }

                return result;
            }
            default -> {
                return " ";
            }
        }
    }

    public String getEquipped() {
        if (player.getEquipped() == null) {
            return "You currently have no weapons equipped";
        } else {
            return "You have the " + player.getEquipped().getShortName() + " equipped";
        }
    }

    public String getHealth() {
        if (player.getHealth() >= 100) {
            return player.getHealth() + " - You are in perfect health.";
        } else if (player.getHealth() >= 50 && player.getHealth() < 100) {
            return player.getHealth() + " - You are in good health, but avoid fighting right now.";
        } else if (player.getHealth() >= 25 && player.getHealth() < 50) {
            return player.getHealth() + " - You are wounded - find something healthy to eat.";
        } else if (player.getHealth() >= 1 && player.getHealth() < 25) {
            return player.getHealth() + " - You are barely alive.";
        } else {
            return player.getHealth() + " - You should be dead.";
        }
    }

    public String look() {
        String result = "You are in " + player.getCurrentRoom().getName() + "\n"
                + player.getCurrentRoom().getDescription();

        if (player.getCurrentRoom().getItems().isEmpty()) {
            result += "\nThere are no items in this room.";
        } else {
            result += "\nItems:";
        }

        for (Item item : player.getCurrentRoom().getItems()) {
            result += "\n- " + item.getLongName();
        }

        if (!(player.getCurrentRoom().getEnemies().isEmpty())) {
            for (Enemy enemy : player.getCurrentRoom().getEnemies()) {
                result += "\n\nBeware! " + enemy.getLongName() + " with " + enemy.getHealth() + " HP" + "\n" + enemy.getDescription();
            }
        } else {
            result += "\n\nThere are no enemies around";
        }

        return result;
    }

    public String equip(String shortName) {
        Item item = player.findItem(shortName);

        if (item == null) {
            return "You do not have " + shortName + " in your inventory";
        }

        if (!(item instanceof Weapon)) {
            return "The " + shortName + " is not a weapon";
        }

        player.equip(shortName);
        return "You have equipped " + item.getLongName();

    }

    public String attack(String shortName) {

        switch (player.attack(shortName)) {
            case NO_WEAPON -> {
                return "You have nothing to attack with";
            }
            case WEAPON_EMPTY -> {
                return "You have no more ammunition for this weapon";
            }
            case NO_ENEMY -> {
                return "There are no enemies to attack in this room";
            }
            case ENEMY_HIT -> {
                Enemy enemy = player.getCurrentRoom().findEnemy(shortName);

                String result = "";
                result += "You " + player.getEquipped().getAttackVerb() + " the " + enemy.getShortName() + " for " + player.getEquipped().getDamage() + " HP. " + player.getEquipped().getUsesLeftText();
                result += "\nThe " + enemy.getShortName() + " " + enemy.getWeapon().getAttackVerb() + " you for " + enemy.getWeapon().getDamage() + " HP.";
                result += "\n\nYou have " + player.getHealth() + " HP remaining";
                result += "\nThe " + enemy.getShortName() + " has " + enemy.getHealth() + " HP remaining";
                return result;
            }
            case ENEMY_DIED -> {
                return "You killed the " + player.getLastEnemyKilledName() + ". Take a look around, they might have dropped something";
            }
            case PLAYER_DIED -> {
                gameRunning = false;
                return "You died. Try again";
            }
        }

        if (player.getEquipped() == null) {
            return "You have nothing to attack with";
        }

        if (!player.getEquipped().canUse()) {
            return "You have no more ammunition for this weapon";
        }

        player.getEquipped().use();
        return "You " + player.getEquipped().getAttackVerb() + " the monster for " + player.getEquipped().getDamage() + " HP. \n" + player.getEquipped().getUsesLeftText();
    }

    public String attack() {

        Enemy enemy = null;
        if (!(player.getCurrentRoom().getEnemies().isEmpty())) {
            enemy = player.getCurrentRoom().getEnemies().getFirst();
        }

        switch (player.attack()) {
            case NO_WEAPON -> {
                return "You have nothing to attack with";
            }
            case WEAPON_EMPTY -> {
                return "You have no more ammunition for this weapon";
            }
            case NO_ENEMY -> {
                return "There are no enemies to attack in this room";
            }
            case ENEMY_HIT -> {

                String result = "";
                result += "\nYou " + player.getEquipped().getAttackVerb() + " the " + enemy.getShortName() + " for " + player.getEquipped().getDamage() + " HP. " + player.getEquipped().getUsesLeftText();
                result += "\nThe " + enemy.getShortName() + " " + enemy.getWeapon().getAttackVerb() + " you for " + enemy.getWeapon().getDamage() + " HP.";
                result += "\n\nYou have " + getPlayerHealth() + " HP remaining";
                result += "\nThe " + enemy.getShortName() + " has " + enemy.getHealth() + " HP remaining";
                return result;
            }
            case ENEMY_DIED -> {
                return "You killed the " + player.getLastEnemyKilledName() + ". Take a look around, they might have dropped something";
            }
            case PLAYER_DIED -> {
                gameRunning = false;
                return "You died. Try again";
            }
        }

        if (player.getEquipped() == null) {
            return "You have nothing to attack with";
        }

        if (!player.getEquipped().canUse()) {
            return "You have no more ammunition for this weapon";
        }

        player.getEquipped().use();
        return "You " + player.getEquipped().getAttackVerb() + " the monster for " + player.getEquipped().getDamage() + " HP. \n" + player.getEquipped().getUsesLeftText();
    }

    public String take(String shortName) {

        if (player.getInv().size() < 5) {
            Item item = player.takeItem(shortName);
            if (item != null) {
                return "You picked up the " + item.getShortName();
            } else {
                return "There are no items like " + shortName + " in the room";
            }
        } else {
            return "There is not enough space in your inventory";
        }

    }

    public String drop(String shortName) {
        Item item = player.dropItem(shortName);

        if (item != null) {
            return "You dropped the " + item.getShortName();
        } else {
            return "There are no items like " + shortName + " in your inventory";
        }

    }

}
