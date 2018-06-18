package maple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBOperation {

    public static Statement state;
    public static Connection conn;
    public static ResultSet rs;

    public static Connection get_connection() {
        
        String ServerIP="127.0.0.1";
        String ServerPort="3306";
        String DATABASE_NAME="maplestory";
        
        String url = "jdbc:mysql://" + ServerIP + ":" + ServerPort + "/" + DATABASE_NAME + "?characterEncoding=UTF-8&useSSL=false";
        String username = "root";
        String password = "";
        if (conn == null) {
            try {
                //產生一個驅動程式物件  否則連不上資料庫 ，捕捉ClassNotFoundException
                Class.forName("com.mysql.jdbc.Driver"); 
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("連線成功...");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("連線失敗...");
                System.out.println(e);
            }
        }
        return conn;
    }

    public static ArrayList<mapleBean> getCHRList() {
        ArrayList<mapleBean> CHRs = new ArrayList<>();
        String sql = "SELECT * FROM characters";
        try {
            state = get_connection().createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                mapleBean chr = new mapleBean();
                chr.setId(rs.getString("id"));
                chr.setName(rs.getString("name"));
                chr.setLevel(rs.getInt("level"));
                chr.setFame(rs.getInt("fame"));
                chr.setMeso(rs.getInt("meso"));
                CHRs.add(chr);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return CHRs;
    }


    //先在此做個連線測試
    public static void main(String[] args) throws Exception {
         System.out.println(DBOperation.getCHRList());
        for (mapleBean o : DBOperation.getCHRList()) {
            System.out.println(o.getId());
        }
    }
}
