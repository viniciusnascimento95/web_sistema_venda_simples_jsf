package br.dao;

import br.entities.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author life
 */
public class Fornecedor_dao {
     // Conexão com a classe Conection, aonde faz a conexão com o banco de dados
    Connection con = Conection.getConnection();
    
    public Fornecedor insertFornecedor(Fornecedor fr) throws SQLException{
        Fornecedor toReturn = null;
        toReturn = consulta(fr.getStrCnpj());
        if(toReturn != fr){
            String sql = "INSERT INTO public.fornecedor (nome, cnpj, email, telefone, endereco)"+
                    "VALUES ('"+ fr.getStrNome() + "','"+fr.getStrCnpj()+"','"+ fr.getStrEmail() + 
                    "','"+ fr.getStrTelefone()+"','"+fr.getStrEndereco()+"');";
            PreparedStatement comando = con.prepareStatement(sql);
            try{
                comando.execute();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Sucessful", "Cadastro realizado."));
            }catch(Exception e){
                e.getMessage();
                System.out.println(e);
            } finally{
                toReturn = consulta(fr.getStrCnpj());
                comando.close();
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", "Cadastro ja existente."));
        }
        return toReturn;
    }
    
    public Fornecedor consulta(String cnpj) throws SQLException {
        Fornecedor toReturn = new Fornecedor();
        String sql = "SELECT * FROM public.fornecedor a WHERE a.cnpj like '%"+cnpj+"%';";
        PreparedStatement comando = con.prepareStatement(sql);
        try{
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                toReturn.setIid(resultado.getInt("id"));
                toReturn.setStrNome(resultado.getString("nome"));
                toReturn.setStrEmail(resultado.getString("email"));
                toReturn.setStrTelefone(resultado.getString("telefone"));
                toReturn.setStrEndereco(resultado.getString("endereco"));
                toReturn.setStrCnpj(resultado.getString("cnpj"));
            }
        }catch (Exception e){
            e.getMessage();
            System.out.println(e);
        }finally{
            comando.close();
        }
        return toReturn;
    }
    
     public ArrayList<Fornecedor> fornecedores() throws SQLException {
        ArrayList<Fornecedor> toReturn = new ArrayList<Fornecedor>();
        String sql = "SELECT * FROM public.fornecedor;";
        PreparedStatement comando = con.prepareStatement(sql);
        try{
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()){
                Fornecedor tmp = new Fornecedor();
                tmp.setIid(resultado.getInt("id"));
                tmp.setStrNome(resultado.getString("nome"));
                tmp.setStrEmail(resultado.getString("email"));
                tmp.setStrTelefone(resultado.getString("telefone"));
                tmp.setStrEndereco(resultado.getString("endereco"));
                tmp.setStrCnpj(resultado.getString("cnpj"));
                toReturn.add(tmp);
            }
        }catch (Exception e){
            e.getMessage();
            System.out.println(e);
        }finally{
            
        }
        return toReturn;
        
    }
}
