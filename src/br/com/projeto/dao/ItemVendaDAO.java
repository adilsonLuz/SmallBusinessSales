package br.com.projeto.dao;

import br.com.projeto.Resources.ConnectionFactory;
import java.sql.Connection;
import br.com.projeto.model.ItemVenda;
import br.com.projeto.model.Produtos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe criada para instanciar os metodos que controlam
 * as funções de itens vendidos no banco de dados
 * 
 * @author Adilson Luz
 * @since Classe Criada em 09/07/2021, 12:26:34
 */
public class ItemVendaDAO {
    
    private Connection conn;

    //Cira conexão com banco de dados
    public ItemVendaDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    //Metodo que cadastra item
    public void cadastraItem(ItemVenda obj){
        
        try {
            //Criar comando SQL
            String sql = "INSERT INTO tb_itensvendas (venda_id, produto_id, qtd, subtotal)"
                       + "VALUES (?,?,?,?)";

            //Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            //Executar o comando SQL
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }        
    }
    
    //Metodo que lista itens de uma venda por ID
    public List<ItemVenda> listarItensPorVenda(int venda_id) {
        try {
            //Criar a lista
            List<ItemVenda> lista = new ArrayList<>();

            //Criar o SQL, organizar e executar
            String sql = "SELECT p.descricao, i.qtd, p.preco_venda, i.subtotal FROM tb_itensvendas AS i "
                       + " INNER JOIN tb_produtos AS p ON (i.produto_id = p.id)"
                       + " WHERE i.venda_id = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, venda_id);            
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();
                
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));  
                prod.setPrecoVender(rs.getDouble("p.preco_venda"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                
                item.setProduto(prod);                
                
                lista.add(item);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }

    }
    

}//fim da classe
