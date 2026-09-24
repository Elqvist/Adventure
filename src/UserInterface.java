import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void startProgram(){

        boolean exit = false;

        while(!exit){

            System.out.println();
            System.out.print("What do you want to do? ");
            String decision = scanner.nextLine();

            if(decision.equalsIgnoreCase("exit")){
                System.out.println("Exiting game!");
                exit = true;
            }
            else{
                switch(decision.toLowerCase()){
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
        System.out.println();
    }


}
