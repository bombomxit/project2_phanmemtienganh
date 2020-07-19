package Model;

import java.sql.*;

public class ShowWord {
    Connection conn;
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void ShowWord() {
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
            ResultSet result = statement.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
