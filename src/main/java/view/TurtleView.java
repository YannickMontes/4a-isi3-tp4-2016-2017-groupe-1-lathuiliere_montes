package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import model.Shape;

/**
 * Created by yannick on 26/04/17.
 */
public class TurtleView extends JComponent implements Observer, MouseListener
{
    private static final int sizeArrowHead = 10;
    private static final int sizeBaseArrow = 5;

    private Turtle turtleModel;
    private DrawingSheet parent;
    private Polygon polygon;

    public TurtleView(Turtle turtle, DrawingSheet parent)
    {
        this.turtleModel = turtle;
        this.parent = parent;
        this.turtleModel.addObserver(this);
        parent.addMouseListener(this);

        this.setPreferredSize(new Dimension(5,5));
        this.setMinimumSize(new Dimension(5,5));

    }

    public Turtle getTurtle()
    {
        return this.turtleModel;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.black);
        this.drawTurtle(g);
    }

    public void drawTurtle (Graphics graph) {
        if (graph==null)
            return;

        Shape shape = turtleModel.getShape();

        switch (shape) {
            case Circle:
                drawCircle(graph);
            case Triangle:
                drawArrow(graph);
            default:
                drawArrow(graph);
        }
    }

    public void drawArrow(Graphics graph)
    {
        Graphics2D graphics = (Graphics2D)graph;
        Point turtlePosition = new Point(turtleModel.getX(), turtleModel.getY());
        this.polygon = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta= Math.toRadians(-1*this.turtleModel.getDirection());
        //Demi angle au sommet du triangle
        double alpha=Math.atan( (float)TurtleView.sizeBaseArrow / (float)TurtleView.sizeArrowHead );
        //Rayon de la fleche
        double r=Math.sqrt(TurtleView.sizeArrowHead*TurtleView.sizeArrowHead + TurtleView.sizeBaseArrow*TurtleView.sizeBaseArrow );
        //Sens de la fleche

        //Pointe
        Point p2=new Point((int) Math.round(turtlePosition.x+r*Math.cos(theta)),
                (int) Math.round(turtlePosition.y-r*Math.sin(theta)));
        this.polygon.addPoint(p2.x,p2.y);
        this.polygon.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        this.polygon.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        this.polygon.addPoint(p2.x,p2.y);
        graphics.setColor(this.turtleModel.getColor());
        graphics.fillPolygon(this.polygon);

    }

    public void drawCircle(Graphics graph)
    {
        Graphics2D graphics = (Graphics2D)graph;

        int radius = 15;
        int x = turtleModel.getX()-(radius/2);
        int y = turtleModel.getY()-(radius/2);

        graphics.setColor(this.turtleModel.getColor());
        graphics.fillOval(x,y,radius,radius);

        this.polygon = new Polygon();

        this.polygon.addPoint(x, y);
        this.polygon.addPoint(x, y);
    }


    public void update(Observable o, Object arg)
    {
        repaint();
    }

    public void mouseClicked(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        if(polygon.contains(e.getX(), e.getY()) && this.parent.getParent().canControl())
        {
            this.parent.setCourante(this.turtleModel);
        }
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }
}
