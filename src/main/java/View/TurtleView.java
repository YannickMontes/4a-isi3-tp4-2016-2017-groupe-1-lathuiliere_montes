package View;

import Model.Segment;
import Model.Turtle;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by yannick on 26/04/17.
 */
public class TurtleView extends JComponent
{
    private static final int sizeArrowHead = 10;
    private static final int sizeBaseArrow = 5;

    private Turtle turtleModel;

    public TurtleView(Turtle turtle)
    {
        this.turtleModel = turtle;
        //this.turtleModel.addObservers(this);
    }

    public void drawTurtle (Graphics graph) {
        if (graph==null)
            return;

        this.drawSegmentTrack(graph);

        this.drawArrow(graph);
    }

    public void drawSegmentTrack(Graphics graph)
    {
        for(Segment segment : this.turtleModel.getListSegments())
        {
            //segment.drawSegment(graph);
        }
    }

    public void drawArrow(Graphics graph)
    {
        Point turtlePosition = new Point(turtleModel.getX(), turtleModel.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta= Math.toRadians(-1*this.turtleModel.getDir());
        //Demi angle au sommet du triangle
        double alpha=Math.atan( (float)TurtleView.sizeBaseArrow / (float)TurtleView.sizeArrowHead );
        //Rayon de la fleche
        double r=Math.sqrt(TurtleView.sizeArrowHead*TurtleView.sizeArrowHead + TurtleView.sizeBaseArrow*TurtleView.sizeBaseArrow );
        //Sens de la fleche

        //Pointe
        Point p2=new Point((int) Math.round(turtlePosition.x+r*Math.cos(theta)),
                (int) Math.round(turtlePosition.y-r*Math.sin(theta)));
        arrow.addPoint(p2.x,p2.y);
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x,p2.y);
        graph.setColor(Color.green);
        graph.fillPolygon(arrow);
    }
}
