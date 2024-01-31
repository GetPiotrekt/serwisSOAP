package org.example.client;

import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.net.Socket;

// Klasa Client
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            // Przygotowujemy wartość dla NumberToWords (można zmieniać wartość)
            String valueForWords = "123";

            // Przygotowujemy zapytanie dla NumberToWords
            SOAPMessage zapytanieWords = ClientSOAP.funkcjaNumberToWords(valueForWords);

            // Konwertujemy SOAPMessage na String
            String serializedMessageWords = ClientSOAP.przeksztalcNaString(zapytanieWords);

            // Wysyłamy zapytanie o konwersję numeru na wyraz do serwera
            ObjectOutputStream outWords = new ObjectOutputStream(socket.getOutputStream());
            outWords.writeObject(serializedMessageWords);
            outWords.flush();

            // Odbieramy odpowiedź od serwera dla NumberToWords
            ObjectInputStream inWords = new ObjectInputStream(socket.getInputStream());
            String odpowiedzWordsString = (String) inWords.readObject();

            // Konwertujemy String z odpowiedzi na SOAPMessage
            SOAPMessage odpowiedzWords = ClientSOAP.przeksztalcNaWiadomoscSOAP(odpowiedzWordsString);

            // Przetwarzamy i wyświetlamy odpowiedź dla NumberToWords
            ClientSOAP.wyswietlOdpowiedz("NumberToWords", odpowiedzWords);

            // Przygotowujemy wartość dla NumberToDollars (można zmieniać wartość)
            String valueForDollars = "456";

            // Przygotowujemy zapytanie dla NumberToDollars
            SOAPMessage zapytanieDollars = ClientSOAP.funkcjaNumberToDollars(valueForDollars);

            // Konwertujemy SOAPMessage na String
            String serializedMessageDollars = ClientSOAP.przeksztalcNaString(zapytanieDollars);

            // Wysyłamy zapytanie o konwersję numeru na dolary do serwera
            ObjectOutputStream outDollars = new ObjectOutputStream(socket.getOutputStream());
            outDollars.writeObject(serializedMessageDollars);
            outDollars.flush();

            // Odbieramy odpowiedź od serwera dla NumberToDollars
            ObjectInputStream inDollars = new ObjectInputStream(socket.getInputStream());
            String odpowiedzDollarsString = (String) inDollars.readObject();

            // Konwertujemy String z odpowiedzi na SOAPMessage
            SOAPMessage odpowiedzDollars = ClientSOAP.przeksztalcNaWiadomoscSOAP(odpowiedzDollarsString);

            // Przetwarzamy i wyświetlamy odpowiedź dla NumberToDollars
            ClientSOAP.wyswietlOdpowiedz("NumberToDollars", odpowiedzDollars);

            // Zamykamy połączenie
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

