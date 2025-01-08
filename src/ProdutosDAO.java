import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    Connection conn;
    PreparedStatement prep;
    private conectaDAO conexao;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO() throws ClassNotFoundException {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();     
    }
    
    public void cadastrarProduto(ProdutosDTO produto) {

        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro: " + sqle.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }
}
