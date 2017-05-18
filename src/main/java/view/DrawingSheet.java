package view;

import model.Turtle;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by yannick on 26/04/17.
 */
public class DrawingSheet extends JPanel
{
    public static Dimension dimension;
    private ArrayList<TurtleView> turtleViews;
    private MainWindow parent;

    public DrawingSheet(MainWindow parent)
    {
        DrawingSheet.dimension = new Dimension(600, 400);
        turtleViews = new ArrayList<TurtleView>();
        this.setBackground(Color.white);
        this.setSize(DrawingSheet.dimension);
        this.setPreferredSize(DrawingSheet.dimension);
        this.parent = parent;
    }

    public ArrayList<Turtle> getTurtles()
    {
        ArrayList<Turtle> ret = new ArrayList<Turtle>();
        for(TurtleView tv : this.turtleViews)
        {
            ret.add(tv.getTurtle());
        }
        return ret;
    }

    public void addTurtleView(TurtleView turtleView)
    {
        turtleViews.add(turtleView);
    }

    public void reset()
    {
        this.turtleViews.clear();
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
        for(TurtleView tv : this.turtleViews)
        {
            tv.paintComponent(g);
        }
    }

    public void setCourante(Turtle turtle)
    {
        this.parent.setCourante(turtle);
    }
}
