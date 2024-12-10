# **Guide d'Utilisation et Documentation**

## **Étape 1 : Cloner le Projet depuis Git**

Pour obtenir le code source du projet, exécutez la commande suivante dans votre terminal :

```bash
git clone <URL_DU_DEPOT>
```

#### **Exemple :**
```bash
git clone https://gitlab.info.uqam.ca/inf2050/2024-hiver/halma14.git
```

Accédez ensuite au répertoire du projet cloné :

```bash
cd halma14
```

---

## **Exigences Système**

### **Prérequis :**
1. **Java Development Kit (JDK)** :
   - Version requise : **Java 17** ou supérieure.
   - Téléchargez-le ici : [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) ou [OpenJDK](https://openjdk.org/).

2. **Apache Maven** :
   - Version requise : **3.8.0** ou supérieure.
   - Téléchargez-le ici : [Apache Maven](https://maven.apache.org/download.cgi).

3. **Système d'exploitation** :
   - Compatible avec Windows, macOS et Linux.

4. **Accès réseau** (facultatif pour les dépendances Maven) :
   - Assurez-vous que Maven peut télécharger les dépendances nécessaires depuis les dépôts en ligne.

---

## **Instructions de Compilation**

Pour compiler le projet et produire un fichier JAR exécutable :

1. Assurez-vous d'avoir installé **Java** et **Maven**.
2. Exécutez la commande suivante dans le répertoire racine du projet :

```bash
mvn clean package
```

Cette commande génère un fichier JAR dans le dossier `target`.

---

## **Exécution du Logiciel**

### **1. Exécution directe avec Maven**

Utilisez la commande suivante pour exécuter le jeu sans créer explicitement un fichier JAR :

```bash
mvn exec:java "-Dexec.args=<taille_du_plateau> <nom_joueur_1> <nom_joueur_2>"
```

#### **Exemples :**
```bash
mvn exec:java "-Dexec.args=4 Max Quentin"
mvn exec:java "-Dexec.args=4 AI Quentin"
```

### **2. Exécution à partir d'un fichier JAR**

Après la compilation, exécutez le fichier JAR généré avec la commande suivante :

```bash
java -jar target/<nom_du_jar>.jar <taille_du_plateau> <nom_joueur_1> <nom_joueur_2>
```

#### **Exemple :**
```bash
java -jar target/halma-1.0.jar 4 Max Quentin
```

---

## **Interface en Ligne de Commande**

### **Options à Fournir :**

#### **1. Taille du plateau**
- Spécifie la base du plateau sous forme d'entier :
  - Exemple : 
    - `3` pour un plateau de 9x9 cases.
    - `4` pour un plateau de 12x12 cases.

#### **2. Noms des joueurs**
- Indiquez les noms des joueurs humains ou utilisez `AI` pour un joueur robot :
  - Exemple : `4 Max Quentin` ou `4 AI Quentin`.

---

## **Règles de Validation des Arguments**

1. **Nombre de joueurs autorisé :**
   - Seuls **2 ou 4 joueurs** sont acceptés pour un plateau carré.
   - Si un nombre invalide de joueurs est fourni, le programme rejettera les arguments.

---

## **Profils pour les IA**

Le projet prend en charge deux profils d'IA pour les joueurs robots. Vous pouvez sélectionner un profil en spécifiant un **profil Maven** au moment de l'exécution.

### **1. Profil MadMax (par défaut)**
- Utilise un algorithme basé sur un générateur pseudo-aléatoire semé avec 42.

#### **Commande :**
```bash
mvn exec:java -Pmadmax "-Dexec.args=4 AI AI"
```

### **2. Profil Keksli**
- Trie les mouvements possibles et sélectionne toujours le premier.

#### **Commande :**
```bash
mvn exec:java -Pkeksli "-Dexec.args=4 AI Quentin"
```

> **Remarque :** Si aucun profil n'est explicitement sélectionné, le profil **MadMax** sera utilisé par défaut.

---

## **Résumé des Commandes**

### **Cloner le projet**
```bash
git clone <URL_DU_DEPOT>
cd <nom_du_projet>
```

### **Compilation**
```bash
mvn clean package
```

### **Exécution directe avec Maven**
```bash
mvn exec:java "-Dexec.args=<taille_du_plateau> <nom_joueur_1> <nom_joueur_2>"
```

### **Exécution à partir du JAR**
```bash
java -jar target/<nom_du_jar>.jar <taille_du_plateau> <nom_joueur_1> <nom_joueur_2>"
```

### **Changer de Profil d'IA**
- MadMax (par défaut) : `-Pmadmax`
- Keksli : `-Pkeksli`

---

## **Documentation API**

La documentation API générée automatiquement est disponible à l'adresse suivante :
[Documentation Javadoc](https://halma14-inf2050-2024-hiver-493699e8bddcac8e6977901fb90fc15dc638.pages.info.uqam.ca/)

---
