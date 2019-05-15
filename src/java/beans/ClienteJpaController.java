/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.IllegalOrphanException;
import beans.exceptions.NonexistentEntityException;
import beans.exceptions.RollbackFailureException;
import entidades.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuario;
import entidades.Movimiento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MartinCoss
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws RollbackFailureException, Exception {
        if (cliente.getMovimientoCollection() == null) {
            cliente.setMovimientoCollection(new ArrayList<Movimiento>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario idUsrAlta = cliente.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta = em.getReference(idUsrAlta.getClass(), idUsrAlta.getIdUsuario());
                cliente.setIdUsrAlta(idUsrAlta);
            }
            Usuario idUsrMod = cliente.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod = em.getReference(idUsrMod.getClass(), idUsrMod.getIdUsuario());
                cliente.setIdUsrMod(idUsrMod);
            }
            Movimiento idMovimiento = cliente.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento = em.getReference(idMovimiento.getClass(), idMovimiento.getIdMovimiento());
                cliente.setIdMovimiento(idMovimiento);
            }
            Collection<Movimiento> attachedMovimientoCollection = new ArrayList<Movimiento>();
            for (Movimiento movimientoCollectionMovimientoToAttach : cliente.getMovimientoCollection()) {
                movimientoCollectionMovimientoToAttach = em.getReference(movimientoCollectionMovimientoToAttach.getClass(), movimientoCollectionMovimientoToAttach.getIdMovimiento());
                attachedMovimientoCollection.add(movimientoCollectionMovimientoToAttach);
            }
            cliente.setMovimientoCollection(attachedMovimientoCollection);
            em.persist(cliente);
            if (idUsrAlta != null) {
                idUsrAlta.getClienteCollection().add(cliente);
                idUsrAlta = em.merge(idUsrAlta);
            }
            if (idUsrMod != null) {
                idUsrMod.getClienteCollection().add(cliente);
                idUsrMod = em.merge(idUsrMod);
            }
            if (idMovimiento != null) {
                idMovimiento.getClienteCollection().add(cliente);
                idMovimiento = em.merge(idMovimiento);
            }
            for (Movimiento movimientoCollectionMovimiento : cliente.getMovimientoCollection()) {
                Cliente oldIdClienteOfMovimientoCollectionMovimiento = movimientoCollectionMovimiento.getIdCliente();
                movimientoCollectionMovimiento.setIdCliente(cliente);
                movimientoCollectionMovimiento = em.merge(movimientoCollectionMovimiento);
                if (oldIdClienteOfMovimientoCollectionMovimiento != null) {
                    oldIdClienteOfMovimientoCollectionMovimiento.getMovimientoCollection().remove(movimientoCollectionMovimiento);
                    oldIdClienteOfMovimientoCollectionMovimiento = em.merge(oldIdClienteOfMovimientoCollectionMovimiento);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Usuario idUsrAltaOld = persistentCliente.getIdUsrAlta();
            Usuario idUsrAltaNew = cliente.getIdUsrAlta();
            Usuario idUsrModOld = persistentCliente.getIdUsrMod();
            Usuario idUsrModNew = cliente.getIdUsrMod();
            Movimiento idMovimientoOld = persistentCliente.getIdMovimiento();
            Movimiento idMovimientoNew = cliente.getIdMovimiento();
            Collection<Movimiento> movimientoCollectionOld = persistentCliente.getMovimientoCollection();
            Collection<Movimiento> movimientoCollectionNew = cliente.getMovimientoCollection();
            List<String> illegalOrphanMessages = null;
            for (Movimiento movimientoCollectionOldMovimiento : movimientoCollectionOld) {
                if (!movimientoCollectionNew.contains(movimientoCollectionOldMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimiento " + movimientoCollectionOldMovimiento + " since its idCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsrAltaNew != null) {
                idUsrAltaNew = em.getReference(idUsrAltaNew.getClass(), idUsrAltaNew.getIdUsuario());
                cliente.setIdUsrAlta(idUsrAltaNew);
            }
            if (idUsrModNew != null) {
                idUsrModNew = em.getReference(idUsrModNew.getClass(), idUsrModNew.getIdUsuario());
                cliente.setIdUsrMod(idUsrModNew);
            }
            if (idMovimientoNew != null) {
                idMovimientoNew = em.getReference(idMovimientoNew.getClass(), idMovimientoNew.getIdMovimiento());
                cliente.setIdMovimiento(idMovimientoNew);
            }
            Collection<Movimiento> attachedMovimientoCollectionNew = new ArrayList<Movimiento>();
            for (Movimiento movimientoCollectionNewMovimientoToAttach : movimientoCollectionNew) {
                movimientoCollectionNewMovimientoToAttach = em.getReference(movimientoCollectionNewMovimientoToAttach.getClass(), movimientoCollectionNewMovimientoToAttach.getIdMovimiento());
                attachedMovimientoCollectionNew.add(movimientoCollectionNewMovimientoToAttach);
            }
            movimientoCollectionNew = attachedMovimientoCollectionNew;
            cliente.setMovimientoCollection(movimientoCollectionNew);
            cliente = em.merge(cliente);
            if (idUsrAltaOld != null && !idUsrAltaOld.equals(idUsrAltaNew)) {
                idUsrAltaOld.getClienteCollection().remove(cliente);
                idUsrAltaOld = em.merge(idUsrAltaOld);
            }
            if (idUsrAltaNew != null && !idUsrAltaNew.equals(idUsrAltaOld)) {
                idUsrAltaNew.getClienteCollection().add(cliente);
                idUsrAltaNew = em.merge(idUsrAltaNew);
            }
            if (idUsrModOld != null && !idUsrModOld.equals(idUsrModNew)) {
                idUsrModOld.getClienteCollection().remove(cliente);
                idUsrModOld = em.merge(idUsrModOld);
            }
            if (idUsrModNew != null && !idUsrModNew.equals(idUsrModOld)) {
                idUsrModNew.getClienteCollection().add(cliente);
                idUsrModNew = em.merge(idUsrModNew);
            }
            if (idMovimientoOld != null && !idMovimientoOld.equals(idMovimientoNew)) {
                idMovimientoOld.getClienteCollection().remove(cliente);
                idMovimientoOld = em.merge(idMovimientoOld);
            }
            if (idMovimientoNew != null && !idMovimientoNew.equals(idMovimientoOld)) {
                idMovimientoNew.getClienteCollection().add(cliente);
                idMovimientoNew = em.merge(idMovimientoNew);
            }
            for (Movimiento movimientoCollectionNewMovimiento : movimientoCollectionNew) {
                if (!movimientoCollectionOld.contains(movimientoCollectionNewMovimiento)) {
                    Cliente oldIdClienteOfMovimientoCollectionNewMovimiento = movimientoCollectionNewMovimiento.getIdCliente();
                    movimientoCollectionNewMovimiento.setIdCliente(cliente);
                    movimientoCollectionNewMovimiento = em.merge(movimientoCollectionNewMovimiento);
                    if (oldIdClienteOfMovimientoCollectionNewMovimiento != null && !oldIdClienteOfMovimientoCollectionNewMovimiento.equals(cliente)) {
                        oldIdClienteOfMovimientoCollectionNewMovimiento.getMovimientoCollection().remove(movimientoCollectionNewMovimiento);
                        oldIdClienteOfMovimientoCollectionNewMovimiento = em.merge(oldIdClienteOfMovimientoCollectionNewMovimiento);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Movimiento> movimientoCollectionOrphanCheck = cliente.getMovimientoCollection();
            for (Movimiento movimientoCollectionOrphanCheckMovimiento : movimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Movimiento " + movimientoCollectionOrphanCheckMovimiento + " in its movimientoCollection field has a non-nullable idCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idUsrAlta = cliente.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta.getClienteCollection().remove(cliente);
                idUsrAlta = em.merge(idUsrAlta);
            }
            Usuario idUsrMod = cliente.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod.getClienteCollection().remove(cliente);
                idUsrMod = em.merge(idUsrMod);
            }
            Movimiento idMovimiento = cliente.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento.getClienteCollection().remove(cliente);
                idMovimiento = em.merge(idMovimiento);
            }
            em.remove(cliente);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
