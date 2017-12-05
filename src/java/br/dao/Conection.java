package br.dao;
/**
 *
 * @author life
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conection {
    public static Connection getConnection(){
        Connection con  = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/Prova",
             "postgres","12345678");
            System.out.println("Conectado");
        }catch(Exception e){
            System.out.println("Erro de conexão");
            throw new SQLException(e.getMessage());
        }finally{
            if(con != null){
                return con;
            }else{
                return null;
            }
        }
    }
}
