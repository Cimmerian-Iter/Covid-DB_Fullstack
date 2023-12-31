# Covid-DB_Fullstack

##Lancer le projet

Cloner le github localement et l'importer sur visual studio code.

Lancer la compilation du projet (soit à l'aide de l'extension java de VSCode, soit dans le menu gradle, en utilisant l'option build)
Ou
Lancer le .jar 

Le site sera accessible a l'addresse http://localhost:8001/

## Fonctionalités

# Système de Gestion des Centres de Vaccination

## Accès Public

### Rechercher des centres par ville (Lire)
Endpoint : `api/public/centers/city/{city}` 

### S'inscrire pour un rendez-vous de vaccination (Créer)
Endpoint : `/api/public/rendezvous/create`

## Accès Administratif

### Super Admin

#### Gestion des centres (CRUD)
- Créer un centre : `api/superadmin/centers/add` (Requête : `creationcentre.http`)
- Obtenir tous les centres : `api/public/centers/all/{id}`
- Supprimer un centre : `api/superadmin/center/deletion/{id}` (Requête : `suppressioncentre.http`)

#### Gestion des administrateurs (CRUD)
- Obtenir tous les utilisateurs admin : `api/superadmin/getAllAdminUsers` (Requête : `getadmin.http`)
- Supprimer un utilisateur : `api/superadmin/deleteUser/{id}` (Requête : `deleteadmin.http`)
- Créer un utilisateur admin : `api/superadmin/create` (Requête : `createadmin.http`)
- Mettre à jour un utilisateur : `/api/superadmin/updateUser/{id}` (Requête : `updateuser.http`)

### Administrateur

#### Gestion des médecins (CRU)
- Créer un utilisateur : `api/admin/create` (Requête : `createuser.http`)
- Obtenir tous les utilisateurs par ID du centre : `api/admin/getAllUsersByCenterId` (Requête : `getuser.http`)
- Mettre à jour un utilisateur autorisé : `api/admin/updateAuthorizedUser/{id}` (Requête : `updateuser.http`)

#### Gestion des rendez-vous (RD)
- Obtenir les rendez-vous par ID du centre : `api/admin/rendezvous/byCenterId` (Requête : `getrendezvous.http`)
- Supprimer un rendez-vous : `api/admin/rendezvous/delete/{id}` (Requête : `deleterendezvous.http`)

### Médecin

#### Rechercher une personne par nom (Lire)
- Endpoint : `/api/user/rendezvous/byName/{name}` (Requête : `searchbyname.http`)

#### Valider la vaccination d'une personne (Mettre à jour)
- Endpoint : `/api/user/rendezvous/vaccinate/{id}` (Requête : `vaccinate.http`)
