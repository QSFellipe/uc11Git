
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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

    public ArrayList<ProdutosDTO> listarProdutos() throws ClassNotFoundException {
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try ( PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                lista.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarStatus(ProdutosDTO produtos) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");
            stmt.setInt(2, produtos.getId());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao atualizar status de venda!");
        }
    }

    ProdutosDTO getProdutosDTO(int idPesquisa) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        ProdutosDTO prod = null;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPesquisa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                prod = new ProdutosDTO();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setValor(rs.getInt("valor"));
                prod.setStatus(rs.getString("status"));

            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro: " + sqle.getMessage());
        }
        return prod;
    }
}
