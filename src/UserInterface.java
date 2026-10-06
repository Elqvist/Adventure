import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void startProgram(){

        boolean exit = false;

        while(!exit){
            System.out.println("\n==============================");
            System.out.println("Welcome to our adventure game!");
            System.out.println("==============================");
            System.out.println("\n1. Start game");
            System.out.println("2. Help");
            System.out.println("3. Credits");
            System.out.println("4. Exit");
            System.out.print("\nEnter: ");
            String choice = scanner.nextLine();
            System.out.println();

            switch(choice){
                case "1" -> startGame();
                case "2" -> help();
                case "3" -> credits();
                case "4" -> exit = true;
                default -> System.out.println("Command not found!");
            }
        }
    }

    public void startGame(){
        System.out.println(adventure.look());
        while(adventure.getGameRunning()){

            System.out.println();
            System.out.print("What do you want to do? ");
            String decision = scanner.nextLine().trim().toLowerCase();

            if(decision.equals("exit")){
                System.out.println("Exiting game!");
                adventure.setGameRunning(false);
            }
            else if(decision.length() >= 5 && decision.substring(0, 5).equalsIgnoreCase("take ")){
                System.out.println(adventure.take(decision.substring(5)));
            }
            else if(decision.length() >= 5 && decision.substring(0, 5).equalsIgnoreCase("drop ")){
                System.out.println(adventure.drop(decision.substring(5)));
            }
            else if(decision.length() >= 4 && decision.substring(0, 4).equalsIgnoreCase("eat ")){
                System.out.println(adventure.eat(decision.substring(4)));
            }
            else if(decision.length() >= 6 && decision.substring(0, 6).equalsIgnoreCase("equip ")){
                System.out.println(adventure.equip(decision.substring(6)));
            }
            else if(decision.length() >= 7 && decision.substring(0, 7).equalsIgnoreCase("attack ")){
                System.out.println(adventure.attack(decision.substring(7)));
            }
            else{
                switch(decision){
                    case "go north", "north" -> {
                        if(adventure.go("north")){
                            System.out.println(adventure.look());
                        }
                        else{
                            System.out.println("You cannot go this way!");
                        }
                    }
                    case "go south", "south" -> {
                        if(adventure.go("south")){
                            System.out.println(adventure.look());
                        }
                        else{
                            System.out.println("You cannot go this way!");
                        }
                    }
                    case "go west", "west" -> {
                        if(adventure.go("west")){
                            System.out.println(adventure.look());
                        }
                        else{
                            System.out.println("You cannot go this way!");
                        }
                    }
                    case "go east", "east" -> {
                        if(adventure.go("east")){
                            System.out.println(adventure.look());
                        }
                        else{
                            System.out.println("You cannot go this way!");
                        }
                    }
                    case "health", "show health" -> System.out.println(adventure.getHealth());
                    case "look", "look around" -> System.out.println(adventure.look());
                    case "inventory" -> System.out.println(adventure.printInv());
                    case "equipped" -> System.out.println(adventure.getEquipped());
                    case "help" -> help();
                    default -> System.out.println("Command not found! Write help for instructions.");
                }
            }
        }
    }

    public void help(){
        System.out.println("Directions:");
        System.out.println("North: Go north");
        System.out.println("East: Go east");
        System.out.println("South: Go south");
        System.out.println("West: Go west");
        System.out.println();
        System.out.println("Actions:");
        System.out.println("Look: Look around");
        System.out.println("Exit: Exit game");
        System.out.println("Take: Pick up an item from a room");
        System.out.println("Drop: Drop an item in a room");
        System.out.println("Eat: Eats a suitable item directly from your inventory or from the room");
        System.out.println("Inventory: View items in your inventory");
        System.out.println("Health: Shows your current health status");
        System.out.println("Equip: Equips a weapon in your inventory");
        System.out.println("Attack: Attacks an enemy if a weapon is equipped");
        System.out.println();
    }

    public void credits(){
        System.out.println("=========");
        System.out.println("Made by: ");
        System.out.println("Nikolai");
        System.out.println("Malthe");
        System.out.println("Emil");
        System.out.println("=========");
    }

}
