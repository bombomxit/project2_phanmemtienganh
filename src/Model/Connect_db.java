package Model;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;
import java.util.ArrayList;

public class Connect_db {
    public ResultSet resultSet;
    private Connection conn;
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void Connect_db() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=project2";// your db name
            String user = "sa"; // your db username
            String password = "sa"; // your db password
//            String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
//                    "databaseName=project2;username=sa;password=sa;";
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connect success!");
            }


            PreparedStatement statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Connect_db();
    }

    //    public boolean addWord(Word word){
//    String sql = "INSERT INTO project2(ID, WordEn, WordVi, linkImg, linkMp3, spelling, typeOfWord)" +
//            " VALUES(?,?,?,?,?,?,?)";
//    try {
//
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1,word.getID());
//        ps.setString(2,word.getWordEn());
//        ps.setString(3,word.getWordVi());
//        ps.setString(4,word.getLinkImg());
//        ps.setString(5,word.getLinkMp3());
//        ps.setString(6,word.getSpelling());
//        ps.setString(7,word.getTypeOfWord());
//        return  ps.executeUpdate() >0;
//
//    }
//    catch (Exception e){
//
//    }
//
//    return false;
//
//        //
//    }
//public ArrayList<Word> getListWord(){
//    ArrayList<Word> list = new ArrayList<>();
//    String sql = " SELECT  * FROM project2";
//try {
//PreparedStatement ps = conn.prepareStatement(sql);
//ResultSet rs= ps.executeQuery();
//while (rs.next()){
//    Word word = new Word();
//    word.setID(rs.getInt("ID"));
//    word.setWordEn(rs.getString("WordEn"));
//    word.setWordVi(rs.getString("WordVi"));
//    word.setLinkImg(rs.getString("LinkImg"));
//    word.setLinkMp3(rs.getString("LinkMp3"));
//    word.setSpelling(rs.getString("spelling"));
//    word.setTypeOfWord(rs.getString("typeOfWord"));
//list.add(word);
//
//}
//}
//
//catch (SQLException e) {
//    e.printStackTrace();
//}
//    return  list;
//}
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
