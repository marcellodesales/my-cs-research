/*
 * @(#)SpeedVector.java        1.0 18/04/2001, 18:28
 *
 * Copyright (c) 2001 Marcello Junior, Emerson.
 * Universidade Federal de Alagoas - Brazil.
 * All rights reserved.
 */

package obliquelaunch;

import java.lang.Math;

/**
 *  SpeedVector is responsable for everything concernig
 * the space where the throwing of an object such as a sphere will occour.
 *
 * @version 	1.01 April 19, 2001
 * @author 	Marcello Junior
 */

public class SpeedVector {

  /** This is a generic representation of the speed vectors
   * that exist in any body in movement. This will have all the
   * information of the vectors, such as Vx vector in the X coordenate,
   * and the Vx vector in the Y coordenate. You can also change the
   * gravity value, and the angle formed between the vectors.
   * Descriptions:
   *    angle = the angle of the throwing in radians.
   *    v0  = initial speed;
   *    vX0 = v0 * COS(angle);
   *    vY0 = v0 * SIN(angle);
   *    v   = SQRT(vX0*vX0 + vY0*vY0);
   * We can also take informations such as the speed in some moment...
   *
   * @param initialSpeed   the inicial speed vector (a double)
   * @param angle          the angle formed between the vectors
   *                       from initialSpeed
   */

  /** It's the real speed. */
  private double v  = 0;

  /** The initial speed passed as a parameter in the constructor. */
  private double v0 = 0;

  /** The initial speed component in the axis x. */
  private double v0X = 0;

  /** The initial speed component in the axis y. */
  private double v0Y = 0;

  /** It's the angle of the throwing formed with the horizontal. */
  private int angle = 0;

  /** The gravity used in the throwing ; */
  private double gravity = 9.8;

  /**
   * SpeedVector(double initialSpeed,int angle) will already create an
   * instance with the speed component vectors vX0 and vY0, besides the
   * initial speed v0 passed as a paramenter (inicialSpeed).
   */
  public SpeedVector(double initialSpeed,int angle){
    System.out.println("####### Criado objeto SpeedVector #######");
    this.setInitialSpeed(initialSpeed);
    this.angle = angle;
  }

public SpeedVector(){ }

  /** Sets the vX0,vY0,v0 values passed by getInitialVx(), getInitialVx() and v0. */
  public void setInitialSpeed(double v0){
    this.v0  = v0;
    this.v0X = this.getInitialVx();
    this.v0Y = this.getInitialVy();
  }

  /** Sets the gravity value; */
  public void setGravity(double gravity){
    this.gravity = gravity;
  }

  /** Sets the angle value; */
  public void setAngle(int angle){
    this.angle = angle;
  }
  
  /** Gets the angle used in the launch */
  public int getAngle(){
    return this.angle;
  }
  
  /** sets and Gets the angle used in the launch */
  /** public int getAngle(double reach){
	  double a = Math.toDegrees(Math.asin((reach * gravity)/(this.v0*this.v0))/ 2);
	this.angle = (int)a;
	return this.angle;
   }*/
   public int getAngle(double reach){
	  double a = Math.asin((reach * gravity)/(this.v0*this.v0));
	  if (a == 0 )	  a=-2;
	  this.angle = (int)Math.toDegrees(a/2);
	return this.angle;
   }
  /** sets and Gets the angle used in the launch */
  public int getAngle(double reach, double maxHeight){
	  double a = Math.toDegrees(Math.atan((4 * maxHeight )/reach));
	  this.angle = (int)a;
	return this.angle;
  }
  
  /** Gets the initialSpeed throws the angle, range and gravity */
  public double getInitialSpeed(int angle, double reach,double gravity){
	  return (Math.sqrt((reach * gravity)/Math.sin(Math.toRadians(2*angle))));
  }
  
  /** Gets the gravity value; */
  public double getGravity(){
    return this.gravity;
  }
  
  /** Gets the initial speed in the vector X  */
  public double getV0X(){
    return this.getInitialVx(); //redundante
  }

  /** Gets the initial speed in the vector Y */
  public double getV0Y(){
    return this.getInitialVy();
  }
  
  /**
   * Gets the initial vector component in the axis X; vX0 = v0 * COS(angle).
   * It's necessary for the @method setInitialSpeed.
   */
  private double getInitialVx(){
    return (this.v0 * Math.cos(Math.toRadians(this.getAngle())));
  }

  /**
   * Gets the initial vector component in the axis Y; vY0 = v0 * SIN(angle)
   * It's necessary for the @method setInitialSpeed.
   */
  private double getInitialVy(){
    return (this.v0 * Math.sin(Math.toRadians(this.getAngle())));
  }

  /**
   * Returns the vX value in some moment of time; Note
   * that this value is the same as vX0 because there's no gravity in the
   * axis x in any moment (a uniform movement). Because of that we
   * didn't pass value of the moment.
   */
  public double getVx(){
    return this.v0X;
  }

  /** Gets the vY value in a given moment; vY = vY0 - gravity * moment. */
  public double getVy(int moment){
    return (this.v0Y - this.gravity * moment);
  }

    /**
   * Returns the real value of the speed in a given moment of time;
   * v(moment) = SQRT(vX(t)*vX(t) + vY(t)*vY(t))
   * It's the sum of the component speed vectors vX and vY.
   */
  public double getSpeed(int moment){
    double x = this.getVx();
    double y = this.getVy(moment);
    return Math.sqrt((x * x) + (y * y) + 2 * x * y * Math.cos(this.getAngle()));
  }
}
