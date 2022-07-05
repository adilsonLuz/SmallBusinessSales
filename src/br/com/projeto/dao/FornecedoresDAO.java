package br.com.projeto.dao;

import br.com.projeto.Resources.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe criada para instanciar os metodos que controlam 
 * as funções de fornecedores no banco de dados do sistema
 * 
 * @author Adilson Luz
 * @since Classe Criada em 05/07/2021, 16:08:50
 */
public class FornecedoresDAO {
    
    private Connection conn;

    //Cria conexão com banco de dados
    public FornecedoresDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    //Método para cadastrar Fornecedores
    public void cadastrarFornecedores(Fornecedores obj) {
        try {
            //Criar comando SQL
            String sql = "INSERT INTO tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, "
                    + "complemento, bairro, cidade, estado)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            //Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            //Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    
    //Método para alterar Fornecedores
    public void alterarFornecedores(Fornecedores obj) {
        try {
            //Criar comando SQL
            String sql = "UPDATE tb_fornecedores SET nome=?, cnpj=?, email=?, telefone=?,"
                    + " celular=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?,"
                    + " cidade=?, estado=? WHERE id=?";

            //Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());            
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            stmt.setInt(13, obj.getId());

            //Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    
    //Método para excluir Fornecedores
    public void excluirFornecedores(Fornecedores obj) {
        try {
            //Criar comando SQL
            String sql = "DELETE FROM tb_fornecedores WHERE id = ?";

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

    //Método que lista todos os Fornecedores
    public List<Fornecedores> listarFornecedores() {
        try {
            //Criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_fornecedores ORDER BY nome ASC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }

    }

    //Método consultar Fornecedores por nome
    public Fornecedores consultarFornecedoresPorNome(String nome) {
        try {
            //Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_fornecedores WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            Fornecedores obj = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedore não encontrado");
            return null;
        }
    }

    //Método que busca Fornecedores por nome
    public List<Fornecedores> buscarFornecedoresPorNome(String nome) {
        try {
            //Criar a lista
            List<Fornecedores> lista = new ArrayList<>();

            //Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_fornecedores WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }

    }


}//fim da classe