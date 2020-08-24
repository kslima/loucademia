package br.com.kslima.loucademia.interfaces.acesso.web;

import br.com.kslima.loucademia.application.service.AcessoService;
import br.com.kslima.loucademia.application.util.ValidationException;
import br.com.kslima.loucademia.domain.acesso.TipoAcesso;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

    @EJB
    private AcessoService acessoService;

    @Inject
    private FacesContext facesContext;

    private String matricula;
    private Integer rg;

    public String registrarAcesso(){
        TipoAcesso tipoAcesso;

        try {

            tipoAcesso = acessoService.registrarAcesso(matricula, rg);
            String msg;
            if (tipoAcesso == TipoAcesso.Entrada) {
                msg = "ENTRADA registrada";
            } else if (tipoAcesso == TipoAcesso.Saida) {
                msg = "SAÍDA registrada";
            } else {
                msg = "Dados de registro de acesso inconsistentes";
            }

            facesContext.addMessage(null, new FacesMessage(msg));

        }catch (ValidationException e){
            facesContext.addMessage(null, new FacesMessage(e.getMessage()));
        }
        return null;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
    }
}
