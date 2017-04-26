package Controller;

import Model.Turtle;
import View.MainWindow;

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

    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        // actions des boutons du haut
        if (c.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int v = Integer.parseInt(window.getInputValue());
                turtleModel.avancer(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }

        }
        else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(window.getInputValue());
                turtleModel.droite(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }
        }
        else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(window.getInputValue());
                turtleModel.gauche(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + window.getInputValue());
            }
        }
        /*else if (c.equals("Proc1"))
            proc1();
        else if (c.equals("Proc2"))
            proc2();
        else if (c.equals("Proc3"))
            proc3();
        else if (c.equals("Effacer"))
            effacer();
        else if (c.equals("Quitter"))
            quitter();*/
    }



}

