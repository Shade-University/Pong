package pongUI;


import pongLogic.GameLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Pong extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        GameLogic game = new GameLogic(); //Logika hry

        GameScreen gameScreen = new GameScreen(game); //Okno hry
        WinScreen winScreen = new WinScreen();
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        SettingsScreen settingsScreen = new SettingsScreen();

        root.getChildren().add(welcomeScreen);
        Scene scene = new Scene(root);
        welcomeScreen.requestFocus(); //Odchytávat klávesy

        welcomeScreen.setOnNewGame(() -> {
            game.Start();
            root.getChildren().clear();
            root.getChildren().add(gameScreen);
            gameScreen.requestFocus();
        });

        welcomeScreen.setOnSettings(() -> {
            root.getChildren().clear();
            root.getChildren().add(settingsScreen);
            settingsScreen.requestFocus();
        });
        
        settingsScreen.setOnEnd(() -> {
            root.getChildren().clear();
            root.getChildren().add(welcomeScreen);
            welcomeScreen.requestFocus();
        });

        game.setOnWinConsumer((p) -> {
            game.Stop();
            root.getChildren().clear();
            winScreen.setWinner(p);
            root.getChildren().add(winScreen);
            winScreen.requestFocus();
        });

        winScreen.setOnRestart(() -> {
            root.getChildren().clear();
            root.getChildren().add(gameScreen);
            gameScreen.requestFocus();
            game.Restart();
        });

        primaryStage.setTitle("Pong");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
