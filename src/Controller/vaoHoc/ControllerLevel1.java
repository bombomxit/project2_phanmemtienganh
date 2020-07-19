package Controller.vaoHoc;

import Controller.process.LongestCommonSubstring;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerLevel1 implements Initializable {
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtBetWeen;
    @FXML
    private TextField txtEnd;
    @FXML
    private ImageView imageView;
    @FXML
    private TextArea textArea;
    @FXML
    private Button btnBack;

    @FXML
    public void onBack(ActionEvent event) {
        Parent root1 = null;
        try {
            //lấy scence cũ
            root1 = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/EnglishFighter.fxml"));

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
    Connect_db connect_db = new Connect_db();
    String [] arr;
    String first;
    String end;
    String between;
    String tiengAnh;
    String linkMp3="";
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String sql=" SELECT TOP 1 * FROM Word order by newid() ";
        connect_db.setSql(sql);
        connect_db.Connect_db();

         tiengAnh = "";
        String tiengViet = "";
        String hashtag = "";
        String goiy = "";
        String spelling = "";
        String typeOfWord = "";
        String date = "";
        String linkImg ="";

        String list = "";
        int i = 1; // Biến này dùng để đánh stt trong danh sách
        while (true) { //Bước nhảy sang từ tiếp sau nó
            try {
                if (!connect_db.resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                //Lấy thông tin của từ/cụm từ
                tiengAnh = connect_db.resultSet.getString("WordEn");

                String a = "";
                String[] arrWord = tiengAnh.split(" ");
                int c = arrWord.length;
                for (String word : arrWord) {
                    a = a + word.substring(0, 1) + word.substring(1) ;
                }
                int b = a.length();
                linkImg = connect_db.resultSet.getString("linkImg");
                tiengViet = connect_db.resultSet.getString("WordVi");
                goiy = connect_db.resultSet.getString("goiy");
                date = connect_db.resultSet.getString("date");
                spelling = connect_db.resultSet.getString("spelling");
                typeOfWord = connect_db.resultSet.getString("typeOfWord");
                linkMp3 = connect_db.resultSet.getString("linkMp3");

                list = list   +
                          "Tiếng Việt: " + tiengViet + "\n" +

                         "Từ gợi ý: " + goiy +
                        "\n" + "ngày : " + date +
                        "\n" + " phát âm: " + spelling  +
                        "\n" + "kiểu từ : " + typeOfWord + "\n"+
                    "từ/cụm từ này có "+ b + " chữ cái"
                        +"\n"
                    +" có " +c +" từ";
                ;



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        textArea.setText(list);
        arr = tiengAnh.split("");
        first = arr[0];

        end = arr[arr.length-1];

        txtBegin.setText(first);
        txtEnd.setText(end);


        File file = new File(linkImg);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

@FXML
    public void onBtnSubmit(ActionEvent event) {

    String w = txtBetWeen.getText();String word = first+w+end;
    if(word.equals(tiengAnh)){
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("chúc mừng");
        alert.setHeaderText("Bạn đã hoàn thành");
        alert.show();
        Parent root1 = null;
        try {
            //lấy scence cũ
            root1 = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/EnglishFighter.fxml"));

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
    }else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("chia buồn");
        alert.setHeaderText("Bạn  không hoàn thành");
        alert.show();
        txtBetWeen.setText("");
    }

    }

    public void onBtnListien(ActionEvent event) {
        String path = linkMp3;

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);

    }

    public void onGoiy1(ActionEvent event) {
        String w = txtBetWeen.getText();
        String word = first+w+end;
        if (!word.equals(tiengAnh)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 1");
            alert.setHeaderText("Bạn  đã nhập sai không phải " + word );
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 1");
            alert.setHeaderText("Bạn đã nhập đúng mà không cần gợi ý");
            alert.show();
        }
    }

//    public void onGoiy2(ActionEvent event) {String word = txtBetWeen.getText();
//        if (!word.equals(tiengAnh)) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("gợi ý 2");
//            alert.setHeaderText(" bạn đã nhập sai \n xâu con chung dài nhất là" +longestCommonSubstring.getLongestCommonSubstring(word,tiengAnh) );
//            alert.show();
//
//        }
//        else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("gợi ý 2");
//            alert.setHeaderText("Bạn đã nhập đúng mà không cần gợi ý");
//            alert.show();
//        }
//    }



    public void onGoiy4(ActionEvent event) {
        String w = txtBetWeen.getText();
        String word = first+w+end;
        if (!word.equals(tiengAnh)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 2");
            alert.setHeaderText(" bạn đã nhập sai ");
            alert.show();

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 2");
            alert.setHeaderText("Bạn đã nhập đúng mà không cần gợi ý");
            alert.show();
        }
    }
}
