package com.example.dao;

/**
 * Contrat pour le composant d'accès aux données.
 */
public interface IDao {

    /**
     * Fournit une valeur numérique issue d'une source de données.
     *
     * @return La donnée mesurée ou persistée.
     */
    double getValue();
}
