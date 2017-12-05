/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.entities;

import br.dao.*;
import java.util.ArrayList;

/**
 *
 * @author jonatas
 */
public class Detalhe_venda {
    int iProduto_id;
    int iVenda_id;
    int iDetalhe_venda_id;
    int iQtd_total;
    double fValor_total;
    ArrayList<Venda> array;

    public ArrayList<Venda> getArray() {
        return array;
    }

    public void setArray(ArrayList<Venda> array) {
        this.array = array;
    }
    
    public double getfValor_total() {
        return fValor_total;
    }

    public void setfValor_total(double fValor_total) {
        this.fValor_total = fValor_total;
    }
    
    public int getiQtd_total() {
        return iQtd_total;
    }

    public void setiQtd_total(int iQtd_total) {
        this.iQtd_total = iQtd_total;
    }

    public int getiProduto_id() {
        return iProduto_id;
    }

    public void setiProduto_id(int iProduto_id) {
        this.iProduto_id = iProduto_id;
    }

    public int getiVenda_id() {
        return iVenda_id;
    }

    public void setiVenda_id(int iVenda_id) {
        this.iVenda_id = iVenda_id;
    }

    public int getiDetalhe_venda_id() {
        return iDetalhe_venda_id;
    }

    public void setiDetalhe_venda_id(int iDetalhe_venda_id) {
        this.iDetalhe_venda_id = iDetalhe_venda_id;
    }
}
