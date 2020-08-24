package br.com.kslima.loucademia.domain.acesso;

import br.com.kslima.loucademia.domain.aluno.Aluno;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class AcessoRepository {

    @PersistenceContext
    private EntityManager em;

    public Acesso findUltimoAcesso(Aluno aluno) {
        try {
            return em.createQuery("SELECT a FROM Acesso a WHERE a.aluno.matricula = :matricula ORDER BY a.id DESC", Acesso.class)
                    .setParameter("matricula", aluno.getMatricula())
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    public void store (Acesso acesso) {
        em.persist(acesso);
    }
}
