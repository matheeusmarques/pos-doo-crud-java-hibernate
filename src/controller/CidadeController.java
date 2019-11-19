/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import crud.modelo.Cidade;
import crud.modelo.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author aluno
 */
public class CidadeController {

    EntityManager manag;

    public void inserir(Cidade cidade) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.persist(cidade); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void editar(Cidade cidade) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.merge(cidade); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void excluir(Cidade cidade) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin();
            Cidade c = manag.find(Cidade.class, cidade.getId());

            // abre uma transação

            manag.remove(c);
        } catch (Exception e) {
            e.printStackTrace();     //imprime o erro
        } finally {
            manag.getTransaction().commit();
            manag.close(); // fecha a conexao
        }
    }

    public List<Cidade> atualizarListaCidades() {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos  
        Query query = manag.createQuery("SELECT e FROM Cidade e");
        List<Cidade> cidades = query.getResultList();
        return cidades;
    }

    public List<Estado> preencherComboEstados() {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos  
        Query query = manag.createQuery("SELECT e FROM Estado e");
        List<Estado> estados = query.getResultList();
        return estados;
    }

    public Cidade buscar(Cidade cidade) {
        System.out.println("metodo buscar cidade");
        EntityManager manager = fabrica.Fabrica.get().createEntityManager();//
        return manager.find(Cidade.class, cidade.getId());
    }
}
