package Controller.addEditDelete;

import Model.Connect_db;
import Model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerEdit implements Initializable {
    @FXML
    private TextField txtWordEn;
    @FXML
    private TextField txtWordVi;
    @FXML
    private TextField txtSpelling;
    @FXML
    private TextField txtTypeOfWord;
    @FXML
    private TextField txtHashtag;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtGoiy;
    @FXML
    private TextField txtLinkImg;
    @FXML
    private TextField txtLinkMp3;
    private Connect_db connect_db = new Connect_db();
    @FXML
    private TableView<Word> tableView;
    @FXML
    private TableColumn<Word, String> c_tienganh;
    @FXML
    private TableColumn<Word, String> c_tiengviet;
    @FXML
    private TableColumn<Word, String> c_phatam;
    @FXML
    private TableColumn<Word, String> c_loaitu;
    @FXML
    private TableColumn<Word, String> c_ngay;
    @FXML
    private TableColumn<Word, String> c_hashtag;
    @FXML
    private TableColumn<Word, String> c_goiy;
    @FXML
    private TableColumn<Word, String> c_linkmp3;
    @FXML
    private TableColumn<Word, String> c_linkimg;
    @FXML
    private ImageView imageView;
    @FXML
    private  Button buttonAdd;
    @FXML
    private Button buttonBack;

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

    @FXML
    private void setbtnupdate(javafx.event.ActionEvent actionEvent) {
if(!txtWordEn.getText().isEmpty()) {
    try {
        String tienganh = txtWordEn.getText().trim();
        while (tienganh.contains("  ")) {
            tienganh = tienganh.replace("  ", " ");
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=project2";// your db name
        String user = "sa"; // your db username
        String password = "sa"; // your db password
//            String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
//                    "databaseName=project2;username=sa;password=sa;";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = " update Word set WordVi=?,spelling=?,typeOfWord=?, date=?, Hashtag=?, goiy=? ,linkImg =? , linkMp3=? Where WordEn=? ";
        if (conn != null) {
            System.out.println("Connect success!");
        }
        PreparedStatement ps = null;
        ps = conn.prepareStatement(sql);
        ps.setString(1, txtWordVi.getText());
        ps.setString(2, txtSpelling.getText());
        ps.setString(3, txtTypeOfWord.getText());
        ps.setString(4, txtDate.getText());
        ps.setString(5, txtHashtag.getText());
        ps.setString(6, txtGoiy.getText());
        ps.setString(7, txtLinkImg.getText());
        ps.setString(8, txtLinkMp3.getText());
        ps.setString(9, tienganh);
        ps.executeUpdate();

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    //Hiện thị thông báo để báo cho người dùng biết là họ đã sửa từ thành công
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Thông Báo");
    alert.setHeaderText("Bạn sửa từ thành công");
    alert.show();

    //Set lại các ô điền thông tin từ
    txtWordEn.setText("");
    txtWordVi.setText("");
    txtHashtag.setText("");
    txtGoiy.setText("");
    txtSpelling.setText("");
    txtSpelling.setText("");
    txtDate.setText("");
    txtLinkImg.setText("");


}
else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Thông Báo");
    alert.setHeaderText("Bạn sửa từ ko thành công");
    alert.show();
}
    }
    ObservableList<Word> observableList = FXCollections.observableArrayList();
    ObservableList<Word> observableList1 = FXCollections.observableArrayList();
    public void onBtnListien(ActionEvent event) {
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

        try {
            connect_db.setSql("select * from Word ");
            connect_db.Connect_db();
            while (connect_db.resultSet.next()) {
                observableList.add(
                        new Word(connect_db.resultSet.getString("WordEn"),
                                connect_db.resultSet.getString("WordVi"),
                                connect_db.resultSet.getString("linkImg"),
                                connect_db.resultSet.getString("linkMp3"),
                                connect_db.resultSet.getString("spelling"),
                                connect_db.resultSet.getString("typeOfWord"),
                                 connect_db.resultSet.getString("date"),
                                   connect_db.resultSet.getString("Hashtag"),
                                connect_db.resultSet.getString("goiy")));

            }
        } catch
        (SQLException s) {
            s.printStackTrace();
        }
        c_tienganh.setCellValueFactory(new PropertyValueFactory<>("wordEn"));
        c_tiengviet.setCellValueFactory(new PropertyValueFactory<>("wordVi"));
        c_phatam.setCellValueFactory(new PropertyValueFactory<>("linkImg"));
        c_loaitu.setCellValueFactory(new PropertyValueFactory<>("linkMp3"));
        c_ngay.setCellValueFactory(new PropertyValueFactory<>("spelling"));
        c_hashtag.setCellValueFactory(new PropertyValueFactory<>("typeOfWord"));
        c_goiy.setCellValueFactory(new PropertyValueFactory<>("date"));
        c_linkimg.setCellValueFactory(new  PropertyValueFactory<>("Hashtag"));
        c_linkmp3.setCellValueFactory(new  PropertyValueFactory<>("goiy"));
        tableView.setItems(observableList);

    }
    @FXML
    private void setbtnDelete(ActionEvent event) {

        if(!txtWordEn.getText().isEmpty()) {
            try { tableView.setItems(null);
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=project2";// your db name
                String user = "sa"; // your db username
                String password = "sa"; // your db password
//            String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
//                    "databaseName=project2;username=sa;password=sa;";
                Connection conn = DriverManager.getConnection(url, user, password);
                String sql = " DELETE  from Word Where WordEn =?";
                if (conn != null) {
                    System.out.println("Connect success!");
                }
                PreparedStatement ps = null;
                ps = conn.prepareStatement(sql);

                ps.setString(1, txtWordEn.getText());
                ps.executeUpdate();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //Hiện thị thông báo để báo cho người dùng biết là họ đã sửa từ thành công
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn xóa từ thành công");
            alert.show();

            //Set lại các ô điền thông tin từ
            txtWordEn.setText("");


        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo");
            alert.setHeaderText("Bạn xóa từ ko thành công");
            alert.show();
        }

        try { tableView.setItems(null);
            connect_db.setSql("select * from Word ");
            connect_db.Connect_db();
            while (connect_db.resultSet.next()) { tableView.setItems(null);
                observableList1.add(
                        new Word(connect_db.resultSet.getString("WordEn"),
                                connect_db.resultSet.getString("WordVi"),
                                connect_db.resultSet.getString("linkImg"),
                                connect_db.resultSet.getString("linkMp3"),
                                connect_db.resultSet.getString("spelling"),
                                connect_db.resultSet.getString("typeOfWord"),
                                connect_db.resultSet.getString("date"),
                                connect_db.resultSet.getString("Hashtag"),
                                connect_db.resultSet.getString("goiy")));

            }
        } catch
        (SQLException s) {
            s.printStackTrace();
        }
        c_tienganh.setCellValueFactory(new PropertyValueFactory<>("wordEn"));
        c_tiengviet.setCellValueFactory(new PropertyValueFactory<>("wordVi"));
        c_phatam.setCellValueFactory(new PropertyValueFactory<>("linkImg"));
        c_loaitu.setCellValueFactory(new PropertyValueFactory<>("linkMp3"));
        c_ngay.setCellValueFactory(new PropertyValueFactory<>("spelling"));
        c_hashtag.setCellValueFactory(new PropertyValueFactory<>("typeOfWord"));
        c_goiy.setCellValueFactory(new PropertyValueFactory<>("date"));
        c_linkimg.setCellValueFactory(new  PropertyValueFactory<>("Hashtag"));
        c_linkmp3.setCellValueFactory(new  PropertyValueFactory<>("goiy"));
        tableView.setItems(null);
        tableView.setItems(observableList1);

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
    public void setbtnAdd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/addEditDelete/addWord.fxml"));
        String image = getClass().getClassLoader().getResource("Source/background.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("thêm từ");
    }

}
