
# Devoir Spring: Application de gestion de materiel
Version de Java utilise: 11


## les fonctionalite de l'application selon le role de l'utilisateur:
N.B: Un Utilisateur peut avoir plus qu'un role (Ex: administrateur et utilisateur)
### utilisateur ayant le role: Employee

1. Chercher Un Materiel:
    - Recherche Par Identifiant : Si le materiel n'existe pas, une message convenable s'affichera
    - Recherche Par Type de Materiel
2. Allouer Un Materiel:
    - Entrer l'identifiant du material a allouer, Puis saisir la duree de location en jours. Si le materiel est indisponible ou tous le stock est deja alloue, une message convenable s'affichera puis retourne au menu principale
3. Rendre Un Materiel:
    - Si l'utilisateur a des materiaux a rendre aujourd'hui, une message d'attention s'affichera avant le menu principal
    - Si l'utilisateur veut rendre un materiel, il doit entrer l'identifiant du materiel a rendre.
4. Afficher La Liste Des Materiaux Alloues Par Un Utilisateur:
    - Afficher les informations de tous les materiaux alloues par l'utilisateur authentifie, si il y'en a rien, l'application lui affichera une message convenable.
5. Afficher la liste de tous les materiaux.

### Utilisateur Ayant Le Role: administrateur
N.B: Un utilisateur qui a le role administrateur a tous les privilege d'un employee plus ceux-ci:
1. Creer Un Nouveau Materiel:
    - Pour cree un materiel il faut saisir, son nom, selectionner un type de materiel existant ou creer un nouveau type, le stock et la disponibilite.
2. Modifier Un Materiel Existant:
    - saisir les nouvaux informations sur le materiel avec certains conditions: le stock ne doit pas etre inferieur a le nombre de location du materiel a modifier, si le nombre est superieur, l'application s'afficera une message d'erreur puis retourne au menu principale
3. Supprimer Un Materiel Existant:
    - condition de suppression: le materiel ne doit pas etre alloue par un utilisateur
4. Marquer Un Materiel Comme Disponible/Indisponible:
    - Si le materiel est disponible, marquer-le comme disponible, sinon marquer-le comme disponible
5. Afficher La Liste Des Materiaux Allour Par Chaque Utilisateur:
    - Afficher tous les information sur les materiaux alloues par chaque utilisateur

## Des Information Supplementaire
- Les Chaines de caractere vide ne sont pas traites dans l'application
- Algorithme de cryptage: sha256
- Pour se Connecter comme etant un admin:
    - username: mohamed1302
    - password: 1234567890
- Pour se connected comme etant un employee:
    - username: gilgamesh1302
    - password: 1234567890
