package br.dao;

import br.entities.Produto;
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
public class Produto_dao {

    Connection con = Conection.getConnection();

    public Produto insertProduto(Produto pr) throws SQLException {
        Produto toReturn = null;
        toReturn = consulta(pr.getNome());
        if (toReturn != pr) {
            String sql = "INSERT INTO public.produto (nome, descricao, valor, modelo, quantidade, fornecedor_id)"
                    + "VALUES ('" + pr.getNome() + "','" + pr.getDescricao()
                    + "','" + pr.getValor() + "','" + pr.getModelo() + "','" + pr.getQuantidade() + "','" + pr.getFornecedor_id() + "');";
            PreparedStatement comando = con.prepareStatement(sql);
            try {
                comando.execute();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Sucessful", "Cadastro realizado."));
            } catch (Exception e) {
                e.getMessage();
                System.out.println(e);
            } finally {
                toReturn = consulta(pr.getNome());
                comando.close();
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", "Cadastro ja existente."));
        }
        return toReturn;
    }

    public ArrayList<Produto> Produtos() throws SQLException {
        ArrayList<Produto> toReturn = new ArrayList<Produto>();
        String sql = "SELECT * FROM public.produto;";
        PreparedStatement comando = con.prepareStatement(sql);
        try {
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Produto tmp = new Produto();
                tmp.setProduto_id(resultado.getInt("id"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setDescricao(resultado.getString("descricao"));
                tmp.setValor(resultado.getDouble("valor"));
                tmp.setModelo(resultado.getString("modelo"));
                tmp.setQuantidade(resultado.getInt("quantidade"));
                tmp.setFornecedor_id(resultado.getInt("fornecedor_id"));
                toReturn.add(tmp);
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e);
        } finally {

        }
        return toReturn;

    }

    public Produto consulta(String nome) throws SQLException {
        Produto toReturn = new Produto();
        String sql = "SELECT * FROM public.produto a WHERE a.nome like '%" + nome + "%';";
        PreparedStatement comando = con.prepareStatement(sql);
        try {
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                toReturn.setProduto_id(resultado.getInt("id"));
                toReturn.setNome(resultado.getString("nome"));
                toReturn.setDescricao(resultado.getString("descricao"));
                toReturn.setValor(resultado.getDouble("valor"));
                toReturn.setModelo(resultado.getString("modelo"));
                toReturn.setQuantidade(resultado.getInt("quantidade"));
                toReturn.setFornecedor_id(resultado.getInt("fornecedor_id"));
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e);
        } finally {
            comando.close();
        }
        return toReturn;
    }

}
