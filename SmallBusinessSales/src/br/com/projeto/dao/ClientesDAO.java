package br.com.projeto.dao;

import br.com.projeto.Resources.ConnectionFactory;
import br.com.projeto.model.Clientes;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe criada para instanciar os metodos que controlam as funções do banco de
 * dados
 *
 * @author Adilson Luz
 * @since Classe Criada em 03/07/2021, 18:36:31
 */
public class ClientesDAO {

    private Connection conn;

    public ClientesDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    //Método para cadastrar clientes
    public void cadastrarCliente(Clientes obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "INSERT INTO tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, "
                    + "complemento, bairro, cidade, estado)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método para alterar clientes
    public void alterarCliente(Clientes obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "UPDATE tb_clientes SET nome=?, rg=?, cpf=?, email=?, telefone=?,"
                    + " celular=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?,"
                    + " cidade=?, estado=? WHERE id=?";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            stmt.setInt(14, obj.getId());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método para excluir clientes
    public void excluirCliente(Clientes obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "DELETE FROM tb_clientes WHERE id = ?";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método que lista todos os Clientes
    public List<Clientes> listarClientes() {
        try {
            //1 Passo criar a lista
            List<Clientes> lista = new ArrayList<>();

            //2 Passo - Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes obj = new Clientes();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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

    //Método consultar cliente por nome
    public Clientes consultarPorNome(String nome) {
        try {
            //1 Passo - Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_clientes WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            Clientes obj = new Clientes();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }
    }

    //Método que busca Cliente por nome
    public List<Clientes> buscarClientePorNome(String nome) {
        try {
            //1 Passo criar a lista
            List<Clientes> lista = new ArrayList<>();

            //2 Passo - Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes obj = new Clientes();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    //Método consultar cliente por nome
    public Clientes consultarPorCpf(String cpf) {
        try {
            //1 Passo - Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_clientes WHERE cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            Clientes obj = new Clientes();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }
    }


}//fim da classe
