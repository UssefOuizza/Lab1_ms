package com.example.metier;

import com.example.dao.IDao;

/**
 * Couche métier fournissant les algorithmes de traitement.
 * Fonctionne en couplage faible avec une implémentation de l'interface IDao.
 */
public class MetierImpl implements IMetier {

    // Référence vers le contrat de persistance
    private IDao dao;

    /**
     * Point d'injection pour assigner le composant DAO.
     *
     * @param daoSource Instance concrète satisfaisant l'interface IDao.
     */
    public void setDao(IDao daoSource) {
        this.dao = daoSource;
    }

    @Override
    public double calcul() {
        if (this.dao == null) {
            throw new IllegalStateException("Erreur : la référence DAO n'a pas été initialisée.");
        }

        // Récupération des données depuis la couche d'accès aux données
        double sourceValue = this.dao.getValue();

        // Traitement métier : transformation de la valeur
        double computedValue = sourceValue * 2;

        System.out.println("[METIER] Calcul effectué. Résultat : " + computedValue);
        return computedValue;
    }
}
