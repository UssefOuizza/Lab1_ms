package com.example.extension;

import com.example.dao.IDao;

/**
 * Extension du DAO simulant une acquisition de mesure via un capteur matériel.
 */
public class DaoImpl2 implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO] Récupération de la valeur depuis le capteur...");
        double sensorMetric = 200.0;
        return sensorMetric;
    }
}
