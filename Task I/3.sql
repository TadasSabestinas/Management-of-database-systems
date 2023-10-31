SELECT Skaitytojas.nr, Skaitytojas.vardas, Skaitytojas.pavarde,
COUNT (*) AS EgzemplioriuSkaicius,
SUM (CASE WHEN Knyga.verte IS NULL  THEN 10 ELSE Knyga.verte END) AS BendraVerte
FROM Stud.Skaitytojas, Stud.Egzempliorius, Stud.Knyga
WHERE Skaitytojas.nr = Egzempliorius.skaitytojas
AND Knyga.isbn = Egzempliorius.isbn
GROUP BY Skaitytojas.nr, Skaitytojas.vardas, Skaitytojas.pavarde
