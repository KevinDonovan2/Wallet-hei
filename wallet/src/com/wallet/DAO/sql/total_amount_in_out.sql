CREATE OR REPLACE FUNCTION sumAmountInOut(
    IN accountId INT,
    IN startDate TIMESTAMP,
    IN endDate TIMESTAMP
)
RETURNS TABLE (totalCredit DECIMAL(9,6), totalDebit DECIMAL(9,6)) AS $$
BEGIN
    RETURN QUERY
    -- Calculer la somme des entrées
     SELECT COALESCE(SUM(t.amount), 0) AS totalCredit,
    FROM transactions t
    INNER JOIN categories c ON t.categoryId = c.idCategory
    WHERE t.accountId = sumAmountInOut.accountId
        AND t.transactionDateTime BETWEEN startDate AND endDate
        AND c.transactionType = 'credit';

    -- Calculer la somme des sorties
     SELECT COALESCE(SUM(t.amount), 0) AS totalDebit
    FROM transactions t
    INNER JOIN categories c ON t.categoryId = c.idCategory
    WHERE t.accountId = sumAmountInOut.accountId
        AND t.transactionDateTime BETWEEN startDate AND endDate
        AND c.transactionType = 'debit';
END $$;
