
package pongObjects;

import pongLogic.Settings;

/**
 *
 * @author Tomáš Vondra
 */
public class Ball extends GameObject {

    private double angleX;
    private double angleY;
    //Momentálně jen 45 stupňů

    public double getAngleY() {
        return angleY;
    }

    public void setAngleY(double angleY) {
        this.angleY = angleY;
    }
    
    public double getAngleX(){
        return angleX;
    }
    public void setAngleX(double angle){
        this.angleX = angle;
    }
    
    @Override
    public void update() {
        //Pohyb míčku
        setX(getX() + (Settings.getBallSpeed() * angleX));
        setY(getY() + (Settings.getBallSpeed() * angleY));
    }

}
