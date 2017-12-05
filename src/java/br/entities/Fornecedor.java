/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.entities;

import br.dao.*;

/**
 *
 * @author jonatas
 */
public class Fornecedor {
    Integer Iid;
    String strNome;
    String strCnpj;
    String strTelefone;
    String strEndereco;
    String strEmail;

    public Integer getIid() {
        return Iid;
    }

    public void setIid(Integer Iid) {
        this.Iid = Iid;
    }

    public String getStrNome() {
        return strNome;
    }

    public void setStrNome(String strNome) {
        this.strNome = strNome;
    }

    public String getStrCnpj() {
        return strCnpj;
    }

    public void setStrCnpj(String strCnpj) {
        this.strCnpj = strCnpj;
    }

    public String getStrTelefone() {
        return strTelefone;
    }

    public void setStrTelefone(String strTelefone) {
        this.strTelefone = strTelefone;
    }

    public String getStrEndereco() {
        return strEndereco;
    }

    public void setStrEndereco(String strEndereco) {
        this.strEndereco = strEndereco;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }
    
}
