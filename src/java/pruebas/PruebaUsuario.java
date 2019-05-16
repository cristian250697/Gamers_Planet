/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import beans.UsuarioJpaController;
import entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author crist
 */
public class PruebaUsuario {
    public static void main(String[] args) {
        // Factoria de entidades
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Gamers_PlanetPU");
        
        // Gestor de entidades
        EntityManager em = emf.createEntityManager();
        
        // Transacción de entidades, permite hacer transacciónes en las entidades
        EntityTransaction tx = em.getTransaction();
        
        Usuario usuario;
        
        UsuarioJpaController uJC = new UsuarioJpaController(emf);
        usuario = uJC.findUsuario(1);
        
        if (usuario != null){
            System.out.println(usuario.getNombre());
        }
        
    }
    
}
