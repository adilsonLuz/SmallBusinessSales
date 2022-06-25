package br.com.projeto.dao;

import br.com.projeto.Resources.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe criada para instanciar os metodos que controlam 
 * as funções de produtos no banco de dados do sistema
 *
 * @author Adilson Luz
 * @since Classe Criada em 05/07/2021, 19:01:29
 */
public class ProdutosDAO {

    private Connection conn;

    public ProdutosDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    //Método para cadastrar Produtos
    public void cadastrarProdutos(Produtos obj) {
        try {
            //Criar comando SQL
            String sql = "INSERT INTO tb_produtos (status, data_cadastro, produto, qtd_estoque, "
                    + "descricao, estoque_minimo, estoque_maximo, preco_compra, preco_venda, "
                    + "fator, cod_barras, imagem, for_id)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getStatus());
            stmt.setString(2, obj.getDataCadastro());
            stmt.setString(3, obj.getNomeProd());
            stmt.setInt(4, obj.getQtd());
            stmt.setString(5, obj.getDescricao());
            stmt.setInt(6, obj.getEstoqueMin());
            stmt.setInt(7, obj.getEstoqueMax());
            stmt.setDouble(8, obj.getPrecoComprar());
            stmt.setDouble(9, obj.getPrecoVender());
            stmt.setDouble(10, obj.getFator());
            stmt.setString(11, obj.getCodBarras());
            stmt.setBytes(12, obj.getImagem());
            stmt.setInt(13, obj.getFornecedor().getId());

            //Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro.... " + e);
        }
    }//Fim do metodo

    //Método para alterar Produtos
    public void alterarProdutos(Produtos obj) {
        try {
            //Criar comando SQL
            String sql = "UPDATE tb_produtos SET status = ?, data_cadastro = ?, produto = ?, qtd_estoque = ?,"
                    + "descricao = ?, estoque_minimo = ?, estoque_maximo = ?, preco_compra = ?, preco_venda = ?,"
                    + "fator = ?, cod_barras = ?, imagem = ?, for_id = ? WHERE id = ?";

            //Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getStatus());
            stmt.setString(2, obj.getDataCadastro());
            stmt.setString(3, obj.getNomeProd());
            stmt.setInt(4, obj.getQtd());
            stmt.setString(5, obj.getDescricao());
            stmt.setInt(6, obj.getEstoqueMin());
            stmt.setInt(7, obj.getEstoqueMax());
            stmt.setDouble(8, obj.getPrecoComprar());
            stmt.setDouble(9, obj.getPrecoVender());
            stmt.setDouble(10, obj.getFator());
            stmt.setString(11, obj.getCodBarras());
            stmt.setBytes(12, obj.getImagem());
            stmt.setInt(13, obj.getFornecedor().getId());
            stmt.setInt(14, obj.getId());

            //Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro.... " + e);
        }
    }//Fim do metodo

