package TestClass;

import entidades.Producto;
import java.util.ArrayList;
import models.modelProducto;

public class TestModelProduct {

    ArrayList<Producto> productos;

    public TestModelProduct() {
        productos = new ArrayList<>();
    }

    public void testGetAllProducts() {
        modelProducto productoM = new modelProducto();
        productos = productoM.getAllProducts();

        for (Producto producto : productos) {
            System.out.println(producto.getNombre());
        }
    }

    public static void main(String[] args) {
        TestModelProduct test = new TestModelProduct();
        test.testGetAllProducts();
    }
}
