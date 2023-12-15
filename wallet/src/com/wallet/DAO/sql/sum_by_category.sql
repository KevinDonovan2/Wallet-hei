CREATE OR REPLACE FUNCTION sum_by_category(
    IN account_id INT,
    IN date_start TIMESTAMP,
    IN date_end TIMESTAMP
)
RETURNS TABLE (category_name VARCHAR(255), total_amount DECIMAL(12,6)) AS $$
BEGIN
    RETURN QUERY
    SELECT
        c.category_name,
        COALESCE(SUM(CASE WHEN t.type_transaction = 'Category1' THEN t.montant ELSE 0 END), 0) AS total_amount
    FROM categories c
    LEFT JOIN transactions t ON c.category_id = t.category_id
                          AND t.account_id = account_id
                          AND t.transaction_date BETWEEN date_start AND date_end
    GROUP BY c.category_name;
END $$;
