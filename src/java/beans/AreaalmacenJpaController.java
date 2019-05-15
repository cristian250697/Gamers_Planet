/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.IllegalOrphanException;
import beans.exceptions.NonexistentEntityException;
import beans.exceptions.RollbackFailureException;
import entidades.Areaalmacen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuario;
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
public class AreaalmacenJpaController implements Serializable {

    public AreaalmacenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Areaalmacen areaalmacen) throws RollbackFailureException, Exception {
        if (areaalmacen.getProductoCollection() == null) {
            areaalmacen.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario idUsrAlta = areaalmacen.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta = em.getReference(idUsrAlta.getClass(), idUsrAlta.getIdUsuario());
                areaalmacen.setIdUsrAlta(idUsrAlta);
            }
            Usuario idUsrMod = areaalmacen.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod = em.getReference(idUsrMod.getClass(), idUsrMod.getIdUsuario());
                areaalmacen.setIdUsrMod(idUsrMod);
            }
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : areaalmacen.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getIdProducto());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            areaalmacen.setProductoCollection(attachedProductoCollection);
            em.persist(areaalmacen);
            if (idUsrAlta != null) {
                idUsrAlta.getAreaalmacenCollection().add(areaalmacen);
                idUsrAlta = em.merge(idUsrAlta);
            }
            if (idUsrMod != null) {
                idUsrMod.getAreaalmacenCollection().add(areaalmacen);
                idUsrMod = em.merge(idUsrMod);
            }
            for (Producto productoCollectionProducto : areaalmacen.getProductoCollection()) {
                Areaalmacen oldIdAreaOfProductoCollectionProducto = productoCollectionProducto.getIdArea();
                productoCollectionProducto.setIdArea(areaalmacen);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldIdAreaOfProductoCollectionProducto != null) {
                    oldIdAreaOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldIdAreaOfProductoCollectionProducto = em.merge(oldIdAreaOfProductoCollectionProducto);
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

    public void edit(Areaalmacen areaalmacen) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Areaalmacen persistentAreaalmacen = em.find(Areaalmacen.class, areaalmacen.getIdArea());
            Usuario idUsrAltaOld = persistentAreaalmacen.getIdUsrAlta();
            Usuario idUsrAltaNew = areaalmacen.getIdUsrAlta();
            Usuario idUsrModOld = persistentAreaalmacen.getIdUsrMod();
            Usuario idUsrModNew = areaalmacen.getIdUsrMod();
            Collection<Producto> productoCollectionOld = persistentAreaalmacen.getProductoCollection();
            Collection<Producto> productoCollectionNew = areaalmacen.getProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoCollectionOldProducto + " since its idArea field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsrAltaNew != null) {
                idUsrAltaNew = em.getReference(idUsrAltaNew.getClass(), idUsrAltaNew.getIdUsuario());
                areaalmacen.setIdUsrAlta(idUsrAltaNew);
            }
            if (idUsrModNew != null) {
                idUsrModNew = em.getReference(idUsrModNew.getClass(), idUsrModNew.getIdUsuario());
                areaalmacen.setIdUsrMod(idUsrModNew);
            }
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getIdProducto());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            areaalmacen.setProductoCollection(productoCollectionNew);
            areaalmacen = em.merge(areaalmacen);
            if (idUsrAltaOld != null && !idUsrAltaOld.equals(idUsrAltaNew)) {
                idUsrAltaOld.getAreaalmacenCollection().remove(areaalmacen);
                idUsrAltaOld = em.merge(idUsrAltaOld);
            }
            if (idUsrAltaNew != null && !idUsrAltaNew.equals(idUsrAltaOld)) {
                idUsrAltaNew.getAreaalmacenCollection().add(areaalmacen);
                idUsrAltaNew = em.merge(idUsrAltaNew);
            }
            if (idUsrModOld != null && !idUsrModOld.equals(idUsrModNew)) {
                idUsrModOld.getAreaalmacenCollection().remove(areaalmacen);
                idUsrModOld = em.merge(idUsrModOld);
            }
            if (idUsrModNew != null && !idUsrModNew.equals(idUsrModOld)) {
                idUsrModNew.getAreaalmacenCollection().add(areaalmacen);
                idUsrModNew = em.merge(idUsrModNew);
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    Areaalmacen oldIdAreaOfProductoCollectionNewProducto = productoCollectionNewProducto.getIdArea();
                    productoCollectionNewProducto.setIdArea(areaalmacen);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldIdAreaOfProductoCollectionNewProducto != null && !oldIdAreaOfProductoCollectionNewProducto.equals(areaalmacen)) {
                        oldIdAreaOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldIdAreaOfProductoCollectionNewProducto = em.merge(oldIdAreaOfProductoCollectionNewProducto);
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
                Integer id = areaalmacen.getIdArea();
                if (findAreaalmacen(id) == null) {
                    throw new NonexistentEntityException("The areaalmacen with id " + id + " no longer exists.");
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
            Areaalmacen areaalmacen;
            try {
                areaalmacen = em.getReference(Areaalmacen.class, id);
                areaalmacen.getIdArea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaalmacen with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Producto> productoCollectionOrphanCheck = areaalmacen.getProductoCollection();
            for (Producto productoCollectionOrphanCheckProducto : productoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Areaalmacen (" + areaalmacen + ") cannot be destroyed since the Producto " + productoCollectionOrphanCheckProducto + " in its productoCollection field has a non-nullable idArea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idUsrAlta = areaalmacen.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta.getAreaalmacenCollection().remove(areaalmacen);
                idUsrAlta = em.merge(idUsrAlta);
            }
            Usuario idUsrMod = areaalmacen.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod.getAreaalmacenCollection().remove(areaalmacen);
                idUsrMod = em.merge(idUsrMod);
            }
            em.remove(areaalmacen);
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

    public List<Areaalmacen> findAreaalmacenEntities() {
        return findAreaalmacenEntities(true, -1, -1);
    }

    public List<Areaalmacen> findAreaalmacenEntities(int maxResults, int firstResult) {
        return findAreaalmacenEntities(false, maxResults, firstResult);
    }

    private List<Areaalmacen> findAreaalmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Areaalmacen.class));
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

    public Areaalmacen findAreaalmacen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Areaalmacen.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaalmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Areaalmacen> rt = cq.from(Areaalmacen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
