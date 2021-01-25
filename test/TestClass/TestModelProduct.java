package TestClass;

import entidades.Producto;
import java.util.ArrayList;
import models.modelProducto;

public class TestModelProduct {


    public TestModelProduct() {

    }

    public void testGetAllProducts() {
        modelProducto obj = new modelProducto();
        obj.eliminarProducto(1);
    }

    public static void main(String[] args) {
        TestModelProduct test = new TestModelProduct();
        test.testGetAllProducts();
    }
}
