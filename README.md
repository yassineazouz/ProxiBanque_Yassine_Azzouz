# ProxiBanque

ProxiBanque est une application bancaire simple développée en **Java** avec **Spring Boot**, permettant la gestion des agences, conseillers, clients, comptes et transactions. Elle offre la possibilité de créer des virements, consulter l’historique et gérer les relations clients-conseillers.

> ⚠️ Le code principal se trouve dans la branche `dev`.

---

## ⚙️ Technologies utilisées

- **Java 21**
- **Spring Boot**
- **Spring Data JPA** (avec PostgreSQL)
- **JUnit 5 + Mockito** pour les tests unitaires
- **Maven** pour la gestion des dépendances
- **PostgreSQL** pour la base de données
- **REST API** pour l’accès aux fonctionnalités

---

## 🏗️ Architecture

- `model/` : Entités JPA (Client, Advisor, Agency, Account, Transaction…)  
- `repository/` : Interfaces Spring Data pour les opérations CRUD  
- `service/` : Logique métier (virements, création de clients, comptes…)  
- `controller/` : Exposition des endpoints REST  
- `dto/` : Objets de transfert de données (DTO)  
- `mapper/` : Conversion entre entités et DTO  
- `test/` : Tests unitaires et d’intégration  

---

## 🚀 Fonctionnalités

1. Gestion des agences
2. Gestion des conseillers (`Advisor`)
3. Gestion des clients
4. Création et gestion des comptes (courant et épargne)
5. Virements entre comptes avec vérification des fonds
6. Historique des transactions

---

## 📦 Installation

1. Cloner le dépôt et passer sur la branche `dev` :  
```bash
git clone https://github.com/yassineazouz/ProxiBanque_Yassine_Azzouz
cd ProxiBanque_Yassine_Azzouz
git checkout dev