    //Método para excluir Produtos
    public void excluirProdutos(Produtos obj) {
        try {
            //Criar comando SQL
            String sql = "DELETE FROM tb_produtos WHERE id = ?";

            //Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            //Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método que lista todos os Produtos
    public List<Produtos> listarProdutos() {
        try {
            //Criar a lista
            List<Produtos> lista = new ArrayList<>();

            //Criar o SQL, organizar e executar
            String sql = "SELECT p.id, p.status, DATE_FORMAT(p.data_cadastro,'%d/%m/%Y') AS data, p.produto, p.qtd_estoque, p.descricao, "
                    + "p.estoque_minimo, p.estoque_maximo, p.preco_compra, p.preco_venda, p.fator, "
                    + "p.cod_barras, p.imagem, f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id) ORDER BY p.id";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setStatus(rs.getString("p.status"));
                obj.setDataCadastro(rs.getString("data"));
                obj.setNomeProd(rs.getString("p.produto"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setEstoqueMin(rs.getInt("p.estoque_minimo"));
                obj.setEstoqueMax(rs.getInt("p.estoque_maximo"));
                obj.setPrecoComprar(rs.getDouble("p.preco_compra"));
                obj.setPrecoVender(rs.getDouble("p.preco_venda"));
                obj.setFator(rs.getDouble("p.fator"));
                obj.setCodBarras(rs.getString("p.cod_barras"));
                obj.setImagem(rs.getBytes("p.imagem"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }

    }//fim do metodo

    //Método consultar Produtos por codigo de barras
    public Produtos consultarProdutosPorCodBarras(String codBarra) {
        try {
            //Criar o SQL, organizar e executar
            String sql = "SELECT p.id, p.status, DATE_FORMAT(p.data_cadastro,'%d/%m/%Y') AS data, p.produto, p.qtd_estoque, p.descricao, "
                    + "p.estoque_minimo, p.estoque_maximo, p.preco_compra, p.preco_venda, p.fator, "
                    + "p.cod_barras, p.imagem, f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id)"
                    + " WHERE p.cod_barras = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codBarra);

            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setStatus(rs.getString("p.status"));
                obj.setDataCadastro(rs.getString("data"));
                obj.setNomeProd(rs.getString("p.produto"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setEstoqueMin(rs.getInt("p.estoque_minimo"));
                obj.setEstoqueMax(rs.getInt("p.estoque_maximo"));
                obj.setPrecoComprar(rs.getDouble("p.preco_compra"));
                obj.setPrecoVender(rs.getDouble("p.preco_venda"));
                obj.setFator(rs.getDouble("p.fator"));
                obj.setCodBarras(rs.getString("p.cod_barras"));
                obj.setImagem(rs.getBytes("p.imagem"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);
            }
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
            return null;
        }
    }//fim do metodo

    //Método que busca Produtos por codigo de barras
    public List<Produtos> buscarProdutosPorCodBarras(String codBarras) {
        try {
            //Criar a lista
            List<Produtos> lista = new ArrayList<>();

            //Criar o SQL, organizar e executar
            String sql = "SELECT p.id, p.status, DATE_FORMAT(p.data_cadastro,'%d/%m/%Y') AS data, p.produto, p.qtd_estoque, p.descricao, "
                    + "p.estoque_minimo, p.estoque_maximo, p.preco_compra, p.preco_venda, p.fator, "
                    + "p.cod_barras, p.imagem, f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id) "
                    + " WHERE p.cod_barras LIKE ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, codBarras);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setStatus(rs.getString("p.status"));
                obj.setDataCadastro(rs.getString("data"));
                obj.setNomeProd(rs.getString("p.produto"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setEstoqueMin(rs.getInt("p.estoque_minimo"));
                obj.setEstoqueMax(rs.getInt("p.estoque_maximo"));
                obj.setPrecoComprar(rs.getDouble("p.preco_compra"));
                obj.setPrecoVender(rs.getDouble("p.preco_venda"));
                obj.setFator(rs.getDouble("p.fator"));
                obj.setCodBarras(rs.getString("p.cod_barras"));
                obj.setImagem(rs.getBytes("p.imagem"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }
    }//fim do metodo
    
    //Método que busca Produtos por nome
    public List<Produtos> buscarProdutosPorNome(String nome) {
        try {
            //Criar a lista
            List<Produtos> lista = new ArrayList<>();

            //Criar o SQL, organizar e executar
            String sql = "SELECT p.id, p.status, DATE_FORMAT(p.data_cadastro,'%d/%m/%Y') AS data, p.produto, p.qtd_estoque, p.descricao, "
                    + "p.estoque_minimo, p.estoque_maximo, p.preco_compra, p.preco_venda, p.fator, "
                    + "p.cod_barras, p.imagem, f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id) "
                    + " WHERE p.produto LIKE ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setStatus(rs.getString("p.status"));
                obj.setDataCadastro(rs.getString("data"));
                obj.setNomeProd(rs.getString("p.produto"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setEstoqueMin(rs.getInt("p.estoque_minimo"));
                obj.setEstoqueMax(rs.getInt("p.estoque_maximo"));
                obj.setPrecoComprar(rs.getDouble("p.preco_compra"));
                obj.setPrecoVender(rs.getDouble("p.preco_venda"));
                obj.setFator(rs.getDouble("p.fator"));
                obj.setCodBarras(rs.getString("p.cod_barras"));
                obj.setImagem(rs.getBytes("p.imagem"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }
    }//fim do metodo
    

    //Método consultar Produtos por nome (usado no FrmVendas)
    public Produtos consultarProdutosPorCodigoBarras(String CodBar) {
        try {
            //Criar o SQL, organizar e executar
            String sql = "SELECT p.id, p.status, DATE_FORMAT(p.data_cadastro,'%d/%m/%Y') AS data, p.produto, p.qtd_estoque, p.descricao, "
                    + "p.estoque_minimo, p.estoque_maximo, p.preco_compra, p.preco_venda, p.fator, "
                    + "p.cod_barras, p.imagem, f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id) "
                    + " where p.cod_barras = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, CodBar);

            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setStatus(rs.getString("p.status"));
                obj.setDataCadastro(rs.getString("data"));
                obj.setNomeProd(rs.getString("p.produto"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setEstoqueMin(rs.getInt("p.estoque_minimo"));
                obj.setEstoqueMax(rs.getInt("p.estoque_maximo"));
                obj.setPrecoComprar(rs.getDouble("p.preco_compra"));
                obj.setPrecoVender(rs.getDouble("p.preco_venda"));
                obj.setFator(rs.getDouble("p.fator"));
                obj.setCodBarras(rs.getString("p.cod_barras"));
                obj.setImagem(rs.getBytes("p.imagem"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }
    }//fim do metodo

    //Metodo que da baixa no estoque
    public void baixaEstoque(int id, int qtd_nova) {
        try {
            //Criar o comando SQL
            String sql = "UPDATE tb_produtos SET qtd_estoque=? WHERE id=?";

            //Conectar com banco de dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);

            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }//fim do metodo

    //Método que retorna o estoque atual de um produto
    public int retornaEstoqueAtual(int id) {
        try {
            int qtd_estoque = 0;

            //Criar o comando SQL
            String sql = "SELECT qtd_estoque FROM tb_produtos WHERE id=?";

            //Organizar e executar o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produtos p = new Produtos();

                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//fim do metodo

    //Metodo que adiciona uma quantidade no estoque
    public void adicionarEstoque(int id, int qtd_nova) {
        try {
            //Criar o comando SQL
            String sql = "UPDATE tb_produtos SET qtd_estoque=? WHERE id=?";

            //Conectar com banco de dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);

            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }//fim do metodo
    
    
}//fim da classe
