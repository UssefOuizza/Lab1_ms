package com.example.dao;

/**
 * Implémentation standard de l'accès aux données (version base de données).
 */
public class DaoImpl implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO] Récupération de la valeur depuis la source de données...");
        double dataRecord = 100.0;
        return dataRecord;
    }
}
