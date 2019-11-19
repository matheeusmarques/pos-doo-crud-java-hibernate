/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import crud.modelo.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author aluno
 */
public class PessoaController {
    EntityManager manag;

    public void inserir(Pessoa pessoa) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.persist(pessoa); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void editar(Pessoa pessoa) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.merge(pessoa); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void excluir(Pessoa pessoa) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin();
            Pessoa p = manag.find(Pessoa.class, pessoa.getId());

            // abre uma transação

            manag.remove(p);
        } catch (Exception e) {
            e.printStackTrace();     //imprime o erro
        } finally {
            manag.getTransaction().commit();
            manag.close(); // fecha a conexao
        }
    }

    public List<Pessoa> buscarTodos() {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos  
        Query query = manag.createQuery("SELECT e FROM Pessoa e");
        List<Pessoa> pessoas = query.getResultList();
        return pessoas;
    }

}
