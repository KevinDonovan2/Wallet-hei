CREATE OR REPLACE FUNCTION sold_in_out(
    IN accountId INT,
    IN startDate TIMESTAMP,
    IN endDate TIMESTAMP
)
RETURNS DECIMAL(12,6) AS $$
DECLARE
    total_in DECIMAL(12,6);
    total_out DECIMAL(12,6);
BEGIN
    -- Calculer la somme des entrées
    SELECT COALESCE(SUM(montant), 0)
    INTO total_in
    FROM transactions
    WHERE account_id = compte_id
        AND transaction_date BETWEEN startDate AND endDate
        AND type_transaction = 'credit';

    -- Calculer la somme des sorties
    SELECT COALESCE(SUM(montant), 0)
    INTO total_out
    FROM transactions
    WHERE account_id = compte_id
        AND transaction_date BETWEEN startDate AND endDate
        AND type_transaction = 'debit';

    -- Retourner la différence entre les entrées et les sorties
    RETURN total_in - total_out;
END $$;
