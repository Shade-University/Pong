
package pongLogic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Tomáš Vondra
 */
public final class Settings {
    
    public static DoubleProperty paddleHeight = new SimpleDoubleProperty(200);
    public static DoubleProperty paddleWidth = new SimpleDoubleProperty(30);
    public static DoubleProperty ballSize = new SimpleDoubleProperty(10);
    
    private static double ballSpeed;
    private static double paddleSpeed;  
    private static double windowHeight;
    private static double windowWidth;
    
    private Settings(){};
    static{
        //Inicializace (pro přehlednost v static konstruktoru)
        ballSpeed = 10;
        paddleSpeed = 10;       
        windowHeight = 700;
        windowWidth = 1200;
    }
    
    public static void setBallSpeed(double ballSpeed) {
        Settings.ballSpeed = ballSpeed;
    }

    public static void setBallSize(double ballSize) {
        Settings.ballSize.set(ballSize);
    }

    public static void setPaddleSpeed(double paddleSpeed) {
        Settings.paddleSpeed = paddleSpeed;
    }

    public static void setPaddleHeight(double paddleHeight) {
        Settings.paddleHeight.set(paddleHeight);
    }

    public static void setPaddleWidth(double paddleWidth) {
        Settings.paddleWidth.set(paddleWidth);
    }

    public static void setWindowHeight(double windowHeight) {
        Settings.windowHeight = windowHeight;
    }

    //TODO WindowSize
    //TODO Metody na easy, medium, hard => opponentPaddleSpeed
    public static void setWindowWidth(double windowWidth) {    
        Settings.windowWidth = windowWidth;
    }

    public static double getBallSize() {
        return ballSize.get();
    }
    public static double getPaddleWidth() {
        return paddleWidth.get();
    }
    
    public static double getBallSpeed() {
        return ballSpeed;
    }

    public static double getPaddleSpeed() {
        return paddleSpeed;
    }

    public static double getPaddleHeight() {
        return paddleHeight.get();
    }

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static double getWindowWidth() {
        return windowWidth;
    }

}
