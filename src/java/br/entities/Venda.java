/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.entities;

import br.dao.*;
import java.util.List;

/**
 *
 * @author jonatas
 */
public class Venda {
    int iCliente_id;
    int iQtd_unitario;
    int iId;
    String strData_venda;
    double fValor_unitario;
    int iProduto_id;
    List<Produto> produtos;

    public int getProduto_id() {
        return iProduto_id;
    }

    public void setProduto_id(int iProduto_id) {
        this.iProduto_id = iProduto_id;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos.add(produtos);
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }
    
    public int getCliente_id() {
        return iCliente_id;
    }

    public void setCliente_id(int iCliente_id) {
        this.iCliente_id = iCliente_id;
    }

    public int getQtd_unitario() {
        return iQtd_unitario;
    }

    public void setQtd_unitario(int iQtd_unitario) {
        this.iQtd_unitario = iQtd_unitario;
    }

    public String getData_venda() {
        return strData_venda;
    }

    public void setData_venda(String strData_venda) {
        this.strData_venda = strData_venda;
    }

    public double getValor_unitario() {
        return fValor_unitario;
    }

    public void setValor_unitario(double fValor_unitario) {
        this.fValor_unitario = fValor_unitario;
    }

    
    
    
}
