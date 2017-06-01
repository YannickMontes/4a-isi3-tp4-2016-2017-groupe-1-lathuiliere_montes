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
        DrawingSheet.dimension = new Dimension(1000, 800);
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

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);

        Color color = graph.getColor();

        Dimension dim = getSize();
        graph.setColor(Color.white);
        graph.fillRect(0,0,dim.width, dim.height);
        graph.setColor(color);

        showTurtles(graph);
    }

    public void showTurtles(Graphics graph) {
        for(TurtleView tv : this.turtleViews)
        {
            tv.paintComponent(graph);
        }
    }

    public void setCourante(Turtle turtle)
    {
        this.parent.setCourante(turtle);
    }

    public MainWindow getParent()
    {
        return this.parent;
    }
}
