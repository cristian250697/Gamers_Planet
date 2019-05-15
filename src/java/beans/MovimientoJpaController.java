/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.IllegalOrphanException;
import beans.exceptions.NonexistentEntityException;
import beans.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Movimientoproducto;
import entidades.Detallemovimiento;
import entidades.Movimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MartinCoss
 */
public class MovimientoJpaController implements Serializable {

    public MovimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimiento movimiento) throws IllegalOrphanException, RollbackFailureException, Exception {
        if (movimiento.getClienteCollection() == null) {
            movimiento.setClienteCollection(new ArrayList<Cliente>());
        }
        if (movimiento.getMovimientoproductoCollection() == null) {
            movimiento.setMovimientoproductoCollection(new ArrayList<Movimientoproducto>());
        }
        if (movimiento.getDetallemovimientoCollection() == null) {
            movimiento.setDetallemovimientoCollection(new ArrayList<Detallemovimiento>());
        }
        List<String> illegalOrphanMessages = null;
        Cliente idClienteOrphanCheck = movimiento.getIdCliente();
        if (idClienteOrphanCheck != null) {
            Movimiento oldIdMovimientoOfIdCliente = idClienteOrphanCheck.getIdMovimiento();
            if (oldIdMovimientoOfIdCliente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Cliente " + idClienteOrphanCheck + " already has an item of type Movimiento whose idCliente column cannot be null. Please make another selection for the idCliente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente idCliente = movimiento.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                movimiento.setIdCliente(idCliente);
            }
            Collection<Cliente> attachedClienteCollection = new ArrayList<Cliente>();
            for (Cliente clienteCollectionClienteToAttach : movimiento.getClienteCollection()) {
                clienteCollectionClienteToAttach = em.getReference(clienteCollectionClienteToAttach.getClass(), clienteCollectionClienteToAttach.getIdCliente());
                attachedClienteCollection.add(clienteCollectionClienteToAttach);
            }
            movimiento.setClienteCollection(attachedClienteCollection);
            Collection<Movimientoproducto> attachedMovimientoproductoCollection = new ArrayList<Movimientoproducto>();
            for (Movimientoproducto movimientoproductoCollectionMovimientoproductoToAttach : movimiento.getMovimientoproductoCollection()) {
                movimientoproductoCollectionMovimientoproductoToAttach = em.getReference(movimientoproductoCollectionMovimientoproductoToAttach.getClass(), movimientoproductoCollectionMovimientoproductoToAttach.getIdMovimientoProducto());
                attachedMovimientoproductoCollection.add(movimientoproductoCollectionMovimientoproductoToAttach);
            }
            movimiento.setMovimientoproductoCollection(attachedMovimientoproductoCollection);
            Collection<Detallemovimiento> attachedDetallemovimientoCollection = new ArrayList<Detallemovimiento>();
            for (Detallemovimiento detallemovimientoCollectionDetallemovimientoToAttach : movimiento.getDetallemovimientoCollection()) {
                detallemovimientoCollectionDetallemovimientoToAttach = em.getReference(detallemovimientoCollectionDetallemovimientoToAttach.getClass(), detallemovimientoCollectionDetallemovimientoToAttach.getIdDetMov());
                attachedDetallemovimientoCollection.add(detallemovimientoCollectionDetallemovimientoToAttach);
            }
            movimiento.setDetallemovimientoCollection(attachedDetallemovimientoCollection);
            em.persist(movimiento);
            if (idCliente != null) {
                idCliente.setIdMovimiento(movimiento);
                idCliente = em.merge(idCliente);
            }
            for (Cliente clienteCollectionCliente : movimiento.getClienteCollection()) {
                Movimiento oldIdMovimientoOfClienteCollectionCliente = clienteCollectionCliente.getIdMovimiento();
                clienteCollectionCliente.setIdMovimiento(movimiento);
                clienteCollectionCliente = em.merge(clienteCollectionCliente);
                if (oldIdMovimientoOfClienteCollectionCliente != null) {
                    oldIdMovimientoOfClienteCollectionCliente.getClienteCollection().remove(clienteCollectionCliente);
                    oldIdMovimientoOfClienteCollectionCliente = em.merge(oldIdMovimientoOfClienteCollectionCliente);
                }
            }
            for (Movimientoproducto movimientoproductoCollectionMovimientoproducto : movimiento.getMovimientoproductoCollection()) {
                Movimiento oldIdMovimientoOfMovimientoproductoCollectionMovimientoproducto = movimientoproductoCollectionMovimientoproducto.getIdMovimiento();
                movimientoproductoCollectionMovimientoproducto.setIdMovimiento(movimiento);
                movimientoproductoCollectionMovimientoproducto = em.merge(movimientoproductoCollectionMovimientoproducto);
                if (oldIdMovimientoOfMovimientoproductoCollectionMovimientoproducto != null) {
                    oldIdMovimientoOfMovimientoproductoCollectionMovimientoproducto.getMovimientoproductoCollection().remove(movimientoproductoCollectionMovimientoproducto);
                    oldIdMovimientoOfMovimientoproductoCollectionMovimientoproducto = em.merge(oldIdMovimientoOfMovimientoproductoCollectionMovimientoproducto);
                }
            }
            for (Detallemovimiento detallemovimientoCollectionDetallemovimiento : movimiento.getDetallemovimientoCollection()) {
                Movimiento oldIdMovimientoOfDetallemovimientoCollectionDetallemovimiento = detallemovimientoCollectionDetallemovimiento.getIdMovimiento();
                detallemovimientoCollectionDetallemovimiento.setIdMovimiento(movimiento);
                detallemovimientoCollectionDetallemovimiento = em.merge(detallemovimientoCollectionDetallemovimiento);
                if (oldIdMovimientoOfDetallemovimientoCollectionDetallemovimiento != null) {
                    oldIdMovimientoOfDetallemovimientoCollectionDetallemovimiento.getDetallemovimientoCollection().remove(detallemovimientoCollectionDetallemovimiento);
                    oldIdMovimientoOfDetallemovimientoCollectionDetallemovimiento = em.merge(oldIdMovimientoOfDetallemovimientoCollectionDetallemovimiento);
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

    public void edit(Movimiento movimiento) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Movimiento persistentMovimiento = em.find(Movimiento.class, movimiento.getIdMovimiento());
            Cliente idClienteOld = persistentMovimiento.getIdCliente();
            Cliente idClienteNew = movimiento.getIdCliente();
            Collection<Cliente> clienteCollectionOld = persistentMovimiento.getClienteCollection();
            Collection<Cliente> clienteCollectionNew = movimiento.getClienteCollection();
            Collection<Movimientoproducto> movimientoproductoCollectionOld = persistentMovimiento.getMovimientoproductoCollection();
            Collection<Movimientoproducto> movimientoproductoCollectionNew = movimiento.getMovimientoproductoCollection();
            Collection<Detallemovimiento> detallemovimientoCollectionOld = persistentMovimiento.getDetallemovimientoCollection();
            Collection<Detallemovimiento> detallemovimientoCollectionNew = movimiento.getDetallemovimientoCollection();
            List<String> illegalOrphanMessages = null;
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Cliente " + idClienteOld + " since its idMovimiento field is not nullable.");
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                Movimiento oldIdMovimientoOfIdCliente = idClienteNew.getIdMovimiento();
                if (oldIdMovimientoOfIdCliente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Cliente " + idClienteNew + " already has an item of type Movimiento whose idCliente column cannot be null. Please make another selection for the idCliente field.");
                }
            }
            for (Cliente clienteCollectionOldCliente : clienteCollectionOld) {
                if (!clienteCollectionNew.contains(clienteCollectionOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteCollectionOldCliente + " since its idMovimiento field is not nullable.");
                }
            }
            for (Movimientoproducto movimientoproductoCollectionOldMovimientoproducto : movimientoproductoCollectionOld) {
                if (!movimientoproductoCollectionNew.contains(movimientoproductoCollectionOldMovimientoproducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimientoproducto " + movimientoproductoCollectionOldMovimientoproducto + " since its idMovimiento field is not nullable.");
                }
            }
            for (Detallemovimiento detallemovimientoCollectionOldDetallemovimiento : detallemovimientoCollectionOld) {
                if (!detallemovimientoCollectionNew.contains(detallemovimientoCollectionOldDetallemovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallemovimiento " + detallemovimientoCollectionOldDetallemovimiento + " since its idMovimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                movimiento.setIdCliente(idClienteNew);
            }
            Collection<Cliente> attachedClienteCollectionNew = new ArrayList<Cliente>();
            for (Cliente clienteCollectionNewClienteToAttach : clienteCollectionNew) {
                clienteCollectionNewClienteToAttach = em.getReference(clienteCollectionNewClienteToAttach.getClass(), clienteCollectionNewClienteToAttach.getIdCliente());
                attachedClienteCollectionNew.add(clienteCollectionNewClienteToAttach);
            }
            clienteCollectionNew = attachedClienteCollectionNew;
            movimiento.setClienteCollection(clienteCollectionNew);
            Collection<Movimientoproducto> attachedMovimientoproductoCollectionNew = new ArrayList<Movimientoproducto>();
            for (Movimientoproducto movimientoproductoCollectionNewMovimientoproductoToAttach : movimientoproductoCollectionNew) {
                movimientoproductoCollectionNewMovimientoproductoToAttach = em.getReference(movimientoproductoCollectionNewMovimientoproductoToAttach.getClass(), movimientoproductoCollectionNewMovimientoproductoToAttach.getIdMovimientoProducto());
                attachedMovimientoproductoCollectionNew.add(movimientoproductoCollectionNewMovimientoproductoToAttach);
            }
            movimientoproductoCollectionNew = attachedMovimientoproductoCollectionNew;
            movimiento.setMovimientoproductoCollection(movimientoproductoCollectionNew);
            Collection<Detallemovimiento> attachedDetallemovimientoCollectionNew = new ArrayList<Detallemovimiento>();
            for (Detallemovimiento detallemovimientoCollectionNewDetallemovimientoToAttach : detallemovimientoCollectionNew) {
                detallemovimientoCollectionNewDetallemovimientoToAttach = em.getReference(detallemovimientoCollectionNewDetallemovimientoToAttach.getClass(), detallemovimientoCollectionNewDetallemovimientoToAttach.getIdDetMov());
                attachedDetallemovimientoCollectionNew.add(detallemovimientoCollectionNewDetallemovimientoToAttach);
            }
            detallemovimientoCollectionNew = attachedDetallemovimientoCollectionNew;
            movimiento.setDetallemovimientoCollection(detallemovimientoCollectionNew);
            movimiento = em.merge(movimiento);
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.setIdMovimiento(movimiento);
                idClienteNew = em.merge(idClienteNew);
            }
            for (Cliente clienteCollectionNewCliente : clienteCollectionNew) {
                if (!clienteCollectionOld.contains(clienteCollectionNewCliente)) {
                    Movimiento oldIdMovimientoOfClienteCollectionNewCliente = clienteCollectionNewCliente.getIdMovimiento();
                    clienteCollectionNewCliente.setIdMovimiento(movimiento);
                    clienteCollectionNewCliente = em.merge(clienteCollectionNewCliente);
                    if (oldIdMovimientoOfClienteCollectionNewCliente != null && !oldIdMovimientoOfClienteCollectionNewCliente.equals(movimiento)) {
                        oldIdMovimientoOfClienteCollectionNewCliente.getClienteCollection().remove(clienteCollectionNewCliente);
                        oldIdMovimientoOfClienteCollectionNewCliente = em.merge(oldIdMovimientoOfClienteCollectionNewCliente);
                    }
                }
            }
            for (Movimientoproducto movimientoproductoCollectionNewMovimientoproducto : movimientoproductoCollectionNew) {
                if (!movimientoproductoCollectionOld.contains(movimientoproductoCollectionNewMovimientoproducto)) {
                    Movimiento oldIdMovimientoOfMovimientoproductoCollectionNewMovimientoproducto = movimientoproductoCollectionNewMovimientoproducto.getIdMovimiento();
                    movimientoproductoCollectionNewMovimientoproducto.setIdMovimiento(movimiento);
                    movimientoproductoCollectionNewMovimientoproducto = em.merge(movimientoproductoCollectionNewMovimientoproducto);
                    if (oldIdMovimientoOfMovimientoproductoCollectionNewMovimientoproducto != null && !oldIdMovimientoOfMovimientoproductoCollectionNewMovimientoproducto.equals(movimiento)) {
                        oldIdMovimientoOfMovimientoproductoCollectionNewMovimientoproducto.getMovimientoproductoCollection().remove(movimientoproductoCollectionNewMovimientoproducto);
                        oldIdMovimientoOfMovimientoproductoCollectionNewMovimientoproducto = em.merge(oldIdMovimientoOfMovimientoproductoCollectionNewMovimientoproducto);
                    }
                }
            }
            for (Detallemovimiento detallemovimientoCollectionNewDetallemovimiento : detallemovimientoCollectionNew) {
                if (!detallemovimientoCollectionOld.contains(detallemovimientoCollectionNewDetallemovimiento)) {
                    Movimiento oldIdMovimientoOfDetallemovimientoCollectionNewDetallemovimiento = detallemovimientoCollectionNewDetallemovimiento.getIdMovimiento();
                    detallemovimientoCollectionNewDetallemovimiento.setIdMovimiento(movimiento);
                    detallemovimientoCollectionNewDetallemovimiento = em.merge(detallemovimientoCollectionNewDetallemovimiento);
                    if (oldIdMovimientoOfDetallemovimientoCollectionNewDetallemovimiento != null && !oldIdMovimientoOfDetallemovimientoCollectionNewDetallemovimiento.equals(movimiento)) {
                        oldIdMovimientoOfDetallemovimientoCollectionNewDetallemovimiento.getDetallemovimientoCollection().remove(detallemovimientoCollectionNewDetallemovimiento);
                        oldIdMovimientoOfDetallemovimientoCollectionNewDetallemovimiento = em.merge(oldIdMovimientoOfDetallemovimientoCollectionNewDetallemovimiento);
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
                Integer id = movimiento.getIdMovimiento();
                if (findMovimiento(id) == null) {
                    throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.");
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
            Movimiento movimiento;
            try {
                movimiento = em.getReference(Movimiento.class, id);
                movimiento.getIdMovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Cliente idClienteOrphanCheck = movimiento.getIdCliente();
            if (idClienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movimiento (" + movimiento + ") cannot be destroyed since the Cliente " + idClienteOrphanCheck + " in its idCliente field has a non-nullable idMovimiento field.");
            }
            Collection<Cliente> clienteCollectionOrphanCheck = movimiento.getClienteCollection();
            for (Cliente clienteCollectionOrphanCheckCliente : clienteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movimiento (" + movimiento + ") cannot be destroyed since the Cliente " + clienteCollectionOrphanCheckCliente + " in its clienteCollection field has a non-nullable idMovimiento field.");
            }
            Collection<Movimientoproducto> movimientoproductoCollectionOrphanCheck = movimiento.getMovimientoproductoCollection();
            for (Movimientoproducto movimientoproductoCollectionOrphanCheckMovimientoproducto : movimientoproductoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movimiento (" + movimiento + ") cannot be destroyed since the Movimientoproducto " + movimientoproductoCollectionOrphanCheckMovimientoproducto + " in its movimientoproductoCollection field has a non-nullable idMovimiento field.");
            }
            Collection<Detallemovimiento> detallemovimientoCollectionOrphanCheck = movimiento.getDetallemovimientoCollection();
            for (Detallemovimiento detallemovimientoCollectionOrphanCheckDetallemovimiento : detallemovimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movimiento (" + movimiento + ") cannot be destroyed since the Detallemovimiento " + detallemovimientoCollectionOrphanCheckDetallemovimiento + " in its detallemovimientoCollection field has a non-nullable idMovimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(movimiento);
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

    public List<Movimiento> findMovimientoEntities() {
        return findMovimientoEntities(true, -1, -1);
    }

    public List<Movimiento> findMovimientoEntities(int maxResults, int firstResult) {
        return findMovimientoEntities(false, maxResults, firstResult);
    }

    private List<Movimiento> findMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimiento.class));
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

    public Movimiento findMovimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimiento> rt = cq.from(Movimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
