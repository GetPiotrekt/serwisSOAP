package org.example;

import javax.xml.soap.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Tworzymy połączenie SOAP
            SOAPConnectionFactory fabrykaPolaczenia = SOAPConnectionFactory.newInstance();
            SOAPConnection polaczenieSOAP = fabrykaPolaczenia.createConnection();

            // Przygotowujemy zapytanie dla NumberToWords
            String urlWords = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";
            SOAPMessage zapytanieWords = funkcjaNumberToWords();

            // Wysyłamy zapytanie i odbieramy odpowiedź dla NumberToWords
            SOAPMessage odpowiedzWords = polaczenieSOAP.call(zapytanieWords, urlWords);

            // Przetwarzamy i wyświetlamy odpowiedź dla NumberToWords
            drukujOdpowiedz("NumberToWords", odpowiedzWords);

            // Przygotowujemy zapytanie dla NumberToDollars
            String urlDollars = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";
            SOAPMessage zapytanieDollars = funkcjaNumberToDollars();

            // Wysyłamy zapytanie i odbieramy odpowiedź dla NumberToDollars
            SOAPMessage odpowiedzDollars = polaczenieSOAP.call(zapytanieDollars, urlDollars);

            // Przetwarzamy i wyświetlamy odpowiedź dla NumberToDollars
            drukujOdpowiedz("NumberToDollars", odpowiedzDollars);

            // Zamykamy połączenie
            polaczenieSOAP.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SOAPMessage funkcjaNumberToWords() throws Exception {
        MessageFactory fabrykaWiadomosci = MessageFactory.newInstance();
        SOAPMessage wiadomoscSOAP = fabrykaWiadomosci.createMessage();
        SOAPPart czescSOAP = wiadomoscSOAP.getSOAPPart();

        SOAPEnvelope koperta = czescSOAP.getEnvelope();
        koperta.addNamespaceDeclaration("web", "http://www.dataaccess.com/webservicesserver/");
        SOAPHeader naglowekSOAP = koperta.getHeader();
        SOAPBody cialoSOAP = koperta.getBody();

        SOAPElement elementZapytaniaSOAP = cialoSOAP.addChildElement("NumberToWords", "web");
        SOAPElement elementParametruSOAP = elementZapytaniaSOAP.addChildElement("ubiNum", "web");
        elementParametruSOAP.addTextNode("123"); // tutaj mozna zmieniać wartość

        wiadomoscSOAP.saveChanges();

        wyswietlWiadomosc();

        return wiadomoscSOAP;
    }

    private static SOAPMessage funkcjaNumberToDollars() throws Exception {
        MessageFactory fabrykaWiadomosci = MessageFactory.newInstance();
        SOAPMessage wiadomoscSOAP = fabrykaWiadomosci.createMessage();
        SOAPPart czescSOAP = wiadomoscSOAP.getSOAPPart();

        SOAPEnvelope koperta = czescSOAP.getEnvelope();
        koperta.addNamespaceDeclaration("web", "http://www.dataaccess.com/webservicesserver/");
        SOAPHeader naglowekSOAP = koperta.getHeader();
        SOAPBody cialoSOAP = koperta.getBody();

        SOAPElement elementZapytaniaSOAP = cialoSOAP.addChildElement("NumberToDollars", "web");
        SOAPElement elementParametruSOAP = elementZapytaniaSOAP.addChildElement("dNum", "web");
        elementParametruSOAP.addTextNode("123"); // tutaj mozna zmieniać wartość

        wiadomoscSOAP.saveChanges();

        wyswietlWiadomosc();

        return wiadomoscSOAP;
    }

    private static void drukujOdpowiedz(String operation, SOAPMessage odpowiedzSOAP) throws Exception {
        SOAPBody cialoOdpowiedziSOAP = odpowiedzSOAP.getSOAPBody();
        String zawartoscOdpowiedzi = cialoOdpowiedziSOAP.getTextContent();

        System.out.println("Odpowiedź SOAP dla wartości '123'. Funkcja: (" + operation + "):");
        System.out.println(zawartoscOdpowiedzi.trim());
        System.out.println();
    }

    private static void wyswietlWiadomosc() throws SOAPException, java.io.IOException {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        String strMsg = new String(out.toByteArray());
        System.out.println(strMsg);
        System.out.println();
    }
}