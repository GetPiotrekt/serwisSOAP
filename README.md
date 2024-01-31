-----===== Serwis SOAP Konwersji Liczb =====-----

Link: https://www.dataaccess.com/webservicesserver/NumberConversion.wso
    - Opis: Serwis SOAP Konwersji Liczb dostarcza funkcji do przekształcania liczb na słowa lub kwoty dolarów.


-----===== Wynik Działania =====-----

"
Odpowiedź SOAP dla wartości '123456'. Funkcja: (NumberToWords):
one hundred and twenty three thousand four hundred and fifty six



Odpowiedź SOAP dla wartości '123456'. Funkcja: (NumberToDollars):
one hundred and twenty three thousand four hundred and fifty six dollars
"

-----===== Wykorzystywane Metody i Publiczne Interfejsy =====-----

/// Serwer \\\

1. Metoda Main
   - Opis: Uruchamia serwer nasłuchujący na określonym porcie.
   - Parametry: Brak

2. konwertujLiczbeNaSlowo(int liczba)
   - Opis: Konwertuje liczbę na słowo.
   - Parametr: `liczba` - Liczba do przekonwertowania.

3. konwertujLiczbeNaDolary(int liczba)
   - Opis: Konwertuje liczbę na kwotę dolarów.
   - Parametr: `liczba` - Liczba do przekonwertowania.

/// Klient \\\

1. Metoda Main
   - Opis: Uruchamia klienta łączącego się z serwerem na określonym porcie.
   - Parametry: Brak

2. zadajZapytanieKonwersjiLiczbyNaSlowo(int liczba)
   - Opis: Wysyła żądanie do serwera konwertującego liczbę na słowo.
   - Parametr: `liczba` - Liczba do przekonwertowania.

3. zadajZapytanieKonwersjiLiczbyNaDolary(int liczba)
   - Opis: Wysyła żądanie do serwera konwertującego liczbę na kwotę dolarów.
   - Parametr: `liczba` - Liczba do przekonwertowania.



-----===== Protokoły Sieciowe =====-----

SOAP (Simple Object Access Protocol)
   - Opis: Protokół do komunikacji między systemami, wykorzystywany w serwisach webowych.



-----===== Testy (powiadomienia o błędach) =====-----

1. Testowanie Połączenia
   - Opis: Sprawdzenie, czy klient może poprawnie połączyć się z serwerem.

2. Testowanie Metody konwertujLiczbeNaSlowo
   - Opis: Sprawdzenie, czy serwer poprawnie konwertuje liczbę na słowo.

3. Testowanie Metody konwertujLiczbeNaDolary
   - Opis: Sprawdzenie, czy serwer poprawnie konwertuje liczbę na kwotę dolarów.
