# Projet Aventurier

## Description
Ce projet simule le déplacement d'un aventurier sur une carte contenant des obstacles (bois). L'aventurier peut se déplacer dans quatre directions (N,S,E,O) et doit éviter les collisions avec les bois et les bords de la carte.

## Prérequis
- Java 8+
- Maven/Gradle

## Installation
```bash
# Cloner le repository
git clone git@github.com:WacimElhaimer/ExoAventurier-Cleva.git
cd ExoAventurier-Cleva

# Compiler le projet
mvn clean install
```

## Utilisation
### Format des fichiers d'entrée

#### Fichier de carte (carte.txt)
```
C 10 10    # Carte de taille 10x10
B 1 1      # Bois en position (1,1)
A 0 0 S    # Aventurier en position (0,0) orienté vers le Sud
```

#### Fichier de mouvements (movements.txt)
```
NSEO    # Séquence de mouvements (Nord, Sud, Est, Ouest)
```

### Exécution
```bash
java -jar target/aventurier.jar map.txt movements.txt
```

## Tests
```bash
# Exécuter les tests unitaires
mvn test

# Exécuter les tests d'intégration
mvn verify
```

## Structure du Projet
```
src/
├── main/java/
│   └── com/adventurer/
│       ├── model/
│       │   ├── Map.java           # Gestion de la carte
│       │   └── Adventurer.java    # Logique de l'aventurier
│       ├── engine/
│       │   └── GameEngine.java    # Moteur de jeu
│       └── util/
│           └── FileParser.java    # Lecture des fichiers
└── test/java/
    └── com/adventurer/
        ├── model/
        │   ├── MapTest.java
        │   └── AdventurerTest.java
        ├── engine/
        │   └── GameEngineTest.java
        ├── util/
        │   └── FileParserTest.java
        └── integration/
            └── IntegrationTest.java    # Tests du sujet
```
## Contribution
Les contributions sont les bienvenues ! Veuillez suivre ces étapes :
1. Fork du projet
2. Création d'une branche (`git checkout -b feature/amelioration`)
3. Commit des changements (`git commit -m 'Ajout de fonctionnalité'`)
4. Push vers la branche (`git push origin feature/amelioration`)
5. Création d'une Pull Request

## License
[MIT License](LICENSE) 