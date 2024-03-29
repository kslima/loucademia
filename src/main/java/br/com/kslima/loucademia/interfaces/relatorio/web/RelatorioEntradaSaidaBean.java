package br.com.kslima.loucademia.interfaces.relatorio.web;

import br.com.kslima.loucademia.application.service.AlunoService;
import br.com.kslima.loucademia.application.util.StringUtils;
import br.com.kslima.loucademia.application.util.Validation;
import br.com.kslima.loucademia.application.util.ValidationException;
import br.com.kslima.loucademia.domain.acesso.Acesso;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class RelatorioEntradaSaidaBean implements Serializable {

    @EJB
    private AlunoService alunoService;

    @Inject
    private FacesContext facesContext;

    private String matricula;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    private List<Acesso> acessos;

    public void carregarAluno(){
        if (!StringUtils.isEmpty(matricula)) {
            gerarRelatorio();
        }
    }

    public String gerarRelatorio() {
        try {
            acessos = alunoService.listAcessoAlunos(matricula, dataInicial, dataFinal);
        } catch (ValidationException e) {
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

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Acesso> getAcessos() {
        return acessos;
    }

}
