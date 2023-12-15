CREATE OR REPLACE FUNCTION sold_in_out(
    IN account_id INT,
    IN date_start TIMESTAMP,
    IN date_end TIMESTAMP
)
RETURNS DECIMAL(9,6) AS $$
DECLARE
    total_in DECIMAL(9,6);
    total_out DECIMAL(9,6);
BEGIN
    -- Calculer la somme des entrées
    SELECT COALESCE(SUM(montant), 0)
    INTO total_in
    FROM transactions
    WHERE account_id = compte_id
        AND transaction_date BETWEEN date_start AND date_end
        AND type_transaction = 'ENTREE';

    -- Calculer la somme des sorties
    SELECT COALESCE(SUM(montant), 0)
    INTO total_out
    FROM transactions
    WHERE account_id = compte_id
        AND transaction_date BETWEEN date_start AND date_end
        AND type_transaction = 'SORTIE';

    -- Retourner la différence entre les entrées et les sorties
    RETURN total_in - total_out;
END $$;
