package br.com.projeto.model;

/**
 * Classe criada para 
 * @author Adilson Luz
 * @since Classe Criada em 08/07/2021, 15:01:56
 */
public class ItemVenda {
    
    //Atributos
    private int id;
    private Vendas venda;
    private Produtos produto;
    private int qtd;
    private double subtotal;
    
    //Contrutor vazio
    public ItemVenda() {
    }
        
    //Metodo construtor
    public ItemVenda(int id, Vendas venda, Produtos produto, int qtd, double subtotal) {
        this.id = id;
        this.venda = venda;
        this.produto = produto;
        this.qtd = qtd;
        this.subtotal = subtotal;
    }
    
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    

}//fim da classe
