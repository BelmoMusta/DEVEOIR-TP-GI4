# Projet de fin de Semestre Spring
- Java version : 17
- VM Options : --add-opens java.base/java.lang=ALL-UNNAMED
## Fonctionnalités
### Adaptation du Matareiel
La chouche DAO, Service et Controller s'adapte automatiquement graçe à "Java Reflection API". Il ne faut qu'a changer le fichier de configuration **application.properties** et les fichiers de messages. Suivant ces étapes
1. Adapter les champs annoté **#Configuration to change** dans **application.properties** & **resources/messages**
2. Adapter **com.ensa.gi.modele.Materiel** en déclarant des attributs dans le même ordre que **application.properties**
3. Ajouter les setters et le getters pour le modele spécifique pour un **Livre** ou **Chaise**
4. Adapter le schéma de la base de données.

### Internationalisation
- Il ne faut qu'ajouter les fichiers correspondants au messages dans **resources/messages/label_xx.properties** avec **xx** est l'id de la langue déstinée.
- **Note :** L'application automatiquement prendre la nouvelle langue en considération.
### L'expiration de la session
- Après une durée, configurée dans **application.properties**, sans activité dans l'application. L'utilisateur doit rentrer le mot de passe pour confirmer son identité.
### Système de journalisation
- La journalisation des événements suivants dans un dossier configuré dans **application.properies** :
  -- Tous les erreurs occurés dans l'application
  -- L'accès à la base de données et la modifications des tables.
  -- L'historique de tous les opérations d'authentification dans l'application (échouée, réussite)
### Single table inheritance
- Utilisation d'un héritage en une seul table entre la classe **Materiel** et ses sous classes qui permet d'ajouter un nouveau type de materiel rien avec la configuration
### Traçabilité de l'application
- Garder l'historique de tous les allocations faites dans l'application
### Recherche d'un materiel
- Pouvoir executer une recherche en utilisant tous les attributs d'un materiel dynamiquement et en utililsant la langue courante de l'application _(en utilisant '%LIKE%'')_:
- name:antigone,edition:folio <-> nom:antigone,edition:folio
### Tests unitaires
- Implémentation des tests unitaires pour valider les fonctionnalités essentielles de l'applications