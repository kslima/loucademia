package br.com.kslima.loucademia.application.service;

import br.com.kslima.loucademia.application.util.StringUtils;
import br.com.kslima.loucademia.application.util.ValidationException;
import br.com.kslima.loucademia.domain.acesso.Acesso;
import br.com.kslima.loucademia.domain.acesso.AcessoRepository;
import br.com.kslima.loucademia.domain.acesso.TipoAcesso;
import br.com.kslima.loucademia.domain.aluno.Aluno;
import br.com.kslima.loucademia.domain.aluno.AlunoRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AcessoService {

    @EJB
    private AcessoRepository acessoRepository;

    @EJB
    AlunoRepository alunoRepository;

    public TipoAcesso registrarAcesso(String matricula, Integer rg){

        if(StringUtils.isEmpty(matricula) && rg == null){
            throw new ValidationException("É preciso fornecer a matrícula ou o RG do aluno");
        }

        Aluno aluno;
        if (StringUtils.isEmpty(matricula)) {
           aluno = alunoRepository.findByRG(rg);
        } else {
            aluno = alunoRepository.findByMatricula(matricula);
        }

        if (aluno == null) {
            throw  new ValidationException("O aluno não foi encontrado");
        }

        Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
        TipoAcesso tipoAcesso;

        if (ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
            ultimoAcesso = new Acesso();
            ultimoAcesso.setAluno(aluno);
            tipoAcesso = ultimoAcesso.registrarAcesso();
            acessoRepository.store(ultimoAcesso);
        } else {
           tipoAcesso = ultimoAcesso.registrarAcesso();
        }

        return tipoAcesso;
    }
}
