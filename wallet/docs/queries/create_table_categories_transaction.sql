--response TD2
DROP TABLE IF EXISTS;
CREATE TABLE IF NOT EXISTS categories (
    idCategory varchar(255) primary key default gen_random_uuid(),
    categoryName varchar(100) unique not null,
    transactionType varchar(100) not null CHECK (transactionType IN ('Credit', 'debit', 'pret'))
);
-- response TD2 1.e. Les categories dans le Wallet by BudgetBakers 
--NOURRITURE ET BOISSONS
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Nourriture et Boissons', 'Credit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Nourriture et Boissons' AND transactionType = 'Credit'
);
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Nourriture et Boissons', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Nourriture et Boissons' AND transactionType = 'Debit'
);
-- Restaurant, fast-food
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Restaurant, fast-food', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Restaurant, fast-food' AND transactionType = 'Debit'
);
-- Logement
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Logement', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Logement' AND transactionType = 'Debit'
);
--Vehicule 
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Véhicule', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Véhicule' AND transactionType = 'Debit'
);
--Loisirs
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Achat de billets de loterie', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Achat de billets de loterie' AND transactionType = 'Debit'
);
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Gains de loterie', 'Credit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Gains de loterie' AND transactionType = 'Credit'
);
--Multimédia, Informatique
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Dépenses Multimédia, Informatique', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Dépenses Multimédia, Informatique' AND transactionType = 'Debit'
);
-- Amendes
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Amendes', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Amendes' AND transactionType = 'Debit'
);
--Pret
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Prêt', 'Credit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Prêt' AND transactionType = 'Credit'
);
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'remboursement', 'Debit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'remboursement' AND transactionType = 'Debit'
);
-- Salaire
INSERT INTO categories (idCategory, categoryName, transactionType)
SELECT gen_random_uuid(), 'Salaire', 'Credit'
WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE categoryName = 'Salaire' AND transactionType = 'Credit'
);





