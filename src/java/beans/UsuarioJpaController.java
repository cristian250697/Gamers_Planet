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
import entidades.Usuario;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Areaalmacen;
import entidades.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MartinCoss
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws RollbackFailureException, Exception {
        if (usuario.getClienteCollection() == null) {
            usuario.setClienteCollection(new ArrayList<Cliente>());
        }
        if (usuario.getClienteCollection1() == null) {
            usuario.setClienteCollection1(new ArrayList<Cliente>());
        }
        if (usuario.getAreaalmacenCollection() == null) {
            usuario.setAreaalmacenCollection(new ArrayList<Areaalmacen>());
        }
        if (usuario.getAreaalmacenCollection1() == null) {
            usuario.setAreaalmacenCollection1(new ArrayList<Areaalmacen>());
        }
        if (usuario.getUsuarioCollection() == null) {
            usuario.setUsuarioCollection(new ArrayList<Usuario>());
        }
        if (usuario.getUsuarioCollection1() == null) {
            usuario.setUsuarioCollection1(new ArrayList<Usuario>());
        }
        if (usuario.getProductoCollection() == null) {
            usuario.setProductoCollection(new ArrayList<Producto>());
        }
        if (usuario.getProductoCollection1() == null) {
            usuario.setProductoCollection1(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario idUsrAlta = usuario.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta = em.getReference(idUsrAlta.getClass(), idUsrAlta.getIdUsuario());
                usuario.setIdUsrAlta(idUsrAlta);
            }
            Usuario idUsrMod = usuario.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod = em.getReference(idUsrMod.getClass(), idUsrMod.getIdUsuario());
                usuario.setIdUsrMod(idUsrMod);
            }
            Collection<Cliente> attachedClienteCollection = new ArrayList<Cliente>();
            for (Cliente clienteCollectionClienteToAttach : usuario.getClienteCollection()) {
                clienteCollectionClienteToAttach = em.getReference(clienteCollectionClienteToAttach.getClass(), clienteCollectionClienteToAttach.getIdCliente());
                attachedClienteCollection.add(clienteCollectionClienteToAttach);
            }
            usuario.setClienteCollection(attachedClienteCollection);
            Collection<Cliente> attachedClienteCollection1 = new ArrayList<Cliente>();
            for (Cliente clienteCollection1ClienteToAttach : usuario.getClienteCollection1()) {
                clienteCollection1ClienteToAttach = em.getReference(clienteCollection1ClienteToAttach.getClass(), clienteCollection1ClienteToAttach.getIdCliente());
                attachedClienteCollection1.add(clienteCollection1ClienteToAttach);
            }
            usuario.setClienteCollection1(attachedClienteCollection1);
            Collection<Areaalmacen> attachedAreaalmacenCollection = new ArrayList<Areaalmacen>();
            for (Areaalmacen areaalmacenCollectionAreaalmacenToAttach : usuario.getAreaalmacenCollection()) {
                areaalmacenCollectionAreaalmacenToAttach = em.getReference(areaalmacenCollectionAreaalmacenToAttach.getClass(), areaalmacenCollectionAreaalmacenToAttach.getIdArea());
                attachedAreaalmacenCollection.add(areaalmacenCollectionAreaalmacenToAttach);
            }
            usuario.setAreaalmacenCollection(attachedAreaalmacenCollection);
            Collection<Areaalmacen> attachedAreaalmacenCollection1 = new ArrayList<Areaalmacen>();
            for (Areaalmacen areaalmacenCollection1AreaalmacenToAttach : usuario.getAreaalmacenCollection1()) {
                areaalmacenCollection1AreaalmacenToAttach = em.getReference(areaalmacenCollection1AreaalmacenToAttach.getClass(), areaalmacenCollection1AreaalmacenToAttach.getIdArea());
                attachedAreaalmacenCollection1.add(areaalmacenCollection1AreaalmacenToAttach);
            }
            usuario.setAreaalmacenCollection1(attachedAreaalmacenCollection1);
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : usuario.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getIdUsuario());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            usuario.setUsuarioCollection(attachedUsuarioCollection);
            Collection<Usuario> attachedUsuarioCollection1 = new ArrayList<Usuario>();
            for (Usuario usuarioCollection1UsuarioToAttach : usuario.getUsuarioCollection1()) {
                usuarioCollection1UsuarioToAttach = em.getReference(usuarioCollection1UsuarioToAttach.getClass(), usuarioCollection1UsuarioToAttach.getIdUsuario());
                attachedUsuarioCollection1.add(usuarioCollection1UsuarioToAttach);
            }
            usuario.setUsuarioCollection1(attachedUsuarioCollection1);
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : usuario.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getIdProducto());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            usuario.setProductoCollection(attachedProductoCollection);
            Collection<Producto> attachedProductoCollection1 = new ArrayList<Producto>();
            for (Producto productoCollection1ProductoToAttach : usuario.getProductoCollection1()) {
                productoCollection1ProductoToAttach = em.getReference(productoCollection1ProductoToAttach.getClass(), productoCollection1ProductoToAttach.getIdProducto());
                attachedProductoCollection1.add(productoCollection1ProductoToAttach);
            }
            usuario.setProductoCollection1(attachedProductoCollection1);
            em.persist(usuario);
            if (idUsrAlta != null) {
                idUsrAlta.getUsuarioCollection().add(usuario);
                idUsrAlta = em.merge(idUsrAlta);
            }
            if (idUsrMod != null) {
                idUsrMod.getUsuarioCollection().add(usuario);
                idUsrMod = em.merge(idUsrMod);
            }
            for (Cliente clienteCollectionCliente : usuario.getClienteCollection()) {
                Usuario oldIdUsrAltaOfClienteCollectionCliente = clienteCollectionCliente.getIdUsrAlta();
                clienteCollectionCliente.setIdUsrAlta(usuario);
                clienteCollectionCliente = em.merge(clienteCollectionCliente);
                if (oldIdUsrAltaOfClienteCollectionCliente != null) {
                    oldIdUsrAltaOfClienteCollectionCliente.getClienteCollection().remove(clienteCollectionCliente);
                    oldIdUsrAltaOfClienteCollectionCliente = em.merge(oldIdUsrAltaOfClienteCollectionCliente);
                }
            }
            for (Cliente clienteCollection1Cliente : usuario.getClienteCollection1()) {
                Usuario oldIdUsrModOfClienteCollection1Cliente = clienteCollection1Cliente.getIdUsrMod();
                clienteCollection1Cliente.setIdUsrMod(usuario);
                clienteCollection1Cliente = em.merge(clienteCollection1Cliente);
                if (oldIdUsrModOfClienteCollection1Cliente != null) {
                    oldIdUsrModOfClienteCollection1Cliente.getClienteCollection1().remove(clienteCollection1Cliente);
                    oldIdUsrModOfClienteCollection1Cliente = em.merge(oldIdUsrModOfClienteCollection1Cliente);
                }
            }
            for (Areaalmacen areaalmacenCollectionAreaalmacen : usuario.getAreaalmacenCollection()) {
                Usuario oldIdUsrAltaOfAreaalmacenCollectionAreaalmacen = areaalmacenCollectionAreaalmacen.getIdUsrAlta();
                areaalmacenCollectionAreaalmacen.setIdUsrAlta(usuario);
                areaalmacenCollectionAreaalmacen = em.merge(areaalmacenCollectionAreaalmacen);
                if (oldIdUsrAltaOfAreaalmacenCollectionAreaalmacen != null) {
                    oldIdUsrAltaOfAreaalmacenCollectionAreaalmacen.getAreaalmacenCollection().remove(areaalmacenCollectionAreaalmacen);
                    oldIdUsrAltaOfAreaalmacenCollectionAreaalmacen = em.merge(oldIdUsrAltaOfAreaalmacenCollectionAreaalmacen);
                }
            }
            for (Areaalmacen areaalmacenCollection1Areaalmacen : usuario.getAreaalmacenCollection1()) {
                Usuario oldIdUsrModOfAreaalmacenCollection1Areaalmacen = areaalmacenCollection1Areaalmacen.getIdUsrMod();
                areaalmacenCollection1Areaalmacen.setIdUsrMod(usuario);
                areaalmacenCollection1Areaalmacen = em.merge(areaalmacenCollection1Areaalmacen);
                if (oldIdUsrModOfAreaalmacenCollection1Areaalmacen != null) {
                    oldIdUsrModOfAreaalmacenCollection1Areaalmacen.getAreaalmacenCollection1().remove(areaalmacenCollection1Areaalmacen);
                    oldIdUsrModOfAreaalmacenCollection1Areaalmacen = em.merge(oldIdUsrModOfAreaalmacenCollection1Areaalmacen);
                }
            }
            for (Usuario usuarioCollectionUsuario : usuario.getUsuarioCollection()) {
                Usuario oldIdUsrAltaOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getIdUsrAlta();
                usuarioCollectionUsuario.setIdUsrAlta(usuario);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldIdUsrAltaOfUsuarioCollectionUsuario != null) {
                    oldIdUsrAltaOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldIdUsrAltaOfUsuarioCollectionUsuario = em.merge(oldIdUsrAltaOfUsuarioCollectionUsuario);
                }
            }
            for (Usuario usuarioCollection1Usuario : usuario.getUsuarioCollection1()) {
                Usuario oldIdUsrModOfUsuarioCollection1Usuario = usuarioCollection1Usuario.getIdUsrMod();
                usuarioCollection1Usuario.setIdUsrMod(usuario);
                usuarioCollection1Usuario = em.merge(usuarioCollection1Usuario);
                if (oldIdUsrModOfUsuarioCollection1Usuario != null) {
                    oldIdUsrModOfUsuarioCollection1Usuario.getUsuarioCollection1().remove(usuarioCollection1Usuario);
                    oldIdUsrModOfUsuarioCollection1Usuario = em.merge(oldIdUsrModOfUsuarioCollection1Usuario);
                }
            }
            for (Producto productoCollectionProducto : usuario.getProductoCollection()) {
                Usuario oldIdUsrAltaOfProductoCollectionProducto = productoCollectionProducto.getIdUsrAlta();
                productoCollectionProducto.setIdUsrAlta(usuario);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldIdUsrAltaOfProductoCollectionProducto != null) {
                    oldIdUsrAltaOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldIdUsrAltaOfProductoCollectionProducto = em.merge(oldIdUsrAltaOfProductoCollectionProducto);
                }
            }
            for (Producto productoCollection1Producto : usuario.getProductoCollection1()) {
                Usuario oldIdUsrModOfProductoCollection1Producto = productoCollection1Producto.getIdUsrMod();
                productoCollection1Producto.setIdUsrMod(usuario);
                productoCollection1Producto = em.merge(productoCollection1Producto);
                if (oldIdUsrModOfProductoCollection1Producto != null) {
                    oldIdUsrModOfProductoCollection1Producto.getProductoCollection1().remove(productoCollection1Producto);
                    oldIdUsrModOfProductoCollection1Producto = em.merge(oldIdUsrModOfProductoCollection1Producto);
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

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Usuario idUsrAltaOld = persistentUsuario.getIdUsrAlta();
            Usuario idUsrAltaNew = usuario.getIdUsrAlta();
            Usuario idUsrModOld = persistentUsuario.getIdUsrMod();
            Usuario idUsrModNew = usuario.getIdUsrMod();
            Collection<Cliente> clienteCollectionOld = persistentUsuario.getClienteCollection();
            Collection<Cliente> clienteCollectionNew = usuario.getClienteCollection();
            Collection<Cliente> clienteCollection1Old = persistentUsuario.getClienteCollection1();
            Collection<Cliente> clienteCollection1New = usuario.getClienteCollection1();
            Collection<Areaalmacen> areaalmacenCollectionOld = persistentUsuario.getAreaalmacenCollection();
            Collection<Areaalmacen> areaalmacenCollectionNew = usuario.getAreaalmacenCollection();
            Collection<Areaalmacen> areaalmacenCollection1Old = persistentUsuario.getAreaalmacenCollection1();
            Collection<Areaalmacen> areaalmacenCollection1New = usuario.getAreaalmacenCollection1();
            Collection<Usuario> usuarioCollectionOld = persistentUsuario.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = usuario.getUsuarioCollection();
            Collection<Usuario> usuarioCollection1Old = persistentUsuario.getUsuarioCollection1();
            Collection<Usuario> usuarioCollection1New = usuario.getUsuarioCollection1();
            Collection<Producto> productoCollectionOld = persistentUsuario.getProductoCollection();
            Collection<Producto> productoCollectionNew = usuario.getProductoCollection();
            Collection<Producto> productoCollection1Old = persistentUsuario.getProductoCollection1();
            Collection<Producto> productoCollection1New = usuario.getProductoCollection1();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteCollectionOldCliente : clienteCollectionOld) {
                if (!clienteCollectionNew.contains(clienteCollectionOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteCollectionOldCliente + " since its idUsrAlta field is not nullable.");
                }
            }
            for (Cliente clienteCollection1OldCliente : clienteCollection1Old) {
                if (!clienteCollection1New.contains(clienteCollection1OldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteCollection1OldCliente + " since its idUsrMod field is not nullable.");
                }
            }
            for (Areaalmacen areaalmacenCollectionOldAreaalmacen : areaalmacenCollectionOld) {
                if (!areaalmacenCollectionNew.contains(areaalmacenCollectionOldAreaalmacen)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Areaalmacen " + areaalmacenCollectionOldAreaalmacen + " since its idUsrAlta field is not nullable.");
                }
            }
            for (Areaalmacen areaalmacenCollection1OldAreaalmacen : areaalmacenCollection1Old) {
                if (!areaalmacenCollection1New.contains(areaalmacenCollection1OldAreaalmacen)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Areaalmacen " + areaalmacenCollection1OldAreaalmacen + " since its idUsrMod field is not nullable.");
                }
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioCollectionOldUsuario + " since its idUsrAlta field is not nullable.");
                }
            }
            for (Usuario usuarioCollection1OldUsuario : usuarioCollection1Old) {
                if (!usuarioCollection1New.contains(usuarioCollection1OldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioCollection1OldUsuario + " since its idUsrMod field is not nullable.");
                }
            }
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoCollectionOldProducto + " since its idUsrAlta field is not nullable.");
                }
            }
            for (Producto productoCollection1OldProducto : productoCollection1Old) {
                if (!productoCollection1New.contains(productoCollection1OldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoCollection1OldProducto + " since its idUsrMod field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsrAltaNew != null) {
                idUsrAltaNew = em.getReference(idUsrAltaNew.getClass(), idUsrAltaNew.getIdUsuario());
                usuario.setIdUsrAlta(idUsrAltaNew);
            }
            if (idUsrModNew != null) {
                idUsrModNew = em.getReference(idUsrModNew.getClass(), idUsrModNew.getIdUsuario());
                usuario.setIdUsrMod(idUsrModNew);
            }
            Collection<Cliente> attachedClienteCollectionNew = new ArrayList<Cliente>();
            for (Cliente clienteCollectionNewClienteToAttach : clienteCollectionNew) {
                clienteCollectionNewClienteToAttach = em.getReference(clienteCollectionNewClienteToAttach.getClass(), clienteCollectionNewClienteToAttach.getIdCliente());
                attachedClienteCollectionNew.add(clienteCollectionNewClienteToAttach);
            }
            clienteCollectionNew = attachedClienteCollectionNew;
            usuario.setClienteCollection(clienteCollectionNew);
            Collection<Cliente> attachedClienteCollection1New = new ArrayList<Cliente>();
            for (Cliente clienteCollection1NewClienteToAttach : clienteCollection1New) {
                clienteCollection1NewClienteToAttach = em.getReference(clienteCollection1NewClienteToAttach.getClass(), clienteCollection1NewClienteToAttach.getIdCliente());
                attachedClienteCollection1New.add(clienteCollection1NewClienteToAttach);
            }
            clienteCollection1New = attachedClienteCollection1New;
            usuario.setClienteCollection1(clienteCollection1New);
            Collection<Areaalmacen> attachedAreaalmacenCollectionNew = new ArrayList<Areaalmacen>();
            for (Areaalmacen areaalmacenCollectionNewAreaalmacenToAttach : areaalmacenCollectionNew) {
                areaalmacenCollectionNewAreaalmacenToAttach = em.getReference(areaalmacenCollectionNewAreaalmacenToAttach.getClass(), areaalmacenCollectionNewAreaalmacenToAttach.getIdArea());
                attachedAreaalmacenCollectionNew.add(areaalmacenCollectionNewAreaalmacenToAttach);
            }
            areaalmacenCollectionNew = attachedAreaalmacenCollectionNew;
            usuario.setAreaalmacenCollection(areaalmacenCollectionNew);
            Collection<Areaalmacen> attachedAreaalmacenCollection1New = new ArrayList<Areaalmacen>();
            for (Areaalmacen areaalmacenCollection1NewAreaalmacenToAttach : areaalmacenCollection1New) {
                areaalmacenCollection1NewAreaalmacenToAttach = em.getReference(areaalmacenCollection1NewAreaalmacenToAttach.getClass(), areaalmacenCollection1NewAreaalmacenToAttach.getIdArea());
                attachedAreaalmacenCollection1New.add(areaalmacenCollection1NewAreaalmacenToAttach);
            }
            areaalmacenCollection1New = attachedAreaalmacenCollection1New;
            usuario.setAreaalmacenCollection1(areaalmacenCollection1New);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            usuario.setUsuarioCollection(usuarioCollectionNew);
            Collection<Usuario> attachedUsuarioCollection1New = new ArrayList<Usuario>();
            for (Usuario usuarioCollection1NewUsuarioToAttach : usuarioCollection1New) {
                usuarioCollection1NewUsuarioToAttach = em.getReference(usuarioCollection1NewUsuarioToAttach.getClass(), usuarioCollection1NewUsuarioToAttach.getIdUsuario());
                attachedUsuarioCollection1New.add(usuarioCollection1NewUsuarioToAttach);
            }
            usuarioCollection1New = attachedUsuarioCollection1New;
            usuario.setUsuarioCollection1(usuarioCollection1New);
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getIdProducto());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            usuario.setProductoCollection(productoCollectionNew);
            Collection<Producto> attachedProductoCollection1New = new ArrayList<Producto>();
            for (Producto productoCollection1NewProductoToAttach : productoCollection1New) {
                productoCollection1NewProductoToAttach = em.getReference(productoCollection1NewProductoToAttach.getClass(), productoCollection1NewProductoToAttach.getIdProducto());
                attachedProductoCollection1New.add(productoCollection1NewProductoToAttach);
            }
            productoCollection1New = attachedProductoCollection1New;
            usuario.setProductoCollection1(productoCollection1New);
            usuario = em.merge(usuario);
            if (idUsrAltaOld != null && !idUsrAltaOld.equals(idUsrAltaNew)) {
                idUsrAltaOld.getUsuarioCollection().remove(usuario);
                idUsrAltaOld = em.merge(idUsrAltaOld);
            }
            if (idUsrAltaNew != null && !idUsrAltaNew.equals(idUsrAltaOld)) {
                idUsrAltaNew.getUsuarioCollection().add(usuario);
                idUsrAltaNew = em.merge(idUsrAltaNew);
            }
            if (idUsrModOld != null && !idUsrModOld.equals(idUsrModNew)) {
                idUsrModOld.getUsuarioCollection().remove(usuario);
                idUsrModOld = em.merge(idUsrModOld);
            }
            if (idUsrModNew != null && !idUsrModNew.equals(idUsrModOld)) {
                idUsrModNew.getUsuarioCollection().add(usuario);
                idUsrModNew = em.merge(idUsrModNew);
            }
            for (Cliente clienteCollectionNewCliente : clienteCollectionNew) {
                if (!clienteCollectionOld.contains(clienteCollectionNewCliente)) {
                    Usuario oldIdUsrAltaOfClienteCollectionNewCliente = clienteCollectionNewCliente.getIdUsrAlta();
                    clienteCollectionNewCliente.setIdUsrAlta(usuario);
                    clienteCollectionNewCliente = em.merge(clienteCollectionNewCliente);
                    if (oldIdUsrAltaOfClienteCollectionNewCliente != null && !oldIdUsrAltaOfClienteCollectionNewCliente.equals(usuario)) {
                        oldIdUsrAltaOfClienteCollectionNewCliente.getClienteCollection().remove(clienteCollectionNewCliente);
                        oldIdUsrAltaOfClienteCollectionNewCliente = em.merge(oldIdUsrAltaOfClienteCollectionNewCliente);
                    }
                }
            }
            for (Cliente clienteCollection1NewCliente : clienteCollection1New) {
                if (!clienteCollection1Old.contains(clienteCollection1NewCliente)) {
                    Usuario oldIdUsrModOfClienteCollection1NewCliente = clienteCollection1NewCliente.getIdUsrMod();
                    clienteCollection1NewCliente.setIdUsrMod(usuario);
                    clienteCollection1NewCliente = em.merge(clienteCollection1NewCliente);
                    if (oldIdUsrModOfClienteCollection1NewCliente != null && !oldIdUsrModOfClienteCollection1NewCliente.equals(usuario)) {
                        oldIdUsrModOfClienteCollection1NewCliente.getClienteCollection1().remove(clienteCollection1NewCliente);
                        oldIdUsrModOfClienteCollection1NewCliente = em.merge(oldIdUsrModOfClienteCollection1NewCliente);
                    }
                }
            }
            for (Areaalmacen areaalmacenCollectionNewAreaalmacen : areaalmacenCollectionNew) {
                if (!areaalmacenCollectionOld.contains(areaalmacenCollectionNewAreaalmacen)) {
                    Usuario oldIdUsrAltaOfAreaalmacenCollectionNewAreaalmacen = areaalmacenCollectionNewAreaalmacen.getIdUsrAlta();
                    areaalmacenCollectionNewAreaalmacen.setIdUsrAlta(usuario);
                    areaalmacenCollectionNewAreaalmacen = em.merge(areaalmacenCollectionNewAreaalmacen);
                    if (oldIdUsrAltaOfAreaalmacenCollectionNewAreaalmacen != null && !oldIdUsrAltaOfAreaalmacenCollectionNewAreaalmacen.equals(usuario)) {
                        oldIdUsrAltaOfAreaalmacenCollectionNewAreaalmacen.getAreaalmacenCollection().remove(areaalmacenCollectionNewAreaalmacen);
                        oldIdUsrAltaOfAreaalmacenCollectionNewAreaalmacen = em.merge(oldIdUsrAltaOfAreaalmacenCollectionNewAreaalmacen);
                    }
                }
            }
            for (Areaalmacen areaalmacenCollection1NewAreaalmacen : areaalmacenCollection1New) {
                if (!areaalmacenCollection1Old.contains(areaalmacenCollection1NewAreaalmacen)) {
                    Usuario oldIdUsrModOfAreaalmacenCollection1NewAreaalmacen = areaalmacenCollection1NewAreaalmacen.getIdUsrMod();
                    areaalmacenCollection1NewAreaalmacen.setIdUsrMod(usuario);
                    areaalmacenCollection1NewAreaalmacen = em.merge(areaalmacenCollection1NewAreaalmacen);
                    if (oldIdUsrModOfAreaalmacenCollection1NewAreaalmacen != null && !oldIdUsrModOfAreaalmacenCollection1NewAreaalmacen.equals(usuario)) {
                        oldIdUsrModOfAreaalmacenCollection1NewAreaalmacen.getAreaalmacenCollection1().remove(areaalmacenCollection1NewAreaalmacen);
                        oldIdUsrModOfAreaalmacenCollection1NewAreaalmacen = em.merge(oldIdUsrModOfAreaalmacenCollection1NewAreaalmacen);
                    }
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    Usuario oldIdUsrAltaOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getIdUsrAlta();
                    usuarioCollectionNewUsuario.setIdUsrAlta(usuario);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldIdUsrAltaOfUsuarioCollectionNewUsuario != null && !oldIdUsrAltaOfUsuarioCollectionNewUsuario.equals(usuario)) {
                        oldIdUsrAltaOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldIdUsrAltaOfUsuarioCollectionNewUsuario = em.merge(oldIdUsrAltaOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            for (Usuario usuarioCollection1NewUsuario : usuarioCollection1New) {
                if (!usuarioCollection1Old.contains(usuarioCollection1NewUsuario)) {
                    Usuario oldIdUsrModOfUsuarioCollection1NewUsuario = usuarioCollection1NewUsuario.getIdUsrMod();
                    usuarioCollection1NewUsuario.setIdUsrMod(usuario);
                    usuarioCollection1NewUsuario = em.merge(usuarioCollection1NewUsuario);
                    if (oldIdUsrModOfUsuarioCollection1NewUsuario != null && !oldIdUsrModOfUsuarioCollection1NewUsuario.equals(usuario)) {
                        oldIdUsrModOfUsuarioCollection1NewUsuario.getUsuarioCollection1().remove(usuarioCollection1NewUsuario);
                        oldIdUsrModOfUsuarioCollection1NewUsuario = em.merge(oldIdUsrModOfUsuarioCollection1NewUsuario);
                    }
                }
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    Usuario oldIdUsrAltaOfProductoCollectionNewProducto = productoCollectionNewProducto.getIdUsrAlta();
                    productoCollectionNewProducto.setIdUsrAlta(usuario);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldIdUsrAltaOfProductoCollectionNewProducto != null && !oldIdUsrAltaOfProductoCollectionNewProducto.equals(usuario)) {
                        oldIdUsrAltaOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldIdUsrAltaOfProductoCollectionNewProducto = em.merge(oldIdUsrAltaOfProductoCollectionNewProducto);
                    }
                }
            }
            for (Producto productoCollection1NewProducto : productoCollection1New) {
                if (!productoCollection1Old.contains(productoCollection1NewProducto)) {
                    Usuario oldIdUsrModOfProductoCollection1NewProducto = productoCollection1NewProducto.getIdUsrMod();
                    productoCollection1NewProducto.setIdUsrMod(usuario);
                    productoCollection1NewProducto = em.merge(productoCollection1NewProducto);
                    if (oldIdUsrModOfProductoCollection1NewProducto != null && !oldIdUsrModOfProductoCollection1NewProducto.equals(usuario)) {
                        oldIdUsrModOfProductoCollection1NewProducto.getProductoCollection1().remove(productoCollection1NewProducto);
                        oldIdUsrModOfProductoCollection1NewProducto = em.merge(oldIdUsrModOfProductoCollection1NewProducto);
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
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cliente> clienteCollectionOrphanCheck = usuario.getClienteCollection();
            for (Cliente clienteCollectionOrphanCheckCliente : clienteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cliente " + clienteCollectionOrphanCheckCliente + " in its clienteCollection field has a non-nullable idUsrAlta field.");
            }
            Collection<Cliente> clienteCollection1OrphanCheck = usuario.getClienteCollection1();
            for (Cliente clienteCollection1OrphanCheckCliente : clienteCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cliente " + clienteCollection1OrphanCheckCliente + " in its clienteCollection1 field has a non-nullable idUsrMod field.");
            }
            Collection<Areaalmacen> areaalmacenCollectionOrphanCheck = usuario.getAreaalmacenCollection();
            for (Areaalmacen areaalmacenCollectionOrphanCheckAreaalmacen : areaalmacenCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Areaalmacen " + areaalmacenCollectionOrphanCheckAreaalmacen + " in its areaalmacenCollection field has a non-nullable idUsrAlta field.");
            }
            Collection<Areaalmacen> areaalmacenCollection1OrphanCheck = usuario.getAreaalmacenCollection1();
            for (Areaalmacen areaalmacenCollection1OrphanCheckAreaalmacen : areaalmacenCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Areaalmacen " + areaalmacenCollection1OrphanCheckAreaalmacen + " in its areaalmacenCollection1 field has a non-nullable idUsrMod field.");
            }
            Collection<Usuario> usuarioCollectionOrphanCheck = usuario.getUsuarioCollection();
            for (Usuario usuarioCollectionOrphanCheckUsuario : usuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuario " + usuarioCollectionOrphanCheckUsuario + " in its usuarioCollection field has a non-nullable idUsrAlta field.");
            }
            Collection<Usuario> usuarioCollection1OrphanCheck = usuario.getUsuarioCollection1();
            for (Usuario usuarioCollection1OrphanCheckUsuario : usuarioCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuario " + usuarioCollection1OrphanCheckUsuario + " in its usuarioCollection1 field has a non-nullable idUsrMod field.");
            }
            Collection<Producto> productoCollectionOrphanCheck = usuario.getProductoCollection();
            for (Producto productoCollectionOrphanCheckProducto : productoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Producto " + productoCollectionOrphanCheckProducto + " in its productoCollection field has a non-nullable idUsrAlta field.");
            }
            Collection<Producto> productoCollection1OrphanCheck = usuario.getProductoCollection1();
            for (Producto productoCollection1OrphanCheckProducto : productoCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Producto " + productoCollection1OrphanCheckProducto + " in its productoCollection1 field has a non-nullable idUsrMod field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idUsrAlta = usuario.getIdUsrAlta();
            if (idUsrAlta != null) {
                idUsrAlta.getUsuarioCollection().remove(usuario);
                idUsrAlta = em.merge(idUsrAlta);
            }
            Usuario idUsrMod = usuario.getIdUsrMod();
            if (idUsrMod != null) {
                idUsrMod.getUsuarioCollection().remove(usuario);
                idUsrMod = em.merge(idUsrMod);
            }
            em.remove(usuario);
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

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
