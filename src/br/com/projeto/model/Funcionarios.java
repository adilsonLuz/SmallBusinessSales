package br.com.projeto.model;

/**
 * Classe criada para modelar o objeto funcionarios 
 * herdando da classe model clientes
 * 
 * @author Adilson Luz
 * @since Classe Criada em 04/07/2021, 17:02:15
 */
public class Funcionarios extends Clientes {
    
    //Atributos
    private String senha;
    private String cargo;
    private String nivelAcesso;

    //Metodo construtor vazio
    public Funcionarios() {
    }//fim do metodo

    //Metodo construtor
    public Funcionarios(String senha, String cargo, String nivelAcesso) {
        this.senha = senha;
        this.cargo = cargo;
        this.nivelAcesso = nivelAcesso;
    }//fim do metodo
    
    
    //Getters & Setters
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }    
    
    
}//fim da classe
