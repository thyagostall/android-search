package com.thyago.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thyago on 6/1/16.
 */
public class SomeDataModel {
    private static int QTY = 6;

    public List<SomeDataEntity> findAll() {
        ArrayList<SomeDataEntity> result = new ArrayList<>(QTY);

        result.add(new SomeDataEntity(1, "Ford", "Taurus"));
        result.add(new SomeDataEntity(2, "Ford", "Fusion"));
        result.add(new SomeDataEntity(3, "Honda", "Civic"));
        result.add(new SomeDataEntity(4, "Chevrolet", "Monza"));
        result.add(new SomeDataEntity(5, "Fiat", "147"));
        result.add(new SomeDataEntity(6, "Fiat", "Oggy"));

        return result;
    }
}
