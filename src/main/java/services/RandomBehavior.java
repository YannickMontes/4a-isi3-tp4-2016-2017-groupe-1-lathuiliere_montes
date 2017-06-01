package services;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import model.Turtle;
import view.DrawingSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 17/05/17.
 */
public class RandomBehavior extends Behavior
{
    public static Behavior getInstance()
    {
        if(instance == null)
        {
            instance = new RandomBehavior();
        }
        return instance;
    }

    @Override
    public void run()
    {
        while(true)
        {
            for(Turtle t : turtles)
            {
                t.randomMoove();
                t.moove();
            }
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
            }
            drawingSheet.repaint();
        }
    }
}
