package br.com.projeto.model;

/**
 * Classe criada para 
 * @author Adilson Luz
 * @since Classe Criada em 05/07/2021, 16:04:51
 */
public class Fornecedores extends Clientes{

    private String cnpj;
    
    //Construtor vazio
    public Fornecedores() {
    }
    
    //Metodo construtor
    public Fornecedores(String cnpj) {
        this.cnpj = cnpj;
    }
    
    //Getters & Setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    
}//fim da classe
