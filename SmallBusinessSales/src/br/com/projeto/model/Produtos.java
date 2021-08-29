package br.com.projeto.model;

/**
 * Classe criada para 
 * @author Adilson Luz
 * @since Classe Criada em 05/07/2021, 18:05:27
 */
public class Produtos {
    
    //Atributos
    private int id;        
    private String status;
    private String dataCadastro;
    private String nomeProd;
    private int qtd;
    private String descricao;
    private int EstoqueMin;
    private int EstoqueMax;
    private double precoComprar;
    private double precoVender;
    private double fator;
    private String codBarras;
    private byte[] imagem;

    private Fornecedores fornecedor;
    
    //Construtor vazio
    public Produtos() {
    }
    
    
    //Método construtor
    public Produtos(int id, String status, String dataCadastro, String nomeProd,
                    int qtd, String descricao, int EstoqueMin, int EstoqueMax, 
                    double precoComprar, double precoVender, double fator, 
                    String codBarras, byte[] imagem, Fornecedores fornecedor) {    
        this.id = id;
        this.status = status;
        this.dataCadastro = dataCadastro;
        this.nomeProd = nomeProd;
        this.qtd = qtd;
        this.descricao = descricao;
        this.EstoqueMin = EstoqueMin;
        this.EstoqueMax = EstoqueMax;
        this.precoComprar = precoComprar;
        this.precoVender = precoVender;
        this.fator = fator;
        this.codBarras = codBarras;
        this.imagem = imagem;
        this.fornecedor = fornecedor;
    }

    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoqueMin() {
        return EstoqueMin;
    }

    public void setEstoqueMin(int EstoqueMin) {
        this.EstoqueMin = EstoqueMin;
    }

    public int getEstoqueMax() {
        return EstoqueMax;
    }

    public void setEstoqueMax(int EstoqueMax) {
        this.EstoqueMax = EstoqueMax;
    }

    public double getPrecoComprar() {
        return precoComprar;
    }

    public void setPrecoComprar(double precoComprar) {
        this.precoComprar = precoComprar;
    }

    public double getPrecoVender() {
        return precoVender;
    }

    public void setPrecoVender(double precoVender) {
        this.precoVender = precoVender;
    }

    public double getFator() {
        return fator;
    }

    public void setFator(double fator) {
        this.fator = fator;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }
    
}//fim da classe
