package controller;

import model.Turtle;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yannick on 26/04/17.
 */
public class GlobalController implements ActionListener
{
    private Turtle turtleModel;
    private MainWindow window;

    public GlobalController(Turtle turtle, MainWindow windows)
    {
        this.turtleModel = turtle;
        this.window = windows;
    }

    // TODO: optimize switch + declare inputValue taller
    public void actionPerformed(ActionEvent action)
    {
        String actionDescription = action.getActionCommand();

        // top bar button's actions
        if (actionDescription.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int inputValue = Integer.parseInt(window.getInputValue());
                turtleModel.avancer(inputValue);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }

        }
        else if (actionDescription.equals("Droite")) {
            try {
                int inputValue = Integer.parseInt(window.getInputValue());
                turtleModel.droite(inputValue);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }
        }
        else if (actionDescription.equals("Gauche")) {
            try {
                int inputValue = Integer.parseInt(window.getInputValue());
                turtleModel.gauche(inputValue);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }
        }
        /*else if (actionDescription.equals("Proc1"))
            proc1();
        else if (actionDescription.equals("Proc2"))
            proc2();
        else if (actionDescription.equals("Proc3"))
            proc3();
        else if (actionDescription.equals("Effacer"))
            effacer();
        else if (actionDescription.equals("Quitter"))
            quitter();*/
    }
}

