package br.dao;

/**
 *
 * @author life
 */
import br.entities.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Cliente_dao {

    // Conexão com a classe Conection, aonde faz a conexão com o banco de dados
    Connection con = Conection.getConnection();

    public Cliente insertCliente(Cliente cl) throws SQLException {
        Cliente toReturn = null;
        toReturn = consulta(cl.getEmail());
        if (toReturn != cl) {
            String sql = "INSERT INTO public.cliente (nome, email, telefone)"
                    + "VALUES ('" + cl.getNome() + "','" + cl.getEmail()
                    + "','" + cl.getTelefone() + "');";
            PreparedStatement comando = con.prepareStatement(sql);
            try {
                comando.execute();

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Sucessful", "Cadastro realizado com sucesso."));
            } catch (Exception e) {
                e.getMessage();
                System.out.println(e);
            } finally {
                toReturn = consulta(cl.getEmail());
                comando.close();
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", "Cadastro ja se encontra existente."));
        }
        return toReturn;
    }

    public ArrayList<Cliente> clientes() throws SQLException {
        ArrayList<Cliente> toReturn = new ArrayList<Cliente>();
        String sql = "SELECT * FROM public.cliente;";
        PreparedStatement comando = con.prepareStatement(sql);
        try {
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Cliente tmp = new Cliente();
                tmp.setId_cliente(resultado.getInt("id"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setEmail(resultado.getString("email"));
                tmp.setTelefone(resultado.getString("telefone"));
                toReturn.add(tmp);
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e);
        } finally {

        }
        return toReturn;

    }

    public Cliente consulta(String email) throws SQLException {
        Cliente toReturn = new Cliente();
        String sql = "SELECT * FROM public.cliente a WHERE a.email like '" + email + "';";
        PreparedStatement comando = con.prepareStatement(sql);
        try {
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                toReturn.setId_cliente(resultado.getInt("id"));
                toReturn.setNome(resultado.getString("nome"));
                toReturn.setEmail(resultado.getString("email"));
                toReturn.setTelefone(resultado.getString("telefone"));
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
