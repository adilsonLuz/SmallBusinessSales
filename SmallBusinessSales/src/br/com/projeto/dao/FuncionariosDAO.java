package br.com.projeto.dao;

import br.com.projeto.Resources.ConnectionFactory;
import br.com.projeto.model.Funcionarios;
import br.com.projeto.view.FrmFuncionarios;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 * Classe criada para
 *
 * @author Adilson Luz
 * @since Classe Criada em 04/07/2021, 17:07:32
 */
public class FuncionariosDAO {

    private Connection conn;

    public FuncionariosDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    //Metodo cadastrar um funcionario
    public void cadastrarFuncionario(Funcionarios obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "INSERT INTO tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, "
                    + "celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivelAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método para alterar um funcionario
    public void alterarFuncionario(Funcionarios obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "UPDATE tb_funcionarios SET nome=?, rg=?, cpf=?, email=?, senha=?,"
                    + " cargo=?, nivel_acesso=?, telefone=?, celular=?, cep=?, endereco=?, "
                    + "numero=?, complemento=?, bairro=?, cidade=?, estado=? WHERE id=?";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivelAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            stmt.setInt(17, obj.getId());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método para excluir um funcionario
    public void excluirFuncionario(Funcionarios obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "DELETE FROM tb_funcionarios WHERE id = ?";

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

    //Método que lista todos os funcionarios
    public List<Funcionarios> listarFuncionarios() {
        try {
            //1 Passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 Passo - Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_funcionarios";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
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

    //Método consultar funcionario por nome
    public Funcionarios consultarFuncionarioPorNome(String nome) {
        try {
            //1 Passo - Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_funcionarios WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            Funcionarios obj = new Funcionarios();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
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

    //Método que busca funcionario por nome
    public List<Funcionarios> buscarFuncionarioPorNome(String nome) {
        try {
            //1 Passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 Passo - Criar o SQL, organizar e executar
            String sql = "SELECT * FROM tb_funcionarios WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
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

    //Método para efetuar o login
    public void efetuarLogin(String email, String senha) {

        Icon icon = new javax.swing.ImageIcon(getClass().getResource("/imagens/iconOk_1.png"));
        try {
            //1 Passo - Comando SQL
            String sql = "SELECT * FROM tb_funcionarios WHERE email=? AND senha=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //caso usuario seja do tipo ADMINISTRADOR
                if (rs.getString("nivel_acesso").equals("Administrador")) {                    
                    JOptionPane.showMessageDialog(null, "Acesso Permitido", "SEJA BEM VINDO AO SISTEMA",
                            JOptionPane.INFORMATION_MESSAGE, icon);
                    FrmMenu tela = new FrmMenu();                    
                    tela.usuarioLogado = rs.getString("nome");                   

                    tela.setVisible(true);
                } //caso o usuario seja do tipo limitado
                else if (rs.getString("nivel_acesso").equals("Usuario")) {
                    JOptionPane.showMessageDialog(null, "Acesso Permitido", "SEJA BEM VINDO AO SISTEMA",
                            JOptionPane.INFORMATION_MESSAGE, icon);
                    FrmMenu tela = new FrmMenu();       
                    FrmFuncionarios telaFunc =new FrmFuncionarios();
                    tela.usuarioLogado = rs.getString("nome");                   
                    
                    //Desabilitar os menus
                    tela.menu_posicao.setEnabled(false);
                    tela.menu_controleVenda.setVisible(false);
                    tela.menu_ConsFunc.setEnabled(false);

                    tela.setVisible(true);
                }

            } else {
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados Incorretos!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
                new FrmLogin().setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }

    }

}//fim da classe
