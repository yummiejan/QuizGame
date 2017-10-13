package Control;

import Control.Networkclasses.QuizClient;
import View.*;
/**
 * Created by Jean-Pierre on 12.01.2017.
 */
public class MainController {
    private InteractionPanelHandler view;
    private QuizClient client;

    public MainController(){
        client = new QuizClient("172.16.3.22",56789,this);
    }

    public void displayMsg(String message){
        view.addToOutput(message);
    }

    public void displaySide(String message){
        view.addToSide(message);
    }

    public void startGame(){
        enableButtons();
        sendMsg("GETBLOCK");
        view.changeLogin();
    }

    public void updateButtons(String bA,String bB, String bC, String bD){
        view.updateButtons(bA,bB,bC,bD);
    }

    public void sendMsg(String message){
        client.send(message);
    }

    public void enableButtons(){
        view.enableButtons();
    }

    public void disableButtons(){
        view.disableButtons();
    }


    public void setInteractionPanelHandler(InteractionPanelHandler interactionPanelHandler) {
        this.view = interactionPanelHandler;
    }
}
