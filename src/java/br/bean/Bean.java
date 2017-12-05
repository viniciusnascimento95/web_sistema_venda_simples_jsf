package br.bean;

import br.dao.Cliente_dao;
import br.dao.Conection;
import br.dao.Fornecedor_dao;
import br.dao.Produto_dao;
import br.dao.Venda_dao;
import br.entities.Cliente;
import br.entities.Fornecedor;
import br.entities.Produto;
import br.entities.Venda;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author life
 */
@ManagedBean
@SessionScoped
public class Bean {
    
    ArrayList<Cliente> cls = new ArrayList<Cliente>();
    ArrayList<Produto> prs = new ArrayList<Produto>();
    ArrayList<Fornecedor> frs = new ArrayList<Fornecedor>();
    ArrayList<Venda> vendas = new ArrayList<Venda>();
    Cliente_dao dao = new Cliente_dao();
    Produto_dao dao_produto = new Produto_dao();
    Fornecedor_dao dao_fornecedor = new Fornecedor_dao();
    Venda_dao dao_venda = new Venda_dao();
    Bean_venda bean_venda = new Bean_venda();
    Cliente cl = new Cliente();
    Produto pr = new Produto();
    Fornecedor fr = new Fornecedor();
    Venda venda = new Venda();
    
    public Bean()throws SQLException{
        cls = dao.clientes();
        prs = dao_produto.Produtos();
        frs = dao_fornecedor.fornecedores();
    }
    
    public String salvarCliente()throws SQLException {
        dao.insertCliente(cl);
        return "list.xhtml?faces-redirect=true";
    }
    
    public String salvarProduto() throws SQLException {
        dao_produto.insertProduto(pr);
        return "list_produtos.xhtml?faces-redirect=true";
    }
    
    public String salvarFornecedor() throws SQLException{
        dao_fornecedor.insertFornecedor(fr);
        return "lista_fornecedor.xhtml?faces-redirect=true";
    }
    
    public String salvarVenda() throws SQLException{
        dao_venda.insertVenda(this.venda, bean_venda.getDroppedProdutos());
        return "lista_fornecedor.xhtml?faces-redirect=true";
    }
    
    
    public Cliente getCl(){
        return cl;
    }
    
    public void setCl(Cliente cl){
        this.cl = cl;
    }
    
    public Produto getPr(){
        return pr;
    }
    
    public void setPr(Produto pr){
        this.pr = pr;
    }
    
    public Fornecedor getFr(){
        return fr;
    }
    
    public void setFr(Fornecedor fr){
        this.fr = fr;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    
    // Lista 
    public ArrayList<Cliente> getCls() throws SQLException{
        return cls = dao.clientes();
    }

    public ArrayList<Produto> getProdutos() throws SQLException{
        return prs = dao_produto.Produtos();
    }
    
    public ArrayList<Fornecedor> getFornecedores() throws SQLException{
        return frs = dao_fornecedor.fornecedores();
    }
    
    // Gerar relatorio 
    public void relatorioCliente(String nome){
        try{
            Connection con = Conection.getConnection();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext sContext = (ServletContext) facesContext.getExternalContext().getContext();
            
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            String realPath;
            realPath = sContext.getRealPath("report") + System.getProperty("file.separator");
            String caminhoRelJasper = "/home/life/NetBeansProjects/web_sistema/web/relatorios/"+nome+".jasper";
            System.out.println(caminhoRelJasper);
            Map parameters = new HashMap();
            parameters.put("","");
            byte[] bytes = JasperRunManager.runReportToPdf(caminhoRelJasper, parameters, con);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
            facesContext.responseComplete();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
