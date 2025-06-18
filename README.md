
# 📢 Kunafoni

**Kunafoni** est une application console Java destinée à faciliter la diffusion d'informations et de notifications ciblées au sein d'une entreprise. Elle permet aux employés de s’abonner à des canaux de diffusion pour recevoir des messages internes, et au Service de Communication Interne (SCI) de gérer ces canaux et les utilisateurs.

---

## 📌 Fonctionnalités

### 👨‍💼 Employés
- Authentification
- Consultation des canaux de diffusion
- Abonnement / Désabonnement à des canaux
- Réception de notifications
- Envoi de messages dans les canaux
- Modification des informations du compte (nom, poste, mot de passe)

### 🏢 Service de Communication Interne (SCI)
- Authentification
- Configuration du compte entreprise
- Création de comptes employés
- Création et gestion des canaux de diffusion
- Ajout / retrait d’employés aux canaux
- Consultation des abonnés à un canal
- Envoi de messages

---

## 🧱 Architecture du projet

### 📁 Structure
```
kunafoni/
│
├── src/
│   └── main/
│       └── java/
│           └── com/mohdiop/
│               ├── Main.java
│               ├── authentication/
│               ├── database/
│               │   ├── daos/
│               │   └── daosimpls/
│               └── notification/
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

### 📸 Diagrammes UML

Insérez ici vos diagrammes de cas d’utilisation et de classes (ex. depuis Draw.io) :

#### Cas d'utilisation de l'Employé
![image](https://github.com/user-attachments/assets/ecf505db-9e9f-4eb0-bdb3-9df68715d425)

#### Cas d'utilisation du SCI
![image](https://github.com/user-attachments/assets/99d97e29-e24b-4fdd-a9dc-f51e9dece7f1)

#### Diagramme des classes
![image](https://github.com/user-attachments/assets/f26def9e-4553-4e30-9d3a-96d8c2fef793)

---

## 🧠 Principes de conception appliqués

- **SRP (Single Responsibility Principle)** : chaque classe a une responsabilité unique.
- **OCP (Open/Closed Principle)** : le système est extensible sans modification des classes existantes.
- **Observer Pattern** : utilisé pour notifier automatiquement les abonnés aux canaux.
- **Utilisation d’interfaces** pour isoler les implémentations (DAO/Service).
- **Couche de persistance PostgreSQL** avec accès via des DAO.

---

## 💾 Technologies utilisées

| Technologie     | Usage                       |
|----------------|-----------------------------|
| Java            | Développement principal     |
| PostgreSQL      | Base de données             |
| Gradle          | Gestion de projet et build  |
| Draw.io         | Conception UML              |

---

## 🔐 Configuration des variables d’environnement

Pour que l’envoi de notifications par email fonctionne, vous devez définir deux variables d’environnement dans votre système :

- `EMAIL` : l’adresse email utilisée pour envoyer les notifications (ex : votre adresse Gmail).
- `PASSWORD` : le mot de passe ou le mot de passe d’application associé.

### Sous Windows (CMD)
```cmd
set EMAIL=votre_email@gmail.com
set PASSWORD=mot_de_passe
```

---

## 🚀 Lancement du projet

### Prérequis
- Java 11 ou plus
- PostgreSQL installé et configuré
- Gradle (ou wrapper fourni)

### Étapes
1. Cloner le projet :
   ```bash
   git clone https://github.com/votre-utilisateur/kunafoni.git
   cd kunafoni
   ```

2. Configurer la base de données PostgreSQL.

  ```sql
  CREATE DATABASE kunafoni;
  ```
3. Lancer le projet :
   ```bash
   ./gradlew run
   ```
---

## 🙋 Auteur

Projet réalisé par **Mohamed Diop** – dans le cadre d’application de **Design Patterns** et principes **SOLID**.

---

## 📄 Licence

Ce projet est sous licence MIT – voir le fichier `LICENSE` pour plus d’informations.
