### Challenge 1: The Missing Title

**Solution**:

```sql
SELECT Title FROM Books WHERE BookID = 'B102';
```

### Challenge 2: The Popular Genre

**Solution**:

```sql
SELECT c.CategoryName , COUNT(*) AS NumberOfBooks
FROM Books b
JOIN Categories c ON b.CategoryID = c.CategoryID
GROUP BY c.CategoryName
ORDER BY NumberOfBooks DESC
LIMIT 1;
```

### Challenge 3: The Prolific Author

**Solution**:

```sql
SELECT a.Name, COUNT(*) As NumberOfBooks
FROM Books b
JOIN Authors a ON b.AuthorID = a.AuthorID
GROUP BY a.Name
ORDER BY NumberOfBooks DESC
LIMIT 1;
```

### Challenge 4: The Budgeting Dilemma

**Solution**:

```sql
SELECT AVG(Price) AS AveragePrice
FROM Books
WHERE CategoryID IN (SELECT CategoryID FROM Categories WHERE CategoryName = 'Adventure');
```

```sql
SELECT AVG(Price) AS AveragePrice
FROM Books b
JOIN Categories c ON b.CategoryID = c.CategoryID
WHERE c.CategoryName = "Adventure";
```

### Challenge 5: The Secret Order

**Solution**:

```sql
SELECT b.Title
FROM Orders o
JOIN Books b ON o.BookID = b.BookID
JOIN Customers c ON o.CustomerID = c.CustomerID
WHERE o.OrderDate = '2023-05-15' AND c.Name = "Emily Carter";
```

### Challenge 6: The International Connection

**Solution**:

```sql
SELECT b.Title, a.Name
FROM Books b
JOIN Authors a ON b.AuthorID = a.AuthorID
WHERE a.Country != 'USA';
```

```sql
SELECT Title
FROM Books
WHERE AuthorID IN (SELECT AuthorID
					FROM Authors
                    WHERE Country != 'USA');
```

### Challenge 7: The Big Sale

**Solution**:

```sql
SELECT b.Title
FROM Books b
LEFT JOIN Orders o ON b.BookID = o.BookID
WHERE o.OrderID IS NULL;
```
