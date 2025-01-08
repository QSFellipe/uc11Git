import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    Connection conn;
    PreparedStatement prep;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor) VALUES (?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro: " + sqle.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }
}
