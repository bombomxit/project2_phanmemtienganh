package Controller.addEditDelete;

import Model.AddWord;
import Model.Connect_db;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDelete implements Initializable {
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonBack;
    @FXML
    private TextField tienganh;
    @FXML
    private TextField tiengviet;
    @FXML
    private TextField typeOfWord;
    @FXML
    private TextField hashTag;
    @FXML
    private TextField goiy;
    private Connect_db connect_db = new Connect_db();
    private AddWord addWord = new AddWord();

    @FXML
    public void onDelete(ActionEvent event) {
        //Kiểm tra điều kiện khi người dùng chưa nhập đủ thông tin của từ/cụm từ cần xóa
        if (!tienganh.getText().isEmpty() && !typeOfWord.getText().isEmpty()
                && !hashTag.getText().isEmpty()
                && !tiengviet.getText().isEmpty()
                && !goiy.getText().isEmpty()) {  //Nếu người dùng nhập đủ sẽ thực hiện xóa từ theo câu lệnh SQL
            String cauLenhSQL = "DELETE FROM Word WHERE WordEn ='" +
                    tienganh.getText().trim() +
                    "' AND WordVi = N'" +
                    tiengviet.getText().trim() +
                    "' AND typeOfWord = N'" +
                    typeOfWord.getText().trim() +
                    "' AND goiy = N'" +
                    goiy.getText().trim() + "' AND linkImg =N'"
                    + hashTag.getText().trim() + "' ";
            addWord.setSql(cauLenhSQL);
            addWord.AddWord();

            //Hiện thị thông báo để báo cho người dùng biết là họ đã xóa từ thành công
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn xóa từ thành công");
            alert.show();

            //Set lại các ô điền thông tin từ
            hashTag.setText("");
            goiy.setText("");
            typeOfWord.setText("");
            tiengviet.setText("");
            tienganh.setText("");

        } else {
            //Nếu người dùng chưa nhập đủ thông tin từ cần xóa thì hiện thị cho người dùng thông báo
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn xóa từ không thành công\n" + "Bạn chưa điền đủ thông tin từ cần xóa!!");
            alert.show();

        }
    }

    @FXML
    public void onBack(ActionEvent event) {
        Parent root1 = null;
        try {
            //lấy scence cũ
            root1 = FXMLLoader.load(getClass().getClassLoader().getResource("View/addEditDelete/addEditDelete.fxml"));

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect_db.setSql("select * from Word");
        connect_db.Connect_db();
        tienganh.setPromptText("What");
        tiengviet.setPromptText("cái gì");
        typeOfWord.setPromptText(" từ để hỏi");
        goiy.setPromptText("W");


    }
}
