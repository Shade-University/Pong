package pongUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pongLogic.Settings;

/**
 *
 * @author Tomáš Vondra
 */
public final class SettingsScreen extends GridPane {

    private Runnable onEnd = () -> {
    };

    public Runnable getOnEnd() {
        return onEnd;
    }

    public void setOnEnd(Runnable onEnd) {
        this.onEnd = onEnd;
    }
    
    private final TextField txtFieldBallSpeed;
    private final TextField txtFieldBallSize;
    private final TextField txtFieldPaddleSpeed;
    private final TextField txtFieldPaddleHeight;
    private final TextField txtFieldWindowWidth;
    private final TextField txtFieldWindowHeight;
    
    public SettingsScreen() {
        this.setPrefSize(Settings.getWindowWidth(),
                Settings.getWindowHeight());
        this.setStyle("-fx-background-color:black");
        this.setAlignment(Pos.CENTER);

        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 10, 0, 10));

        Label lblBallSpeed = new Label("Rychlost míčku");
        lblBallSpeed.setTextFill(Color.WHITE);
        Label lblBallSize = new Label("Velikost míčku");
        lblBallSize.setTextFill(Color.WHITE);
        Label lblPaddleSpeed = new Label("Rychlost pálky");
        lblPaddleSpeed.setTextFill(Color.WHITE);
        Label lblPaddleHeight = new Label("Velikost pálky");
        lblPaddleHeight.setTextFill(Color.WHITE);
        Label lblWindowHeight = new Label("Velikost okna");
        lblWindowHeight.setTextFill(Color.WHITE);
        Label lblWindowWidth = new Label("Šířka okna");
        lblWindowWidth.setTextFill(Color.WHITE);

        txtFieldBallSpeed = new TextField(Double.toString(
                Settings.getBallSpeed()));
        txtFieldBallSize = new TextField(Double.toString(
                Settings.getBallSize()));
        txtFieldPaddleSpeed = new TextField(Double.toString(
                Settings.getPaddleSpeed()));
        txtFieldPaddleHeight = new TextField(Double.toString(
                Settings.getPaddleHeight()));
        txtFieldWindowWidth = new TextField(Double.toString(
                Settings.getWindowWidth()));
        txtFieldWindowHeight = new TextField(Double.toString(
                Settings.getWindowHeight()));

        Button btnOk = new Button("Ok");
        btnOk.setOnAction(e -> {
            if(validate()){
                setupValues();
                onEnd.run();
            } else{
                showAlert();
            }
            
        });
        btnOk.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(e -> onEnd.run());
        btnCancel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        this.add(lblBallSize, 0, 0);
        this.add(txtFieldBallSize, 1, 0);
        this.add(lblBallSpeed, 2, 0);
        this.add(txtFieldBallSpeed, 3, 0);

        this.add(lblPaddleSpeed, 0, 1);
        this.add(txtFieldPaddleSpeed, 1, 1);
        this.add(lblPaddleHeight, 2, 1);
        this.add(txtFieldPaddleHeight, 3, 1);

        this.add(lblWindowWidth, 0, 2);
        this.add(txtFieldWindowWidth, 1, 2);
        this.add(lblWindowHeight, 2, 2);
        this.add(txtFieldWindowHeight, 3, 2);

        this.add(btnOk, 0, 3, 2, 3);
        this.add(btnCancel, 2, 3, 3, 3);
    }

    private boolean validate() {
        for (Node node : this.getChildren()) {
            try {
                if (node instanceof TextField) {
                    Double.parseDouble(((TextField) node).getText());
                }
            }
            catch(NumberFormatException | NullPointerException npe){
                return false;
            }
        }
        return true;
    }
    
    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Validační error");
        alert.setContentText("Některá z hodnot není správně vyplněná");
        alert.show();
    }
    private void setupValues(){
        Settings.setBallSize(Double.parseDouble(txtFieldBallSize.getText()));
        Settings.setBallSpeed(Double.parseDouble(txtFieldBallSpeed.getText()));
        Settings.setPaddleHeight(Double.parseDouble(txtFieldPaddleHeight.getText()));
        Settings.setPaddleSpeed(Double.parseDouble(txtFieldPaddleSpeed.getText()));
        Settings.setWindowWidth(Double.parseDouble(txtFieldWindowWidth.getText()));
        Settings.setWindowHeight(Double.parseDouble(txtFieldWindowHeight.getText()));
    }

}
