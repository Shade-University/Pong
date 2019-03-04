
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
    
    //TODO settery na konfigurovatelné nastavení
    //TODO Metody na easy, medium, hard => opponentPaddleSpeed

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
