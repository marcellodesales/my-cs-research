/*
 * @(#)Projectile.java        1.0 Created on 18 de Abril de 2001, 21:33
 *
 * Copyright (c) 2001 Marcello Junior, Emerson.
 * Universidade Federal de Alagoas - Brazil.
 * All rights reserved.
 */

package obliquelaunch;

import obliquelaunch.SpeedVector;

import java.awt.Point;
/**
 * Projectile is something thrown up by the Thrower. It can be a ball, or a sphere...
 *
 * @author Marcello
 * @version 1.5
 */
public class Projectile {

  /** The point position of the projectile. */
  public Point point;
  
  /** The speed vectors of the projectile. */
  public SpeedVector speedVector;

  /** Creates a new Projectile with its speed vectors, besides setting the initialPoint - setInitialPoint(). */
  public Projectile() {
    System.out.println("###### Criado objeto Projectile #######");
    speedVector = new SpeedVector();
    point       = new Point(0,0);
    this.setInitialPoint();
  }

  /** Sets the initial value of the projectile, in the position (0, 0). */
  public void setInitialPoint(){
    this.point.x = 0;
    this.point.y = 0;
  }

  /** Sets the angle of the projectile. */
  public void setAngle(int angle){
    this.speedVector.setAngle(angle);
  }

  /** Sets the initial speed of the projectile by a Thrower object. */
  public void setInitialSpeed(double speed){
    this.speedVector.setInitialSpeed(speed);
  }

  /** Sets the gravity value of the space where the projectile is being thrown. */
  public void setGravity(double gravity){
    this.speedVector.setGravity(gravity);
  }

  /** Gets the initial speed value of the Vector Vy. */
  public double getVyInitialSpeed(){
    return this.speedVector.getV0Y();
  }
  /** Gets the initial speed */
  public double getInitialSpeed(int angle, double reach,double gravity){
	  return this.speedVector.getInitialSpeed(angle, reach, gravity);
  }
  /** Gets the initial speed value of the Vector Vx. */
  public double getVxInitialSpeed(){
    return this.speedVector.getV0X();
  }

  /** Gets the speed value in a given moment. */
  public double getSpeed(int moment){
    return this.speedVector.getSpeed(moment);
  }

  /**
   * Gets the point of X position in a given moment (V0x * moment) and the Y
   * position in a given moment. (Voy * moment) - (g*moment*moment)/2
   */
  public Point getMomentPoint(int moment){
      Point point = new Point();
      double g    = this.speedVector.getGravity();
      double vY0  = this.getVyInitialSpeed();
      point.y     = (int)((vY0 * moment) - ((g*moment*moment)/2));
      point.x     = (int)(this.getVxInitialSpeed() * moment);
      return point;
  }
    
  public Point getParablePoint(int xPoint){
      Point  point   = new Point();
      int    angle   = this.speedVector.getAngle();
      double v0X     = this.speedVector.getV0X();
      double gravity = this.speedVector.getGravity();
      
      //int y = (int)((xPoint * (Math.tan(Math.toRadians(angle)))) - (xPoint*xPoint * gravity) /(2*v0X*v0X));
      double y = ((xPoint * (Math.tan(Math.toRadians(angle)))) - (xPoint*xPoint * gravity) /(2*v0X*v0X));
      point.setLocation(xPoint,y);
      return point;
  }
  
  public int getAngle(double reach){
	  return this.speedVector.getAngle(reach);
  }
  
  public int getAngle(double reach, double maxHeight){
	  return this.speedVector.getAngle(reach,maxHeight);
  }
}
