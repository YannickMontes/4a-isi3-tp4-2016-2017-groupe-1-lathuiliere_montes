package View;

import Model.Turtle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by yannick on 26/04/17.
 */
public class DrawingSheet extends JPanel
{
    private ArrayList<Turtle> turtles; // la liste des turtles enregistrees

    public DrawingSheet() {
        turtles = new ArrayList<Turtle>();
    }

    public void addTortue(Turtle o) {
        turtles.add(o);
    }

    public void reset() {
        for (Iterator it = turtles.iterator(); it.hasNext();) {
            Turtle t = (Turtle) it.next();
            t.reset();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for(Iterator it = turtles.iterator(); it.hasNext();) {
            Turtle t = (Turtle) it.next();
            t.drawTurtle(g);
        }
    }
}
