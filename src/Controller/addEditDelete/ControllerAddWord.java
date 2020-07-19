package Controller.addEditDelete;

import Model.AddWord;
import Model.Connect_db;
import Model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ControllerAddWord implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private Button buttonChonTepAnh;
    @FXML
    private Button buttonChonTepMp3;
    @FXML
    private TextField txtWordEn;
    @FXML
    private TextField txtWordVi;
    @FXML
    private TextField txtLinkImg;
    @FXML
    private TextField txtLinkMp3;
    @FXML
    private TextField txtSpelling;
    @FXML
    private TextField txtTypeOfWord;
    @FXML
    private Button btnAdd;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txthashtag;
    @FXML
    private TextField txtgoiy;
    private AddWord addWord = new AddWord();

    @FXML
    public void onBack(ActionEvent event) {
        Parent root1 = null;
        try {
            //lấy scence cũ
            root1 = FXMLLoader.load(getClass().getClassLoader().getResource("View/addEditDelete/editWord.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //lấy Stage cũ để set Scene cũ
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // set lại background cho Scene cũ
        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();

        // lấy Stage cũ để set Scene cũ : quay trở lại màn hình chính
        stage.setTitle("English Fighter");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
    }

    @FXML
    public void setButtonChonTepAnh(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        FileChooser.ExtensionFilter imgameFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        //Chỉ cho người dùng chọn file ảnh không cho chọn file khác
        fileChooser.getExtensionFilters().add(imgameFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            txtLinkImg.setText(file.getPath());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Bạn chưa chọn tệp ảnh");
            alert.show();
        }


//        if(file != null){
//            Image image = new Image(file.toURI().toString());
//            imv.setImage(image);
//        }

//        String localUrl = null;
        try {
            txtLinkImg.setText(file.getPath());
//
            Image image = new Image(file.toURL().toString());
            imageView.setImage(image);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText("Bạn chưa chọn ảnh");
            alert.show();
        }
    }

    @FXML
    public void setButtonChonTepMp3(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        FileChooser.ExtensionFilter imgameFilter = new FileChooser.ExtensionFilter("Media Files", "*.mp3", "*.m4a");
        //Chỉ cho người dùng chọn file âm thanh không cho chọn file khác
        fileChooser.getExtensionFilters().add(imgameFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            txtLinkMp3.setText(file.getPath());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Bạn chưa chọn tệp Mp3");
            alert.show();
        }

    }

    @FXML
    public void onAdd(ActionEvent event) {
//        Word word = new Word();
//        word.setID(Integer.parseInt(txtID.getText()));
//        word.setWordEn(txtWordEn.getText());
//        word.setTypeOfWord(txtTypeOfWord.getText());
//        word.setWordVi(txtWordVi.getText());
//        word.setLinkImg(txtLinkImg.getText());
//        word.setLinkMp3(txtLinkMp3.getText());
//        word.setSpelling(txtSpelling.getText());

        if (!txtWordEn.getText().isEmpty()
                && !txtWordVi.getText().isEmpty()
                && !txtgoiy.getText().isEmpty()
                && !txthashtag.getText().isEmpty()
                && !txtTypeOfWord.getText().isEmpty()
                && !txtSpelling.getText().isEmpty()) {
            String tienganh = txtWordEn.getText().trim();
            while (tienganh.contains("  ")) {
                tienganh = tienganh.replace("  ", " ");
            }
            String sql = "INSERT INTO Word VALUES (" + "N'"
                    + tienganh
                    + "',N'" + txtWordVi.getText().trim()
                    + "',N'" + txtLinkImg.getText().trim() + "',N'" +
                    txtLinkMp3.getText().trim() + "',N'"
                    + txtSpelling.getText().trim()
                    + "',N'" + txtTypeOfWord.getText().trim() +
                    "',N'" + datePicker.getValue().toString() +
                    "',N'" + txthashtag.getText().trim() +

                    "',N'" + txtgoiy.getText().trim() + "')";


            addWord.setSql(sql);
            addWord.AddWord();
            //Hiện thị thông báo để báo cho người dùng biết là họ đã thêm từ thành công
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn thêm từ thành công");
            alert.show();
//Set lại các ô điền thông tin từ
            txtWordEn.setText("");
            txtWordVi.setText("");
            txthashtag.setText("");
            txtgoiy.setText("");
            txtSpelling.setText("");
            txtSpelling.setText("");


        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn thêm từ không thành công");
            alert.show();
        }

//
    }

    public static void main(String[] args) {

    }public void onBtnListien(ActionEvent event) {
        String path = txtLinkMp3.getText();

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}


