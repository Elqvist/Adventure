import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void startProgram(){

        boolean exit = false;

        while(!exit){

            System.out.println();
            System.out.print("What do you want to do? ");
            String decision = scanner.nextLine().trim().toLowerCase();

            if(decision.equals("exit")){
                System.out.println("Exiting game!");
                exit = true;
            }
            else if(decision.length() >= 5 && decision.substring(0, 5).equalsIgnoreCase("take ")){
                System.out.println(adventure.take(decision.substring(5)));
            }
            else if(decision.length() >= 5 && decision.substring(0, 5).equalsIgnoreCase("drop ")){
                System.out.println(adventure.drop(decision.substring(5)));
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
                    case "look", "look around" -> System.out.println(adventure.look());
                    case "inventory" -> System.out.println(adventure.printInv());
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
        System.out.println("Inventory: View items in your inventory");
        System.out.println();
    }


}
