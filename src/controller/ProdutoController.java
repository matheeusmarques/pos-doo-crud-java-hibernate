/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import crud.modelo.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author aluno
 */
public class ProdutoController {
    EntityManager manag;

    public void inserir(Produto produto) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.persist(produto); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void editar(Produto produto) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin(); // abre uma transação
            manag.merge(produto); // salva 
            t.commit(); // confirma a transação 

        } catch (Exception e) {
            t.rollback(); // desfaz tudoo
            e.printStackTrace(); //imprime o erro
        } finally {
            manag.close(); // fecha a conexao
        }
    }

    public void excluir(Produto produto) {
        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos 
        EntityTransaction t = manag.getTransaction();

        try {
            t.begin();
            Produto e = manag.find(Produto.class, produto.getId());

            // abre uma transação

            manag.remove(e);
        } catch (Exception e) {
            e.printStackTrace();     //imprime o erro
        } finally {
            manag.getTransaction().commit();
            manag.close(); // fecha a conexao
        }
    }

    public Produto buscar(Produto produto) {
        EntityManager manager = fabrica.Fabrica.get().createEntityManager();//
        return manager.find(Produto.class, produto.getId());
    }

    public List<Produto> buscarTodos() {

        manag = fabrica.Fabrica.get().createEntityManager(); // create... chama todos os métodos  
        Query query = manag.createQuery("SELECT e FROM Produto e");
        List<Produto> produtos = query.getResultList();
        return produtos;
    }
}
