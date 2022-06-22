package br.com.projeto.Resources;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe criada para Conexão com banco de dados
 * @author Adilson Luz
 * @since Classe Criada em 03/07/2021, 18:19:45
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        
        try{            
            return DriverManager.getConnection("jdbc:mysql://localhost/SmallBusinessSales","admin", "123");            
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }//fim do metodo

}//fim da classe
