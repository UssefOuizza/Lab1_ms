package com.example.metier;

/**
 * Contrat définissant les traitements de la logique métier.
 */
public interface IMetier {

    /**
     * Exécute les règles métiers sur les données fournies par le DAO.
     *
     * @return Résultat numérique final après traitement.
     */
    double calcul();
}
