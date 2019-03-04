
package pongObjects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Tomáš Vondra
 */
public abstract class GameObject {

    private DoubleProperty xProperty = new SimpleDoubleProperty(0);
    private DoubleProperty yProperty = new SimpleDoubleProperty(0);
    //DoubleProperty pro bind

    public DoubleProperty getxProperty() {
        return xProperty;
    }

    public DoubleProperty getyProperty() {
        return yProperty;
    }
    
    public double getX(){
        return xProperty.get();
    }
    public void setX(double x){
        xProperty.set(x);
    }
    public double getY(){
        return yProperty.get();
    }
    public void setY(double y){
        yProperty.set(y);
    }
    
    public abstract void update();
}
