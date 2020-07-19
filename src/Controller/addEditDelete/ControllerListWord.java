package Controller.addEditDelete;


import Model.AddWord;
import Model.Connect_db;

import Model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerListWord implements Initializable {

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
    private Button buttonSearch;
    @FXML
    private TextField textField;
    ObservableList<Word> observableList = FXCollections.observableArrayList();

    @FXML
    public void onBack(javafx.event.ActionEvent event) {
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
        tableView.setEditable(true);
//        connect_db.setSql("select * from Word ORDER BY WordEn");
//        connect_db.Connect_db();
//
//        String tiengAnh = "";
//        String tiengViet = "";
//        String hashtag = "";
//        String goiy = "";
//        String spelling = "";
//        String typeOfWord = "";
//        String date = "";
//
//
//        String list = "";
//        int i = 1; // Biến này dùng để đánh stt trong danh sách
//        while (true) { //Bước nhảy sang từ tiếp sau nó
//            try {
//                if (!connect_db.resultSet.next()) break;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                //Lấy thông tin của từ/cụm từ
//                tiengAnh = connect_db.resultSet.getString("WordEn");
//                tiengViet = connect_db.resultSet.getString("WordVi");
//                hashtag = connect_db.resultSet.getString("Hashtag");
//                goiy = connect_db.resultSet.getString("goiy");
//                date = connect_db.resultSet.getString("date");
//                spelling = connect_db.resultSet.getString("spelling");
//                typeOfWord = connect_db.resultSet.getString("typeOfWord");
//
//                list =    "Tiếng Anh: " + tiengAnh + "   " +
//                        "     " + "Tiếng Việt: " + tiengViet + "    " +
//                        "    " + "Hash tag: " + hashtag + "    " +
//                        "    " + "Từ gợi ý: " + goiy + "   " +
//                        "    " + "ngày : " + date + "   " +
//                        "     " + " phát âm: " + spelling + "     " +
//                        "   " + "kiểu từ : " + typeOfWord + "     " +
//                        "    " +
//                        "      " +
//                            i++;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //SetTexkArea
//        textArea.setText(list);
//
////        //Set Combobox
////        comboBox.setItems(listSearch);
        try {
            connect_db.setSql("select * from Word ");
            connect_db.Connect_db();
            while (connect_db.resultSet.next()) {
                observableList.add(
                        new Word(connect_db.resultSet.getString("WordEn"),
                                connect_db.resultSet.getString("WordVi"),
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
        c_phatam.setCellValueFactory(new PropertyValueFactory<>("spelling"));
        c_loaitu.setCellValueFactory(new PropertyValueFactory<>("typeOfWord"));
        c_ngay.setCellValueFactory(new PropertyValueFactory<>("date"));
        c_hashtag.setCellValueFactory(new PropertyValueFactory<>("Hashtag"));
        c_goiy.setCellValueFactory(new PropertyValueFactory<>("goiy"));
        tableView.setItems(observableList);
    }
}
