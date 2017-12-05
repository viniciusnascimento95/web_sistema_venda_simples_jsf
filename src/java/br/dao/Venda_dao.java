package br.dao;

import br.entities.Cliente;
import br.entities.Detalhe_venda;
import br.entities.Produto;
import br.entities.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author life
 */
public class Venda_dao {

    Connection con = Conection.getConnection();

    public Venda insertVenda(Venda v, ArrayList<Venda> venda) throws SQLException {
        Venda toReturn = null;
        ArrayList<Produto> prod = new ArrayList<Produto>();
        // toReturn = consulta(v.);
        for (Venda vend : venda) {
            String sql = "INSERT INTO public.venda (cliente_id, data_venda, valor_unitario, quantidade_unitaria)"
                    + "VALUES ('" + v.getCliente_id() + "','" + vend.getData_venda()
                    + "','" + vend.getValor_unitario() + "','" + vend.getQtd_unitario() + "');";
            prod = (ArrayList<Produto>) vend.getProdutos();
            PreparedStatement comando = con.prepareStatement(sql);

            try {
                comando.execute();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Sucessful", "Cadastro realizado."));
            } catch (Exception e) {
                e.getMessage();
                System.out.println(e);
            } finally {
                //toReturn = consulta(cl.getEmail());
                comando.close();
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", "Cadastro ja existente."));
        }
        consulta(v.getCliente_id(), venda);
        return toReturn;

    }

    public ArrayList<Detalhe_venda> consulta(int cliente_id, ArrayList<Venda> venda) throws SQLException {
        //Date d = new Date();
        //SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Detalhe_venda> toReturn = new ArrayList<Detalhe_venda>();
        //String sql = "SELECT id FROM vendas.venda v WHERE v.cliente_id = '"+cliente_id+"';"; 

        try {
            for (Venda v : venda) {
                String sql = "SELECT id, sum(quantidade_unitaria) as quantidade_total,(valor_unitario * quantidade_unitaria) as valor_total from public.venda where cliente_id = '" + cliente_id + "' and data_venda = '" + v.getData_venda() + "' group by id, valor_total;";
                PreparedStatement comando = con.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery();
                if (resultado.next()) {
                    Detalhe_venda tmp = new Detalhe_venda();
                    tmp.setiVenda_id(resultado.getInt("id"));
                    tmp.setiProduto_id(v.getProduto_id());
                    tmp.setfValor_total(resultado.getDouble("valor_total"));
                    tmp.setiQtd_total(resultado.getInt("quantidade_total"));
                    toReturn.add(tmp);
                }
            }
            salvaDetalhe_venda(toReturn);
            // con.close();
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e);
        } finally {
            //comando.close();
        }
        return toReturn;
    }

    public void salvaDetalhe_venda(ArrayList<Detalhe_venda> v) throws SQLException {
        for (Detalhe_venda venda : v) {
            String sql = "INSERT INTO public.detalhe_venda (venda_id, produto_id, valor_total, quantidade_total)"
                    + "VALUES ('" + venda.getiVenda_id() + "','" + venda.getiProduto_id()
                    + "','" + venda.getfValor_total() + "','" + venda.getiQtd_total() + "');";
            PreparedStatement comando = con.prepareStatement(sql);
            try {
                // Executando o prepared statement
                comando.execute();
                //comando2.execute();
                // FacesContext mostra uma mensagem na pagina do navegador.
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Sucessful", "Cadastro realizado."));
            } catch (Exception e) {
                e.getMessage();
                System.out.println(e);
            } finally {
                //toReturn = consulta(cl.getEmail());
                comando.close();

            }
        }
        alter();
        //con.close();
    }

    public void alter() throws SQLException {
        System.out.println("entrei no alter");
        ArrayList<Produto> toReturn = new ArrayList<Produto>();
        //Produto toReturn = new Produto();
        String c = "select p.id, p.descricao, (p.quantidade - dv.quantidade_total) as quantidade\n"
                + "from public.detalhe_venda as dv\n"
                + "inner join public.produto p on p.id = dv.produto_id\n"
                + "where p.id = dv.produto_id;";
        PreparedStatement comand = con.prepareStatement(c);
        try {
            ResultSet resultado = comand.executeQuery();
            while (resultado.next()) {
                Produto tmp = new Produto();
                tmp.setProduto_id(resultado.getInt("id"));
                tmp.setQuantidade(resultado.getInt("quantidade"));
                toReturn.add(tmp);
                System.out.println("rodei");
            }
            comand.close();
            for (Produto p : toReturn) {
                String sqls = "UPDATE public.produto SET quantidade ='" + p.getQuantidade() + "'WHERE id = '" + p.getProduto_id() + "';";
                PreparedStatement comando = con.prepareStatement(sqls);
                comando.execute();
                comando.close();
                System.out.println("rodei for");
            }
            // con.close();
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e);
        } finally {
            //comando.close();
            con.close();
        }
    }
}
