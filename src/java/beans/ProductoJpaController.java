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
import entidades.Areaalmacen;
import entidades.Usuario;
import entidades.Movimientoproducto;
import entidades.Producto;
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
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws RollbackFailureException, Exception {
        if (producto.getMovimientoproductoCollection() == null) {
            producto.setMovimientoproductoCollection(new ArrayList<Movimientoproducto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Areaalmacen idArea = producto.getIdArea();
            if (idArea != null) {
                idArea = em.getReference(idArea.getClass(), idArea.getIdArea());
                producto.setIdArea(idArea);
            }
            Usuario idUsrAlta = producto.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta = em.getReference(idUsrAlta.getClass(), idUsrAlta.getIdUsuario());
                producto.setIdUsrAlta(idUsrAlta);
            }
            Usuario idUsrMod = producto.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod = em.getReference(idUsrMod.getClass(), idUsrMod.getIdUsuario());
                producto.setIdUsrMod(idUsrMod);
            }
            Collection<Movimientoproducto> attachedMovimientoproductoCollection = new ArrayList<Movimientoproducto>();
            for (Movimientoproducto movimientoproductoCollectionMovimientoproductoToAttach : producto.getMovimientoproductoCollection()) {
                movimientoproductoCollectionMovimientoproductoToAttach = em.getReference(movimientoproductoCollectionMovimientoproductoToAttach.getClass(), movimientoproductoCollectionMovimientoproductoToAttach.getIdMovimientoProducto());
                attachedMovimientoproductoCollection.add(movimientoproductoCollectionMovimientoproductoToAttach);
            }
            producto.setMovimientoproductoCollection(attachedMovimientoproductoCollection);
            em.persist(producto);
            if (idArea != null) {
                idArea.getProductoCollection().add(producto);
                idArea = em.merge(idArea);
            }
            if (idUsrAlta != null) {
                idUsrAlta.getProductoCollection().add(producto);
                idUsrAlta = em.merge(idUsrAlta);
            }
            if (idUsrMod != null) {
                idUsrMod.getProductoCollection().add(producto);
                idUsrMod = em.merge(idUsrMod);
            }
            for (Movimientoproducto movimientoproductoCollectionMovimientoproducto : producto.getMovimientoproductoCollection()) {
                Producto oldIdProductoOfMovimientoproductoCollectionMovimientoproducto = movimientoproductoCollectionMovimientoproducto.getIdProducto();
                movimientoproductoCollectionMovimientoproducto.setIdProducto(producto);
                movimientoproductoCollectionMovimientoproducto = em.merge(movimientoproductoCollectionMovimientoproducto);
                if (oldIdProductoOfMovimientoproductoCollectionMovimientoproducto != null) {
                    oldIdProductoOfMovimientoproductoCollectionMovimientoproducto.getMovimientoproductoCollection().remove(movimientoproductoCollectionMovimientoproducto);
                    oldIdProductoOfMovimientoproductoCollectionMovimientoproducto = em.merge(oldIdProductoOfMovimientoproductoCollectionMovimientoproducto);
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

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Areaalmacen idAreaOld = persistentProducto.getIdArea();
            Areaalmacen idAreaNew = producto.getIdArea();
            Usuario idUsrAltaOld = persistentProducto.getIdUsrAlta();
            Usuario idUsrAltaNew = producto.getIdUsrAlta();
            Usuario idUsrModOld = persistentProducto.getIdUsrMod();
            Usuario idUsrModNew = producto.getIdUsrMod();
            Collection<Movimientoproducto> movimientoproductoCollectionOld = persistentProducto.getMovimientoproductoCollection();
            Collection<Movimientoproducto> movimientoproductoCollectionNew = producto.getMovimientoproductoCollection();
            List<String> illegalOrphanMessages = null;
            for (Movimientoproducto movimientoproductoCollectionOldMovimientoproducto : movimientoproductoCollectionOld) {
                if (!movimientoproductoCollectionNew.contains(movimientoproductoCollectionOldMovimientoproducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimientoproducto " + movimientoproductoCollectionOldMovimientoproducto + " since its idProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAreaNew != null) {
                idAreaNew = em.getReference(idAreaNew.getClass(), idAreaNew.getIdArea());
                producto.setIdArea(idAreaNew);
            }
            if (idUsrAltaNew != null) {
                idUsrAltaNew = em.getReference(idUsrAltaNew.getClass(), idUsrAltaNew.getIdUsuario());
                producto.setIdUsrAlta(idUsrAltaNew);
            }
            if (idUsrModNew != null) {
                idUsrModNew = em.getReference(idUsrModNew.getClass(), idUsrModNew.getIdUsuario());
                producto.setIdUsrMod(idUsrModNew);
            }
            Collection<Movimientoproducto> attachedMovimientoproductoCollectionNew = new ArrayList<Movimientoproducto>();
            for (Movimientoproducto movimientoproductoCollectionNewMovimientoproductoToAttach : movimientoproductoCollectionNew) {
                movimientoproductoCollectionNewMovimientoproductoToAttach = em.getReference(movimientoproductoCollectionNewMovimientoproductoToAttach.getClass(), movimientoproductoCollectionNewMovimientoproductoToAttach.getIdMovimientoProducto());
                attachedMovimientoproductoCollectionNew.add(movimientoproductoCollectionNewMovimientoproductoToAttach);
            }
            movimientoproductoCollectionNew = attachedMovimientoproductoCollectionNew;
            producto.setMovimientoproductoCollection(movimientoproductoCollectionNew);
            producto = em.merge(producto);
            if (idAreaOld != null && !idAreaOld.equals(idAreaNew)) {
                idAreaOld.getProductoCollection().remove(producto);
                idAreaOld = em.merge(idAreaOld);
            }
            if (idAreaNew != null && !idAreaNew.equals(idAreaOld)) {
                idAreaNew.getProductoCollection().add(producto);
                idAreaNew = em.merge(idAreaNew);
            }
            if (idUsrAltaOld != null && !idUsrAltaOld.equals(idUsrAltaNew)) {
                idUsrAltaOld.getProductoCollection().remove(producto);
                idUsrAltaOld = em.merge(idUsrAltaOld);
            }
            if (idUsrAltaNew != null && !idUsrAltaNew.equals(idUsrAltaOld)) {
                idUsrAltaNew.getProductoCollection().add(producto);
                idUsrAltaNew = em.merge(idUsrAltaNew);
            }
            if (idUsrModOld != null && !idUsrModOld.equals(idUsrModNew)) {
                idUsrModOld.getProductoCollection().remove(producto);
                idUsrModOld = em.merge(idUsrModOld);
            }
            if (idUsrModNew != null && !idUsrModNew.equals(idUsrModOld)) {
                idUsrModNew.getProductoCollection().add(producto);
                idUsrModNew = em.merge(idUsrModNew);
            }
            for (Movimientoproducto movimientoproductoCollectionNewMovimientoproducto : movimientoproductoCollectionNew) {
                if (!movimientoproductoCollectionOld.contains(movimientoproductoCollectionNewMovimientoproducto)) {
                    Producto oldIdProductoOfMovimientoproductoCollectionNewMovimientoproducto = movimientoproductoCollectionNewMovimientoproducto.getIdProducto();
                    movimientoproductoCollectionNewMovimientoproducto.setIdProducto(producto);
                    movimientoproductoCollectionNewMovimientoproducto = em.merge(movimientoproductoCollectionNewMovimientoproducto);
                    if (oldIdProductoOfMovimientoproductoCollectionNewMovimientoproducto != null && !oldIdProductoOfMovimientoproductoCollectionNewMovimientoproducto.equals(producto)) {
                        oldIdProductoOfMovimientoproductoCollectionNewMovimientoproducto.getMovimientoproductoCollection().remove(movimientoproductoCollectionNewMovimientoproducto);
                        oldIdProductoOfMovimientoproductoCollectionNewMovimientoproducto = em.merge(oldIdProductoOfMovimientoproductoCollectionNewMovimientoproducto);
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
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Movimientoproducto> movimientoproductoCollectionOrphanCheck = producto.getMovimientoproductoCollection();
            for (Movimientoproducto movimientoproductoCollectionOrphanCheckMovimientoproducto : movimientoproductoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Movimientoproducto " + movimientoproductoCollectionOrphanCheckMovimientoproducto + " in its movimientoproductoCollection field has a non-nullable idProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Areaalmacen idArea = producto.getIdArea();
            if (idArea != null) {
                idArea.getProductoCollection().remove(producto);
                idArea = em.merge(idArea);
            }
            Usuario idUsrAlta = producto.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta.getProductoCollection().remove(producto);
                idUsrAlta = em.merge(idUsrAlta);
            }
            Usuario idUsrMod = producto.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod.getProductoCollection().remove(producto);
                idUsrMod = em.merge(idUsrMod);
            }
            em.remove(producto);
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

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
