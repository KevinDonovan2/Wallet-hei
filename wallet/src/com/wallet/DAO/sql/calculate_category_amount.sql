CREATE OR REPLACE FUNCTION calculateCategoryAmount(
    IN accountId INT,
    IN startDate TIMESTAMP,
    IN endDate TIMESTAMP
)
RETURNS TABLE (categoryName VARCHAR(255), totalAmount DECIMAL(12,6)) AS $$
BEGIN
    RETURN QUERY
    SELECT c.categoryName, 
    COALESCE(SUM(CASE WHEN t.amount THEN t.amount ELSE 0 END), 0) AS totalAmount
    FROM categories c
    LEFT JOIN transactions t ON c.idCategory = t.categoryId
        AND t.accountId = calculateCategoryAmount.accountId
        AND t.transactionDateTime BETWEEN startDate AND endDate
    GROUP BY c.categoryName;
END $$;
