
#  Projet de fin d'année - Spring


#####  Application de gestion d'allocation et de restitution de matériel

## Conditions

---
    Java version : 17


## Aspect Techniques

---
    1- Respecter la décomposition des couches : dao, service, controller, modele...\
    2- Utilisation de JdbcTemplate\
    3- Injection des beans par annotations.

## Fonctionnalités

---
###Authentification d'utilisateur
    L'utilisateur se connecte à partir d'un username et d'un mot de passe
    L'utilisateur peut avoir un ou plusieurs rôles ( admin , custom User )
    Les infos sont déjà injectées dans la base de donnée : 
    Username : root   |  Password : 1234  | roles : admin user
    Username : faycal   |  Password : 0000  | roles : user
    Chaque rôle a son menu d'acceuil spécifique

###Gestion des matériaux
    Admin (lui seulement) peut :
    - Créer un nouveau matériel
    - Modifier les infos d'un matériel
    - Supprimer un matériel
    - Gérer la disponibilité d'un matériel
    - Afficher tous les matériaux alloués par les utilisateurs
---
    Un utilisateur user peut juste de : 
    - Chercher un matériel par son Id
    - Afficher les matériaux alloués par lui-même
    - Allouer ou retourner un matériel

