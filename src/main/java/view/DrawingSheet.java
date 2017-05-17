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
    private ArrayList<TurtleView> turtleViews;
    private MainWindow parent;

    public DrawingSheet(MainWindow parent)
    {
        turtleViews = new ArrayList<TurtleView>();
        this.setBackground(Color.white);
        this.setSize(new Dimension(600,400));
        this.setPreferredSize(new Dimension(600,400));
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
        for(TurtleView tv : turtleViews)
        {
            if(tv.getTurtle() != this.parent.getCourante())
                turtleViews.remove(tv);
            else
            {
                tv.getTurtle().reset();
                this.parent.getCourante().setPosition(this.getSize().width/2, this.getSize().height/2);
            }
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
