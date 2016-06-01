package com.thyago.search;

import java.util.ArrayList;

/**
 * Created by thyago on 6/1/16.
 */
public class SomeDataModel {
    private static int QTY = 10;

    public Iterable<SomeDataEntity> findAll() {
        ArrayList<SomeDataEntity> result = new ArrayList<>(QTY);

        result.add(new SomeDataEntity(1, "Ford", "Taurus"));
        result.add(new SomeDataEntity(2, "Ford", "Fusion"));
        result.add(new SomeDataEntity(3, "Honda", "Civic"));

        return result;
    }
}
