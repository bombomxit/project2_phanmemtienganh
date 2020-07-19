package Controller.addEditDelete;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAddEditDelete {
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonListWord;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonEdit;

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



    public void setButtonListWord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/addEditDelete/listWord.fxml"));
//        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
//        root.setStyle("-fx-background-image: url('" + image + "');" +
//                "-fx-background-position: center center;" +
//                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonListWord.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("danh sách");

    }

    public void setButtonEdit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/addEditDelete/editWord.fxml"));
//        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
//        root.setStyle("-fx-background-image: url('" + image + "');" +
//                "-fx-background-position: center center;" +
//                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonEdit.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Sửa từ");
    }

//    public void setButtonDelete(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/addEditDelete/DeleteWord.fxml"));
//        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
//        root.setStyle("-fx-background-image: url('" + image + "');" +
//                "-fx-background-position: center center;" +
//                "-fx-background-repeat: stretch;");
//        Stage stage = (Stage) buttonDelete.getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.setTitle("thêm từ");
//    }
}
