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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLevel7 implements Initializable {
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtBetWeen;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextArea textArea;
    @FXML
    private ImageView imageView;
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
    String[] arr;
    String first;
    String end;
    String between;
    String tiengAnh;
    String linkMp3 = "";


    public void onBtnSubmit(ActionEvent event) {String word = txtBetWeen.getText();
        if (word.equals(tiengAnh)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("chúc mừng");
            alert.setHeaderText("Bạn đã hoàn thành");
            alert.show();
            Parent root1 = null;
//            try {
////                //lấy scence cũ
////                root1 = FXMLLoader.load(getClass().getClassLoader().getResource("View/StartLearning/EnglishFighter.fxml"));
////
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            //lấy Stage cũ để set Scene cũ
////            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////            // set lại background cho Scene cũ
////            String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
////            root1.setStyle("-fx-background-image: url('" + image + "');" +
////                    "-fx-background-position: center center;" +
////                    "-fx-background-repeat: stretch;");
////            // lấy Stage cũ để set Scene cũ : quay trở lại màn hình chính
////            stage.setTitle("English Fighter");
////            stage.setScene(new Scene(root1));
////            stage.setResizable(false);//        String list = "";
//            String sql = "SELECT * FROM Word ";
//            connect_db.setSql(sql);
//            connect_db.Connect_db();

            String list="";
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
                linkImg = connect_db.resultSet.getString("linkImg");
                tiengViet = connect_db.resultSet.getString("WordVi");
                goiy = connect_db.resultSet.getString("goiy");
                date = connect_db.resultSet.getString("date");
                spelling = connect_db.resultSet.getString("spelling");
                typeOfWord = connect_db.resultSet.getString("typeOfWord");
                linkMp3 = connect_db.resultSet.getString("linkMp3");
                list =    "\n" + "ngày : " + date +

                        "\n" + "kiểu từ : " + typeOfWord + "\n" ;
                textArea.setText(list);

                File file = new File(linkImg);
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("chia buồn");
            alert.setHeaderText("Bạn  không hoàn thành");
            alert.show();
            txtBetWeen.setText("");
        }

    }
    String tiengViet = "";
    String hashtag = "";
    String goiy = "";
    String spelling = "";
    String typeOfWord = "";
    String date = "";
    String linkImg = "";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "SELECT * FROM Word ";
        connect_db.setSql(sql);
        connect_db.Connect_db();

        tiengAnh = "";



        int i = 1; // Biến này dùng để đánh stt trong danh sách
        //Bước nhảy sang từ tiếp sau nó

        String list = "";
            try {
                connect_db.resultSet.next();
                //Lấy thông tin của từ/cụm từ
                tiengAnh = connect_db.resultSet.getString("WordEn");
                linkImg = connect_db.resultSet.getString("linkImg");
                tiengViet = connect_db.resultSet.getString("WordVi");
                goiy = connect_db.resultSet.getString("goiy");
                date = connect_db.resultSet.getString("date");
                spelling = connect_db.resultSet.getString("spelling");
                typeOfWord = connect_db.resultSet.getString("typeOfWord");
                linkMp3 = connect_db.resultSet.getString("linkMp3");

                list = list+"\n" + "ngày : " + date +

                        "\n" + "kiểu từ : " + typeOfWord + "\n";


            } catch (SQLException e) {
                e.printStackTrace();
            }

        textArea.setText(list);



        File file = new File(linkImg);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void onGoiy1(ActionEvent event) {
        String word = txtBetWeen.getText();
        if (!word.equals(tiengAnh)) {String a = "";
            String[] arrWord = tiengAnh.split(" ");
            int c = arrWord.length;
            for (String d : arrWord) {
                a = a + d.substring(0, 1) + d.substring(1) ;
            }
            int b = a.length();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 1");
            alert.setHeaderText("Bạn  đã nhập sai " + word + "hãy nghe phát âm " + "từ/cụm từ này có "+ b + " chữ cái"   +"\n"
                    +" có " +c +" từ "+"\nphiên âm là "+spelling+"\ngợi ý là" +goiy +"\n dịch nghĩa là " +tiengViet);
            alert.show();
            String path = linkMp3;

            //Instantiating Media class
            Media media = new Media(new File(path).toURI().toString());

            //Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            //by setting this property to true, the audio will be played
            mediaPlayer.setAutoPlay(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 1");
            alert.setHeaderText("Bạn đã nhập đúng mà không cần gợi ý");
            alert.show();
        }
    }

    public void onGoiy2(ActionEvent event) {
        LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
        String word = txtBetWeen.getText();
        String[] arrWord = tiengAnh.split(" ");
        int c = arrWord.length;String a = "";
        for (String d : arrWord) {
            a = a + d.substring(0, 1) + d.substring(1) ;
        }
        int b = a.length();
        if ( word.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 2");
            alert.setHeaderText(" bạn đã nhập sai hãy nghe phát âm ");
            alert.show();
            String path = linkMp3;

            //Instantiating Media class
            Media media = new Media(new File(path).toURI().toString());

            //Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            //by setting this property to true, the audio will be played
            mediaPlayer.setAutoPlay(true);
        }

        else  if (!word.equals(tiengAnh) ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 2");
            alert.setHeaderText(" bạn đã nhập sai \n xâu con chung dài nhất là:" +
                    " "+ longestCommonSubstring.getLongestCommonSubstring(word,tiengAnh)+
                    "\ngợi ý là " + goiy+"từ/cụm từ này có "+ b + " chữ cái"   +"\n"
                    +" có " +c +" từ "+"\n hãy nghe phát âm");
            alert.show();
            String path = linkMp3;

            //Instantiating Media class
            Media media = new Media(new File(path).toURI().toString());

            //Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            //by setting this property to true, the audio will be played
            mediaPlayer.setAutoPlay(true);
        }


        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 2");
            alert.setHeaderText("Bạn đã nhập đúng mà không cần gợi ý");
            alert.show();
        }
    }

    public void onGoiy3(ActionEvent event) {
        String word = txtBetWeen.getText();
        if (!word.equals(tiengAnh)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 3");
            alert.setHeaderText(" bạn đã nhập sai hãy nghe phát âm ");
            alert.show();
            String path = linkMp3;

            //Instantiating Media class
            Media media = new Media(new File(path).toURI().toString());

            //Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            //by setting this property to true, the audio will be played
            mediaPlayer.setAutoPlay(true);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 3");
            alert.setHeaderText("Bạn đã nhập đúng mà không cần gợi ý");
            alert.show();
        }

    }

    public void onGoiy4(ActionEvent event) {String word = txtBetWeen.getText();
        if (!word.equals(tiengAnh)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 4");
            alert.setHeaderText(" bạn đã nhập sai hãy nghe phát âm \n phiên âm là " +spelling);
            alert.show();
            String path = linkMp3;
            //Instantiating Media class
            Media media = new Media(new File(path).toURI().toString());

            //Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            //by setting this property to true, the audio will be played
            mediaPlayer.setAutoPlay(true);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("gợi ý 4");
            alert.setHeaderText("Bạn đã nhập đúng mà không cần gợi ý");
            alert.show();

        }
    }
}