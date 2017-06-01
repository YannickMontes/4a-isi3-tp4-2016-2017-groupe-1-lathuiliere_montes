package services;

import model.Turtle;
import view.DrawingSheet;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by yannick on 01/06/17.
 */
public abstract class Behavior extends Thread
{
    protected static Behavior instance;
    protected ArrayList<Turtle> turtles;
    protected DrawingSheet drawingSheet;

    public void setTurtles(ArrayList<Turtle> t)
    {
        this.turtles = t;
    }

    public void setDrawingSheet(DrawingSheet sheet)
    {
        this.drawingSheet = sheet;
    }
}
