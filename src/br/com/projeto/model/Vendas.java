package br.com.projeto.model;

/**
 * Classe criada para 
 * @author Adilson Luz
 * @since Classe Criada em 06/07/2021, 17:35:56
 */
public class Vendas {
    
    //Atributos
    private int id;
    private Clientes cliente;
    private String data_venda;
    private double total_venda;
    private String obs;
    
    //Construtor vazio
    public Vendas() {
    }
    
    //Metodo construtor
    public Vendas(int id, Clientes cliente, String data_venda, double total_venda, String obs) {
        this.id = id;
        this.cliente = cliente;
        this.data_venda = data_venda;
        this.total_venda = total_venda;
        this.obs = obs;
    }
    
    
    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public double getTotal_venda() {
        return total_venda;
    }

    public void setTotal_venda(double total_venda) {
        this.total_venda = total_venda;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
}//fim da classe
