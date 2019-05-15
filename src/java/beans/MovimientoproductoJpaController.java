/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Movimiento;
import entidades.Movimientoproducto;
import entidades.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MartinCoss
 */
public class MovimientoproductoJpaController implements Serializable {

    public MovimientoproductoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimientoproducto movimientoproducto) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Movimiento idMovimiento = movimientoproducto.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento = em.getReference(idMovimiento.getClass(), idMovimiento.getIdMovimiento());
                movimientoproducto.setIdMovimiento(idMovimiento);
            }
            Producto idProducto = movimientoproducto.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                movimientoproducto.setIdProducto(idProducto);
            }
            em.persist(movimientoproducto);
            if (idMovimiento != null) {
                idMovimiento.getMovimientoproductoCollection().add(movimientoproducto);
                idMovimiento = em.merge(idMovimiento);
            }
            if (idProducto != null) {
                idProducto.getMovimientoproductoCollection().add(movimientoproducto);
                idProducto = em.merge(idProducto);
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

    public void edit(Movimientoproducto movimientoproducto) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Movimientoproducto persistentMovimientoproducto = em.find(Movimientoproducto.class, movimientoproducto.getIdMovimientoProducto());
            Movimiento idMovimientoOld = persistentMovimientoproducto.getIdMovimiento();
            Movimiento idMovimientoNew = movimientoproducto.getIdMovimiento();
            Producto idProductoOld = persistentMovimientoproducto.getIdProducto();
            Producto idProductoNew = movimientoproducto.getIdProducto();
            if (idMovimientoNew != null) {
                idMovimientoNew = em.getReference(idMovimientoNew.getClass(), idMovimientoNew.getIdMovimiento());
                movimientoproducto.setIdMovimiento(idMovimientoNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                movimientoproducto.setIdProducto(idProductoNew);
            }
            movimientoproducto = em.merge(movimientoproducto);
            if (idMovimientoOld != null && !idMovimientoOld.equals(idMovimientoNew)) {
                idMovimientoOld.getMovimientoproductoCollection().remove(movimientoproducto);
                idMovimientoOld = em.merge(idMovimientoOld);
            }
            if (idMovimientoNew != null && !idMovimientoNew.equals(idMovimientoOld)) {
                idMovimientoNew.getMovimientoproductoCollection().add(movimientoproducto);
                idMovimientoNew = em.merge(idMovimientoNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getMovimientoproductoCollection().remove(movimientoproducto);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getMovimientoproductoCollection().add(movimientoproducto);
                idProductoNew = em.merge(idProductoNew);
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
                Integer id = movimientoproducto.getIdMovimientoProducto();
                if (findMovimientoproducto(id) == null) {
                    throw new NonexistentEntityException("The movimientoproducto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Movimientoproducto movimientoproducto;
            try {
                movimientoproducto = em.getReference(Movimientoproducto.class, id);
                movimientoproducto.getIdMovimientoProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientoproducto with id " + id + " no longer exists.", enfe);
            }
            Movimiento idMovimiento = movimientoproducto.getIdMovimiento();
            if (idMovimiento != null) {
                idMovimiento.getMovimientoproductoCollection().remove(movimientoproducto);
                idMovimiento = em.merge(idMovimiento);
            }
            Producto idProducto = movimientoproducto.getIdProducto();
            if (idProducto != null) {
                idProducto.getMovimientoproductoCollection().remove(movimientoproducto);
                idProducto = em.merge(idProducto);
            }
            em.remove(movimientoproducto);
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

    public List<Movimientoproducto> findMovimientoproductoEntities() {
        return findMovimientoproductoEntities(true, -1, -1);
    }

    public List<Movimientoproducto> findMovimientoproductoEntities(int maxResults, int firstResult) {
        return findMovimientoproductoEntities(false, maxResults, firstResult);
    }

    private List<Movimientoproducto> findMovimientoproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimientoproducto.class));
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

    public Movimientoproducto findMovimientoproducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimientoproducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimientoproducto> rt = cq.from(Movimientoproducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
