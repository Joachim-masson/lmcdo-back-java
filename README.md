# ☀️ Les Mystérieuses Cités d'Or — Back-End Java API

<p align="center">
  <strong>L'API Back-End robuste et thématique conçue en Java (Spring Boot) pour propulser le grand registre des explorateurs de la galaxie LMCDO.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Status-En%20D%C3%A9veloppement-orange?style=for-the-badge" alt="Status">
  <img src="https://img.shields.io/badge/Environment-Local-lightgrey?style=for-the-badge" alt="Environnement">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-3.4.1-6DB33F?style=flat-square&logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Maven-3.9-C71A36?style=flat-square&logo=apache-maven&logoColor=white" alt="Maven">
</p>

---

## 🧭 Le Concept du Projet

Ce projet constitue le moteur **Back-End (API REST)** de l'univers **LMCDO** (Les Mystérieuses Cités d'Or). Développé en **Java avec Spring Boot**, il a pour rôle de gérer l'intégralité des données de l'application, d'assurer la persistance en base de données et de répondre aux requêtes du Front-End React.

Il sert de vitrine technique pour valider et consolider mes compétences en architecture d'API, gestion de base de données relationnelle (MySQL) et sécurisation des échanges (CORS, gestion des statuts utilisateurs).

---

## 🏛️ Fonctionnalités de l'API

L'API expose un ensemble de points d'accès (endpoints) structurés pour administrer la base de données des utilisateurs (les "Explorateurs") :

*   👥 **Gestion des Explorateurs (CRUD complet) :**
    *   **Create :** Inscription et enregistrement de nouveaux profils.
    *   **Read :** Récupération de la liste globale ou des détails d'un utilisateur spécifique.
    *   **Update :** Modification des profils (nom, email, rôle, statut d'activité).
    *   **Delete :** Gestion de la suppression (Soft Delete / Hard Delete).
*   🗿 **Gestion des Personnages / Characters (CRUD complet) :**
    *   **Create :** Ajout de nouveaux héros, alliés ou antagonistes à l'aventure.
    *   **Read :** Récupération de la liste complète des personnages ou de la fiche détaillée d'un héros (Esteban, Zia, Tao...).
    *   **Update :** Mise à jour des informations, descriptions ou attributs d'un personnage (ex: modifier la description du Grand Condor).
    *   **Delete :** Retrait d'un personnage de la base de données.
*   🛡️ **Modération & Sécurité :**
    *   Gestion fine des rôles applicatifs (`USER`, `MODERATOR`, `ADMIN`).
    *   Fonctionnalité de **bannissement immédiat** avec enregistrement de l'horodatage (`bannedAt`) et désactivation du compte (`isActive`).
*   🌐 **Configuration CORS robuste :** Intégration d'un système de filtrage global autorisant les requêtes complexes (`PATCH`, `PUT`, `OPTIONS`) pour communiquer de manière fluide et sécurisée avec le serveur de développement Front-End.

---

## 🔌 Configuration et Base de Données

Le projet utilise des variables de configuration pour s'adapter à ton environnement local.

1. Crée ou modifie le fichier `src/main/resources/application.properties`.
2. Configure l'accès à ta base de données locale (MySQL) :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lmcdo_db
spring.datasource.username=ton_utilisateur
spring.datasource.password=ton_mot_de_passe

# Hibernate / JPA settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🚀 Lancement en Local

Pour faire tourner le Front-End sur ton poste :
1. **Cloner le projet :**
```bash 
  git clone [https://github.com/Joachim-masson/lmcdo-back-java.git](https://github.com/Joachim-masson/lmcdo-back-java.git)
  cd lmcdo-back-java
```

2. **Générer le build et installer les dépendances avec Maven :**
```bash
  ./mvnw clean install
```
(ou mvn clean install si Maven est installé globalement sur ta machine).
3. **Lancer l'application Spring Boot :**
```bash
  ./mvnw spring-boot:run
```

L'API sera accessible par défaut localement sur : http://localhost:9000 (ou le port défini dans tes propriétés).

---
## 🛠️ Stack Technique
Langage : Java 21 (ou version supérieure compatible)

Framework : Spring Boot 3.x (Spring Web, Spring Data JPA)

Base de données : MySQL 

Gestionnaire de dépendances : Maven

---

## 👤 Auteur
Joachim Masson — Développeur Full-Stack Junior

Portfolio en ligne : joachim-masson.vercel.app

GitHub : [joachim-masson](https://github.com/Joachim-masson)
