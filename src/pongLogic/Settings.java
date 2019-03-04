
package pongLogic;

/**
 *
 * @author Tomáš Vondra
 */
public final class Settings {
    
    private static double ballSpeed;
    private static double ballSize;

    private static double paddleSpeed;
    private static double paddleHeight;
    private static double paddleWidth;
 
    private static double windowHeight;
    private static double windowWidth;
    
    private Settings(){};
    static{
        //Inicializace (pro přehlednost v static konstruktoru)
        ballSpeed = 10;
        ballSize = 10;
        
        paddleHeight = 200;
        paddleWidth = 10;
        paddleSpeed = 10;       
        windowHeight = 700;
        windowWidth = 1200;
    }
    
    public static void setBallSpeed(double ballSpeed) {
        Settings.ballSpeed = ballSpeed;
    }

    public static void setBallSize(double ballSize) {
        Settings.ballSize = ballSize;
    }

    public static void setPaddleSpeed(double paddleSpeed) {
        Settings.paddleSpeed = paddleSpeed;
    }

    public static void setPaddleHeight(double paddleHeight) {
        Settings.paddleHeight = paddleHeight;
    }

    public static void setPaddleWidth(double paddleWidth) {
        Settings.paddleWidth = paddleWidth;
    }

    public static void setWindowHeight(double windowHeight) {
        Settings.windowHeight = windowHeight;
    }

    //TODO settery na konfigurovatelné nastavení
    //TODO Metody na easy, medium, hard => opponentPaddleSpeed
    public static void setWindowWidth(double windowWidth) {    
        Settings.windowWidth = windowWidth;
    }

    public static double getBallSize() {
        return ballSize;
    }
    public static double getPaddleWidth() {
        return paddleWidth;
    }
    
    public static double getBallSpeed() {
        return ballSpeed;
    }

    public static double getPaddleSpeed() {
        return paddleSpeed;
    }

    public static double getPaddleHeight() {
        return paddleHeight;
    }

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static double getWindowWidth() {
        return windowWidth;
    }

}
