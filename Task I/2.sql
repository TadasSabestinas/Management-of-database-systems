SELECT nr, pavadinimas
FROM Stud.Egzempliorius LEFT JOIN Stud.Knyga on Stud.Egzempliorius.isbn = Stud.Knyga.isbn
WHERE Knyga.pavadinimas = 'Duomenu bazes'