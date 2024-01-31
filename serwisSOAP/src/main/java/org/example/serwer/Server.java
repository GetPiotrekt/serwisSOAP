package org.example.serwer;

import org.example.client.ClientSOAP;

import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serwer nasłuchuje na porcie 12345...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Połączono z klientem: " + socket.getInetAddress());

                // Odbieramy zapytanie od klienta
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String zapytanieString = (String) in.readObject();

                // Konwertujemy String na SOAPMessage
                SOAPMessage zapytanie = ClientSOAP.przeksztalcNaWiadomoscSOAP(zapytanieString);

                // Pobieramy nazwę operacji (NumberToWords lub NumberToDollars)
                String operation = ClientSOAP.pobierzOperacjeZapytania(zapytanie);

                // Obsługujemy zapytanie
                SOAPMessage odpowiedz = obsluzZapytanie(operation, zapytanie);

                // Konwertujemy odpowiedź na String
                String serializedOdpowiedz = ClientSOAP.przeksztalcNaString(odpowiedz);

                // Wysyłamy odpowiedź do klienta
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(serializedOdpowiedz);
                out.flush();

                // Zamykamy połączenie
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SOAPMessage obsluzZapytanie(String operation, SOAPMessage zapytanie) throws Exception {
        // W zależności od operacji, przygotowujemy odpowiedź
        switch (operation) {
            case "NumberToWords":
                return ClientSOAP.funkcjaNumberToWords(ClientSOAP.pobierzWartoscZapytania(zapytanie));
            case "NumberToDollars":
                return ClientSOAP.funkcjaNumberToDollars(ClientSOAP.pobierzWartoscZapytania(zapytanie));
            default:
                throw new UnsupportedOperationException("Nieobsługiwana operacja: " + operation);
        }
    }
}
