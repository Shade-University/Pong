package pongUI;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import pongLogic.Settings;

/**
 *
 * @author Tomáš Vondra
 */
public class SettingsScreen extends Pane {

    public SettingsScreen() {
        this.setPrefSize(Settings.getWindowWidth(),
                Settings.getWindowHeight());
        this.setStyle("-fx-background-color:black");
        //TODO UI
    }

}
