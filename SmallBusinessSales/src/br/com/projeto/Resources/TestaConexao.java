package br.com.projeto.Resources;

import javax.swing.JOptionPane;

/**
 * Classe criada para testar a conexão com banco de dados
 * @author Adilson Luz
 * @since Classe Criada em 03/07/2021, 18:29:39
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Opss... aconteceu o erro " + e);
        }
    }

}//fim da classe
