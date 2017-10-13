package View;

import Control.MainController;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Jean-Pierre on 13.09.2017.
 */
public class InteractionPanelHandler {

    private MainController mainController;

    private JPanel panel;
    private JTextArea console;
    private JLabel title;
    private JTextField chatBox;
    private JLabel chatLabel;
    private JButton loginButton;
    private JButton buttonA;
    private JButton buttonB;
    private JTextArea sideField;
    private JButton buttonC;
    private JButton buttonD;
    private JTextField passwordTextField;
    private JTextField usernameTextField;
    private JLabel usernameLabel;
    private boolean focus;
    private boolean login;

    public InteractionPanelHandler(MainController mainController) {
        this.mainController = mainController;
        mainController.setInteractionPanelHandler(this);
        createButtons();

        addToOutput("Login with your username and password!");

        login = true;

    }

    private void createButtons(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(login) {
                    mainController.sendMsg("LOGIN");
                    mainController.sendMsg("USER#" + usernameTextField.getText() + "#" + passwordTextField.getText());
                }else{
                    mainController.sendMsg("GETPOINTS");
                }
            }
        });

        chatBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                focus = true;
            }
        });

        chatBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                focus = false;
            }
        });

        chatBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(focus && e.getKeyCode() == KeyEvent.VK_ENTER){
                    mainController.sendMsg("SEND#" + chatBox.getText());

                }
                chatBox.setText("");
            }
        });

        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.sendMsg("ANSWER#1");
            }
        });

        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.sendMsg("ANSWER#2");
            }
        });

        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.sendMsg("ANSWER#3");
            }
        });

        buttonD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.sendMsg("ANSWER#4");
            }
        });
    }
    public void updateButtons(String bA, String bB, String bC, String bD){
        buttonA.setText(bA);
        buttonB.setText(bB);
        buttonC.setText(bC);
        buttonB.setText(bD);
    }

    public void enableButtons(){
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

    public void disableButtons(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    public void enableLogin(){
        loginButton.setEnabled(true);
    }
    public void disableLogin(){
        loginButton.setEnabled(false);
    }

    public void changeLogin(){
        loginButton.setText("Get points");
        loginButton.setEnabled(true);
        login = false;
    }

    public JPanel getPanel(){
        return panel;
    }

    public void addToOutput(String text){
        if(console.getText().isEmpty()){
            console.setText(text);
        }else{
            console.setText(console.getText() + "\n" + text);
        }

    }

    public void addToSide(String text){
        if(sideField.getText().isEmpty()){
            sideField.setText(text);
        }else{
            sideField.setText(sideField.getText() + "\n" + text);
        }
    }

}