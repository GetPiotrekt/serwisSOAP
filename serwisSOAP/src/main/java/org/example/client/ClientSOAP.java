package org.example.client;

import javax.xml.soap.*;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

public class ClientSOAP {

    public static SOAPMessage przeksztalcNaWiadomoscSOAP(String xml) throws Exception {
        MessageFactory fabrykaWiadomosci = MessageFactory.newInstance();
        SOAPMessage wiadomoscSOAP = fabrykaWiadomosci.createMessage();
        wiadomoscSOAP.getSOAPPart().setContent(new StreamSource(new StringReader(xml)));
        return wiadomoscSOAP;
    }

    public static String przeksztalcNaString(SOAPMessage wiadomoscSOAP) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wiadomoscSOAP.writeTo(out);
        return new String(out.toByteArray());
    }
    public static String pobierzWartoscZapytania(SOAPMessage zapytanie) throws SOAPException {
        SOAPBody cialoSOAP = zapytanie.getSOAPBody();
        return cialoSOAP.getTextContent();
    }

    public static String pobierzOperacjeZapytania(SOAPMessage zapytanie) throws SOAPException {
        SOAPBody cialoSOAP = zapytanie.getSOAPBody();
        return cialoSOAP.getChildNodes().item(0).getLocalName();
    }

    public static SOAPMessage funkcjaNumberToWords(String value) throws Exception {
        MessageFactory fabrykaWiadomosci = MessageFactory.newInstance();
        SOAPMessage wiadomoscSOAP = fabrykaWiadomosci.createMessage();
        SOAPPart czescSOAP = wiadomoscSOAP.getSOAPPart();

        SOAPEnvelope koperta = czescSOAP.getEnvelope();
        koperta.addNamespaceDeclaration("web", "http://www.dataaccess.com/webservicesserver/");
        SOAPHeader naglowekSOAP = koperta.getHeader();
        SOAPBody cialoSOAP = koperta.getBody();

        SOAPElement elementZapytaniaSOAP = cialoSOAP.addChildElement("NumberToWords", "web");
        SOAPElement elementParametruSOAP = elementZapytaniaSOAP.addChildElement("ubiNum", "web");
        elementParametruSOAP.addTextNode(value); // Wartość przekazana z klienta

        wiadomoscSOAP.saveChanges();

        wyswietlWiadomosc(wiadomoscSOAP);

        return wiadomoscSOAP;
    }


    public static SOAPMessage funkcjaNumberToDollars(String value) throws Exception {
        MessageFactory fabrykaWiadomosci = MessageFactory.newInstance();
        SOAPMessage wiadomoscSOAP = fabrykaWiadomosci.createMessage();
        SOAPPart czescSOAP = wiadomoscSOAP.getSOAPPart();

        SOAPEnvelope koperta = czescSOAP.getEnvelope();
        koperta.addNamespaceDeclaration("web", "http://www.dataaccess.com/webservicesserver/");
        SOAPHeader naglowekSOAP = koperta.getHeader();
        SOAPBody cialoSOAP = koperta.getBody();

        SOAPElement elementZapytaniaSOAP = cialoSOAP.addChildElement("NumberToDollars", "web");
        SOAPElement elementParametruSOAP = elementZapytaniaSOAP.addChildElement("dNum", "web");
        elementParametruSOAP.addTextNode(value); // Wartość przekazana z klienta

        wiadomoscSOAP.saveChanges();

        wyswietlWiadomosc(wiadomoscSOAP);

        return wiadomoscSOAP;
    }

    public static void wyswietlOdpowiedz(String operation, SOAPMessage odpowiedzSOAP) throws Exception {
        SOAPBody cialoOdpowiedziSOAP = odpowiedzSOAP.getSOAPBody();
        String zawartoscOdpowiedzi = cialoOdpowiedziSOAP.getTextContent();

        System.out.println("Odpowiedź SOAP dla wartości '123'. Funkcja: (" + operation + "):");
        System.out.println(zawartoscOdpowiedzi.trim());
        System.out.println();
    }

    private static void wyswietlWiadomosc(SOAPMessage soapMessage) throws SOAPException, java.io.IOException {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        soapMessage.writeTo(out);
        String strMsg = new String(out.toByteArray());
        System.out.println(strMsg);
        System.out.println();
    }
}
