# Rapport de Contribution - [TP3 Equipe 14]

## Membres de l'équipe
| Nom                   | Groupe      |
|-----------------------|--------------------------------------|
| Ayoub EL mouhtassib   | Groupe 14      |
| Mehdi Ghanem          | Groupe 14      |
| Ange Wu               | Groupe 14      |

---

## Répartition des tâches

### [Ayoub El mouhtassib]
- **Tâches effectuées :**
  - [Tâche 1 : Pom.xml]
    - **details :** Correction des erreurs dans tp2, rajout toutes dependances necessaires,repository,dependences des interfaces et tests, plugin jar,javadoc,test integration,checkstyle, creation 2 profils MadMax et Keksli dans pom
    - **Resultat :** pom.xml fonctionnel et sans erreurs
  - [Tâche 2 : .gitlab-ci.yml]
    - **details :** Creation du fichier, ecritures des stages (job) avec tous leurs parametres
    - **Resultat :** tous les jobs passent et javadoc generé automatiquement sur site web
  - [Tâche 3 : ReadMe.md]
    - **details :** Creation du fichier, ecriture de toutes les etapes necessaire pour l'installation du projet sur maven
    - **Resultat :** belle representation du fichier en format .md avec lien vers javadoc sur le web
  - [Tâche 3 : Classes Java]
    - **details :** Mise a jour BoardImplTest selon Junit 4
    - **details :** Creation Nouveau test BoardImpl2Test
    - **details :** Creation MadMaxIntegrationTest
    - **details :** Rajout KeksliMoveSelector et MadMaxMoveSelector avec MadMaxMoveSelectorTest
    - **details :** Rajout Class AbstractSquareFactoryTest
    - **details :** Mise a jour DefaultConsoleLauncher et integration runtp03
  - [Tâche 3 : Branches]
    - **details :** Creation nouvelle branche "SecondBranch" avec rajout de DefaultConsoleLauncherTest et merge reussi avec pipeline reussi aussi
    - **details :** Integration lien siteweb javadoc dans ReadMe




### [Mehdi Ghanem]
- **Tâches effectuées :**
  - [Tâche 1 : Classes Java]
  - **details :** Mise a jour BoardImpl pour tenter de combler les trous du TP2 (initialisation des pions et rendre la partie jouable entre autres)
  - **Resultat :** Je n'ai pas reussi a creer les pions et donc a rendre le jeu jouable
  - **details :** Mise a jour ModelImpl pour tenter de combler les trous du TP2 (initialisation des pions et rendre la partie jouable entre autres)
  - **Resultats :** Je n'ai pas reussi a creer les pions et donc a rendre le jeu jouable
  - **details :** Mise a jour ControllerImpl pour tenter de l'adapter au lanceur TP3
  - **Resultats :** Je ne suis pas parvenu a aller au bout de mon travail (voir commentaire)
  - **details :** Mise a jour SquareModelFactory pour tenter de l'adapter au lanceur TP3
  - **Resultats :** Je ne suis pas parvenu a aller au bout de mon travail (voir commentaire)
  - **details :** Implementation des deux joueurs IA
  - **Resultats :** Je ne suis pas parvenu a aller au bout de mon travail (voir commentaire)
- **Commentaires :**
  - Mon role dans ce TP consistait principalement a combler tout ce que l'on a pas reussi a faire au cours du TP2. Cependant, je n'ai pas pu contribuer autant qu'a mon habitude car mon ordinateur s'est cassé, perdant donc accès à mon travail. Et n'ayant pas d'appareil de rechange qui puisse faire la job, je n'ai donc pas pu pousser mon code sur gitlab car étant incomplet et n'ayant plus accès à mon appareil pour coder. Je n'ai donc pas pu être aussi impactant dans le travail de mon équipe qu'au cours des deux premiers TP (voir mes contributions au TP1 et TP2 pour preuve).

  Je vous prie donc de prendre cela en consideration pour juger nos contributions respectives.

### [Ange Wu]
- **Tâches effectuées :**
- **Commentaires :**
  - N'a pas contribué au TP1, n'a pratiquement pas contribué au TP2 et n'a rien fait au TP3, et ce tout en ne donnant jamais signe d'implication dans l'équipe. Durant tout le semestre, le travail se faisait uniquement à deux (Ayoub et Mehdi). Je vous prie de prendre cela en considération pour les notes finales, car cela a eu une grande conséquence sur nos notes des TPs.

---


## Résumé des contributions

- Interfaces et tests abstraits intégrés via le dépôt Maven tiers 	10% ==> fait dans le pom par Ayoub
- Build côté serveur configuré sur GitLab CI et réussite 	10% ==> fait par Ayoub
- Les deux joueurs IA implémentés 	10% ==> **A Faire**
- Profils de construction fonctionnels pour MadMax et Keksli (JAR + exec lanceur) 	10% ==> **A faire**
- Tests d'intégration Mad Max dans la phase verify 	10% ==> partiellement fait par Ayoub **A finaliser**
- Couverture des tests > 90 %, appliquée dans le pom (lignes de code du contrôleur + modèle) 	10% ==> **A faire dans les class , pour le pom, le plugin est deja la**
- Checkstyle appliqué dans le pom, sans avertissements 	10% ==> fait par Ayoub
- Javadoc appliqué dans le pom, sans avertissements 	10% ==> fait par Ayoub
- Documentation d'installation et d'utilisation dans README.md + lien vers les pages javadoc 	5% ==> fait par Ayoub
- Au moins une demande de fusion acceptée depuis une branche de fonctionnalité 	5% ==> fait par Ayoub et Mehdi
- Tous les tests fournis passent 	5% ==> fait par Ayoub
- Répo sans encombrement, pas de code généré ni de fichiers binaires 	5% ==> fait par Ayoub et Mehdi

---

**Note :** Ce document peut être mis à jour au fur et à mesure de l'avancement du projet.
