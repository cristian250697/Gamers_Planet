package TestClass;

import entidades.Area;
import java.util.ArrayList;
import models.modeloArea;

public class TestModelArea {

    ArrayList<Area> areas;

    public void imprimeAreas() {
        modeloArea areaM = new modeloArea();
        areas = areaM.getAllAreas();

        for (Area area : areas) {
            System.out.println(area.getNombre());
        }
    }

    public static void main(String[] args) {
        TestModelArea obj = new TestModelArea();
        obj.imprimeAreas();
    }
}
