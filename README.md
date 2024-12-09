# Instructions de Compilation et d'Exécution

## Instructions de Compilation

Pour compiler le projet et produire le fichier JAR, exécutez les commandes suivantes dans le répertoire racine du projet :

```bash
mvn clean package
```

Cette commande générera un fichier JAR dans le dossier `target`.

## Instructions d'Exécution

Vous pouvez exécuter le jeu directement avec Maven ou en utilisant le fichier JAR généré.

### Exécution avec Maven

```bash
mvn exec:java "-Dexec.args=<taille_du_plateau> <nom_joueur_1> <nom_joueur_2>"
```

#### Exemples :
```bash
mvn exec:java "-Dexec.args=4 Max Quentin"
mvn exec:java "-Dexec.args=3 Max Quentin Ryan Hafedh"
```

### Exécution à partir du fichier JAR

#### Exemples :
```bash
java -jar target/*jar 4 Max Quentin
```

---

## Interface en Ligne de Commande

### Options fournies par l'utilisateur

1. **Taille du plateau :**
   - Un entier spécifiant la base du plateau (ex. 3 pour un plateau 9x9, 4 pour un plateau 12x12).

2. **Noms des joueurs :**
   - Les noms des joueurs humains ou l'IA (`AI` pour les joueurs robots).
   - Exemple : `4 Max Quentin` ou `4 AI Quentin`.

### Règles de validation des arguments

- **Seuls 2 ou 4 joueurs** sont acceptés pour un plateau carré.
- Si le nombre de joueurs est invalide, le programme rejettera les arguments.

---

## Profils d'IA

Le projet prend en charge deux profils d'IA :

1. **MadMax (par défaut) :**
   - Utilise un algorithme aléatoire basé sur un générateur de nombres pseudo-aléatoires semé avec 42.
   - **Commande :**
     ```bash
     mvn exec:java -Pmadmax "-Dexec.args=4 AI AI"
     ```

2. **Keksli :**
   - Trie les mouvements possibles et sélectionne le premier.
   - **Commande :**
     ```bash
     mvn exec:java -Pkeksli "-Dexec.args=4 AI Quentin"
     ```

- Si aucun profil n'est explicitement spécifié, **MadMax sera utilisé par défaut.**

---

## Tests

### Exécution des Tests

Pour exécuter les tests unitaires et d'intégration, utilisez la commande suivante :

```bash
mvn test
```

### Couverture de Test

Pour générer un rapport de couverture combinée (unitaires + intégration) avec Jacoco :

```bash
mvn verify
```

Les rapports seront disponibles dans le dossier `target/site/jacoco/`.

---

## Documentation API

La documentation API est automatiquement générée et disponible via le job pages du pipeline CI/CD sur GitLab.

### Pour consulter la documentation en local :
```bash
mvn javadoc:javadoc
```

Les fichiers seront générés dans le dossier `target/site/apidocs/`.
