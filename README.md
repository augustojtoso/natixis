# Natixis - Taches
Une API Restful permettant de gérer une liste de tâches à faire.
## Installation
```bash
mvn install 
```

## Usage

- Récupérer la liste de toutes les tâches

```bash
GET http://localhost:8080/tache
```

- Récupérer les tâches à effectuer
```bash
GET http://localhost:8080/tache?s=aeffectuer
```
- Récupérer une tâche par son ID
```bash
GET http://localhost:8080/tache/{id}
```
- Ajouter des tâches
```bash
POST http://localhost:8080/tache
Example body: 
{
  "label":"la description de la tâche",
  "complete":true
}
```
- Changer le statut d'une tâche
```bash
PATCH http://localhost:8080/tache/{id}
Example body: 
{
  "newStatus":true
}
```