
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    public Connection connectDB() throws ClassNotFoundException{
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/uc11";
        String user = "root";
        String password = "Fal6250200407";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
}
