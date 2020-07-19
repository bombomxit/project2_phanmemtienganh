package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button btnVaoHoc;
    @FXML
    private Button btnaddEditDelete;
    @FXML
    private Button btnTutorial;
    @FXML
    private Button btnThoat;

    @FXML
    public void onQuit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void onEnglishFighter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/EnglishFighter.fxml"));
        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");

        Stage stage = (Stage) btnaddEditDelete.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Vào Học");
    }

    @FXML
    public void onTutorial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/Tutorial/tutorial.fxml"));
        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");

        Stage stage = (Stage) btnTutorial.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Tutorial");
    }

    @FXML
    public void onAddEditDelete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/addEditDelete/addEditDelete.fxml"));
        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");

        Stage stage = (Stage) btnTutorial.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("AddEditDelete");
    }


}
