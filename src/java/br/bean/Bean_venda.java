package br.bean;

import br.dao.Cliente_dao;
import br.dao.Produto_dao;
import br.dao.Venda_dao;
import br.entities.Cliente;
import br.entities.Produto;
import br.entities.Venda;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author life
 */
@ManagedBean(name = "bean_venda")
@SessionScoped
public class Bean_venda implements Serializable {

    private Produto_dao produto_dao = new Produto_dao();

    private ArrayList<Produto> produtos;

    private ArrayList<Venda> droppedProdutos;

    private ArrayList<Cliente> cls = new ArrayList<Cliente>();

    private Produto selectedProduto;

    private Venda_dao dao_venda = new Venda_dao();

    private Cliente_dao dao = new Cliente_dao();

    private Venda venda = new Venda();

    @PostConstruct
    public void init() {
        try {
            produtos = produto_dao.Produtos();
            cls = dao.clientes();
        } catch (SQLException ex) {
            Logger.getLogger(Bean_venda.class.getName()).log(Level.SEVERE, null, ex);
        }
        droppedProdutos = new ArrayList<Venda>();
    }

    public String salvarVenda() throws SQLException {
        dao_venda.insertVenda(this.venda, getDroppedProdutos());
        return "lista_fornecedor.xhtml?faces-redirect=true";
    }

    public void onProdutoDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());
        Venda venda = new Venda();
        Date d = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        venda.setData_venda(formatador.format(d));
        venda.setProduto_id(produto.getProduto_id());
        venda.setValor_unitario(produto.getValor());
        droppedProdutos.add(venda);
        produtos.remove(produto);
    }

    public ArrayList<Produto> getProdutos() throws SQLException {
        return produtos = produto_dao.Produtos();
    }

    public ArrayList<Venda> getDroppedProdutos() {
        return droppedProdutos;
    }

    public ArrayList<Cliente> getCls() throws SQLException {
        return cls;
    }

    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void removeProdutoDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());
        droppedProdutos.remove(produto);
    }

    public void setService(Produto_dao produto_dao) {
        this.produto_dao = produto_dao;
    }
}
