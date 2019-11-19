/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import crud.modelo.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author aluno
 */
public class EstadoController {

    EntityManager manag;

    public void inserir(Estado estado) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.persist(estado); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void editar(Estado estado) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.merge(estado); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void excluir(Estado estado) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin();
            Estado e = manag.find(Estado.class, estado.getId());

            // abre uma transação

            manag.remove(e);
        } catch (Exception e) {
            e.printStackTrace();     //imprime o erro
        } finally {
            manag.getTransaction().commit();
            manag.close(); // fecha a conexao
        }
    }

    public Estado buscar(Estado estado) {
        EntityManager manager = fabrica.Fabrica.get().createEntityManager();//
        return manager.find(Estado.class, estado.getId());
    }

    public List<Estado> buscarTodos() {

        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos  
        Query query = manag.createQuery("SELECT e FROM Estado e");
        List<Estado> estados = query.getResultList();
        return estados;
    }
}
