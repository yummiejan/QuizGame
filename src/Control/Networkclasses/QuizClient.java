package Control.Networkclasses;

import Control.*;

/**
 * Created by janpa on 12.10.2017.
 */
public class QuizClient extends Client {
    private MainController controller;

    public QuizClient(String serverIP, int serverPort, MainController controller){
        super(serverIP,serverPort);
        this.controller = controller;
    }
    @Override
    public void processMessage(String message) {
        String[] msg = message.split("#");

        switch(msg[0]){
            case "TRYAGAIN":
                controller.displayMsg("Server: Incorrect Username or Password!");
                break;
            case "WELCOME":
                controller.displayMsg("Server: Welcome to QuizGame");
                controller.displayMsg("Your Points: " + msg[1]);
                controller.sendMsg("STARTQUEUE");
                break;
            case "STARTGAME":
                controller.startGame();
                controller.displayMsg("|-----------------------------------------------------------------------------------------------|");
                controller.displayMsg("|-------------------------------------------GAME ON---------------------------------------------|");
                controller.displayMsg("|-----------------------------------------------------------------------------------------------|");
                break;
            case "BLOCK":
                controller.displayMsg("Server: Question: " +msg[1]);
                String bA = msg[2];
                String bB = msg[3];
                String bC = msg[4];
                String bD = msg[5];
                controller.updateButtons(bA,bB,bC,bD);
                break;
            case "ANSWER":
                if(msg[1].equals("TRUE")) {
                    controller.displayMsg("Server: Your answer was wrong!");
                }else{
                    controller.displayMsg("Server: Your answer was right!");
                }
                break;
            case "ENDROUND":
                controller.displayMsg("Server: The round has ended!");
                controller.updateButtons(" ", " ", " ", " ");
                controller.disableButtons();
                break;
            case "SENDTOALL":
                controller.displayMsg(msg[1] + ": " + msg[2]);
                break;
            case "INFO":
                controller.displaySide(msg[1] + ": " + msg[2] + "\n" + msg[3] + ": " + msg[4] + "\n" + msg[5] + ": " + msg[6]);
                break;
        }
    }
}
