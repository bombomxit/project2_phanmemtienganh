package Controller.vaoHoc;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerEnglishFighter {
    @FXML
    private Button buttonLv1;
    @FXML
    private Button buttonLv2;
    @FXML
    private Button buttonLv3;
    @FXML
    private Button buttonLv4;
    @FXML
    private Button buttonLv5;
    @FXML
    private Button buttonLv6;
    @FXML
    private Button buttonLv7;
    @FXML
    private Button btnBack;

    @FXML
    public void onBack(ActionEvent event) {
        Parent root1 = null;
        try {
            //lấy scence cũ
            root1 = FXMLLoader.load(getClass().getClassLoader().getResource("View/Background/background.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //lấy Stage cũ để set Scene cũ
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // set lại background cho Scene cũ
        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
        root1.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        // lấy Stage cũ để set Scene cũ : quay trở lại màn hình chính
        stage.setTitle("English Fighter");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
    }

    public void onbtnLevel1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/Level1.fxml"));
        String image = getClass().getClassLoader().getResource("Source/Level1.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonLv1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Level 1");
    }

    public void onbtnLevel2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/Level2.fxml"));
        String image = getClass().getClassLoader().getResource("Source/Level2.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonLv2.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Level 2");
    }

    public void onbtnLevel3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/Level3.fxml"));
        String image = getClass().getClassLoader().getResource("Source/Level3.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonLv3.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Level 3");
    }

    public void onbtnLevel4(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/Level4.fxml"));
        String image = getClass().getClassLoader().getResource("Source/Level4.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonLv4.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Level 4");
    }

    public void onbtnLevel5(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/Level5.fxml"));
        String image = getClass().getClassLoader().getResource("Source/Level5.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonLv5.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Level 5");
    }

    public void onbtnLevel6(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/Level6.fxml"));
        Stage stage = (Stage) buttonLv6.getScene().getWindow();
        String image = getClass().getClassLoader().getResource("Source/Level3.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        stage.setScene(new Scene(root));
        stage.setTitle("Level 6");
    }

    public void onbtnLevel7(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/Level7.fxml"));
        String image = getClass().getClassLoader().getResource("Source/Level5.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonLv7.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Level 7");
    }


}