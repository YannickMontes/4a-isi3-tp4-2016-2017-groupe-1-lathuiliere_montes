package controller;

import model.Turtle;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by yannick on 26/04/17.
 */
public class GlobalController implements ActionListener
{
    private Turtle currentTurtle;
    private MainWindow window;
    private ArrayList<Turtle> turtles;

    public GlobalController(Turtle turtle, MainWindow windows, ArrayList<Turtle> turtles)
    {
        this.currentTurtle = turtle;
        this.window = windows;
        this.turtles = turtles;
    }

    public void setCurrentTurtle(Turtle turtle)
    {
        this.currentTurtle = turtle;
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
                currentTurtle.avancer(inputValue);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }

        }
        else if (actionDescription.equals("Droite")) {
            try {
                int inputValue = Integer.parseInt(window.getInputValue());
                currentTurtle.droite(inputValue);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }
        }
        else if (actionDescription.equals("Gauche")) {
            try {
                int inputValue = Integer.parseInt(window.getInputValue());
                currentTurtle.gauche(inputValue);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }
        }
        else if (actionDescription.equals("Proc1"))
            this.window.proc1();
        else if (actionDescription.equals("Proc2"))
            this.window.proc2();
        else if (actionDescription.equals("Proc3"))
            this.window.proc3();
        else if (actionDescription.equals("Effacer"))
            this.window.effacer();
        else if (actionDescription.equals("Quitter"))
            this.window.quitter();
        else if(actionDescription.equals("AddTurtle"))
            this.window.addNewTurtle();
    }
}

