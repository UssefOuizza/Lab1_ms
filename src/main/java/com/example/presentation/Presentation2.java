package com.example.presentation;

import com.example.dao.IDao;
import com.example.metier.IMetier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Presentation2 {

    public static void main(String[] args) {
        try {
            // Lecture des classes cibles depuis la configuration
            String[] configuredClasses = readConfigFile("config.txt");

            // Instanciation dynamique du composant DAO
            IDao daoService = (IDao) createInstance(configuredClasses[0]);
            System.out.println("[CONTAINER] DAO instancié : " + daoService.getClass().getName());

            // Instanciation dynamique du service Métier
            IMetier businessService = (IMetier) createInstance(configuredClasses[1]);
            System.out.println("[CONTAINER] Métier instancié : " + businessService.getClass().getName());

            // Injection par réflexion de la dépendance DAO dans le Métier
            bindDependency(businessService, "setDao", daoService);

            // Traitement et affichage du résultat
            double calculationOutput = businessService.calcul();
            System.out.println("Résultats = " + calculationOutput);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Charge les noms complets des classes spécifiées dans le fichier texte.
     */
    private static String[] readConfigFile(String configPath) throws IOException {
        String[] classEntries = new String[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configPath))) {
            for (int idx = 0; idx < classEntries.length; idx++) {
                classEntries[idx] = bufferedReader.readLine().trim();
            }
        }
        return classEntries;
    }

    /**
     * Crée un nouvel objet via son constructeur par défaut en utilisant la réflexion.
     */
    private static Object createInstance(String qualifiedClassName) throws Exception {
        Class<?> targetType = Class.forName(qualifiedClassName);
        Constructor<?> defaultConstructor = targetType.getDeclaredConstructor();
        return defaultConstructor.newInstance();
    }

    /**
     * Injecte dynamiquement un composant dans un objet cible via une méthode setter.
     */
    private static void bindDependency(Object targetObject, String mutatorName, Object dependencyBean) throws Exception {
        Class<?> destinationType = targetObject.getClass();
        Class<?> interfaceContract = dependencyBean.getClass().getInterfaces()[0];
        Method mutatorMethod = destinationType.getMethod(mutatorName, interfaceContract);
        mutatorMethod.invoke(targetObject, dependencyBean);
    }
}
