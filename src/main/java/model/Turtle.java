package model;

import services.TurtleService;
import view.DrawingSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Turtle extends Observable
{
	protected int x;
	protected int y;
	protected int direction;
	protected int coul;

    public int getCoul() {
        return coul;
    }

    public void setCoul(int coul) {
        this.coul = coul;
    }

    public int getFieldOfViewDistance() {
        return fieldOfViewDistance;
    }

    public void setFieldOfViewDistance(int fieldOfViewDistance) {
        this.fieldOfViewDistance = fieldOfViewDistance;
    }

    public int getFieldOfViewAngle() {
        return fieldOfViewAngle;
    }

    public void setFieldOfViewAngle(int fieldOfViewAngle) {
        this.fieldOfViewAngle = fieldOfViewAngle;
    }

    protected int fieldOfViewDistance;
	protected int fieldOfViewAngle;
	protected double speed;

	public Turtle()
    {
		reset();
	}

    public void reset()
    {
        x = 0;
        y = 0;
        direction = -90;
        coul = 0;
        speed = 3;
        fieldOfViewAngle = 130;
        fieldOfViewDistance = 50;
    }

    public void setColor(int n)
    {
        coul = n;
    }

    public int getColorInt()
    {
        return coul;
    }

    public Color getColor()
	{
		return this.decodeColor(coul);
	}

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        if(direction > 180)
        {
            this.direction =  (-1 * direction) + 180;
        }
        else if(direction < -180)
        {
            this.direction = (-1 * direction) - 180;
        }
        else
        {
            this.direction = direction;
        }
        notifyObservers();
    }

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setPosition(int newX, int newY)
    {
        if(newX > DrawingSheet.dimension.getWidth())
        {
            newX = newX - (int)DrawingSheet.dimension.getWidth();
        }
        else if(newX < 0)
        {
            newX = newX + (int)DrawingSheet.dimension.getWidth();
        }
        if(newY > DrawingSheet.dimension.getHeight())
        {
            newY = newY - (int)DrawingSheet.dimension.getHeight();
        }
        else if(newY < 0)
        {
            newY = newY + (int)DrawingSheet.dimension.getHeight();
        }

        x = newX;
		y = newY;

		notifyObservers();
	}

	protected Color decodeColor(int c) {
		switch(c) {
			case 0: return(Color.black);
			case 1: return(Color.blue);
			case 2: return(Color.cyan);
			case 3: return(Color.darkGray);
			case 4: return(Color.red);
			case 5: return(Color.green);
			case 6: return(Color.lightGray);
			case 7: return(Color.magenta);
			case 8: return(Color.orange);
			case 9: return(Color.gray);
			case 10: return(Color.pink);
			case 11: return(Color.yellow);
			default : return(Color.black);
		}
	}

	public void avancer(double dist)
    {
		int newX = (int) Math.round(x+dist*Math.cos(Math.toRadians(direction)));
		int newY = (int) Math.round(y+dist*Math.sin(Math.toRadians(direction)));

		this.setPosition(newX, newY);

        notifyObservers();
	}

	public void droite(int ang)
    {
		direction = (direction + ang) % 360;
        notifyObservers();
	}

	public void gauche(int ang)
    {
		direction = (direction - ang) % 360;
        notifyObservers();
	}

	public void couleur(int n) {
		coul = n % 12;
	}

	public void couleurSuivante() {
	 	couleur(coul+1);
	}

	/** quelques classiques */

	public void carre() {
		for (int i=0;i<4;i++) {
			avancer(100);
			droite(90);
		}
		notifyObservers();
	}

	public void poly(int n, int a) {
		for (int j=0;j<a;j++) {
			avancer(n);
			droite(360/a);
		}
        notifyObservers();
	}

	public void spiral(int n, int k, int a) {
		for (int i = 0; i < k; i++) {
			couleur(coul+1);
			avancer(n);
			droite(360/a);
			n = n+1;
		}
        notifyObservers();
	}

	public void flocking(ArrayList<Turtle> turtles, boolean color)
	{
		ArrayList<Turtle> neigh = TurtleService.getInstance().getNeighborhoodOfTurtle(this, turtles, color);

        if(!neigh.isEmpty())
		{
			double meanDirection = TurtleService.getInstance().getAverageDirection(neigh);

			double meanSpeed = TurtleService.getInstance().getAverageSpeed(neigh);

			this.setDirection((int) meanDirection);

			if(!TurtleService.getInstance().get360Neighborhood(this, turtles, 5, color).isEmpty())
			{
				meanDirection *= 0.90;
			}

			this.setSpeed(meanSpeed);
		}
		notifyObservers();
	}

	public void moove()
	{
		this.avancer(speed);
	}
}
