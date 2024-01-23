package com.example.giftcardcrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
/**
 *
 * @author user1
 */
public class GiftCardCrawler {

     public static void main(String[] args) {
        // URLs of the gift card certificates
        String url1 = "https://distribution.giftlov.com/api/Orders/95ccc5cc-dec2-480f-a0b9-37233c064133/49f5520479158e085a8d10000cc7740a0834bbde49493a0493415cae2a57ad6a/o/15642927?r=5.611925132178843";
        String url2 = "https://distribution.giftlov.com/api/Orders/78a01bfa-b803-4ccd-8f99-5de87bfbc8a7/669f317a91995af3a8fb6d5cf6cec4066033bc8ca4e305d19b5d1229c461a3f9/o/13188160?&token=03AFcWeA4TFcXYi2ZrYRR_NWS8z-3cG89F9JawC9Vwbdco5W_bSIMdnw0zkUOEa51fIwMQWusMtUMss2EXJdHb40__6_RyZ01k6Oh-wZ4B67p_kYzpZ-Q1-MholCRr5eT5uS1oxKq8nyKOH8tTAEjIKuIheGPizZBR766TNt0PvnH8etQq9N-C8XAYT4iJrBVr16ecjcLraxLvtLupvrvG2-cwpe97FV4XiGQKM3vbLN1MGIAsoWLyXcLwkJmQvQFuk6ouhpXvUtfCVDZLTTk05IS4oHTxW9TtAiueQ6xPXObZd5aetWD1W0cWG6HDWY65teWDENKTDj2CahvvGTOKpccIjb_ci7lRANkUYeDMGIEdsgs5iiect514qnXBDZ_38f_44WGY4qfxbFSIqt7SDF3A6J7UgFMcklmYUHctTr65wfxon4eAwyAm8Ikz_HiaJuQOcsfxfAXNO9re2tgfdcYVPRObNkL4Dsr6qyofuwO56h525i9EkzJMF8Sr5MpPDlfD2ZlhmXbuMQ-qf55gDIByYhN1SAXG9kKb5ELi1v_mLb1GsGxE5boAYDvN5CdfkcE8U7c7gYt5";

        // Extract and print claim codes
        String claimCode1 = extractClaimCode(url1);
        String claimCode2 = extractClaimCode(url2);

        // Display results on a simple web page
        displayResults(claimCode1, claimCode2);
    }

    private static String extractClaimCode(String url) {
        try {
            // Connect to the URL and get the HTML document
            Document document = Jsoup.connect(url).get();

            // Extract claim code element
            Element claimCodeElement = document.select("#card-number").first();

            // Extract and return the claim code
            return (claimCodeElement != null) ? claimCodeElement.text() : "Claim code not found";

        } catch (IOException e) {
            //e.printStackTrace();
            return "Error extracting claim code";
        }
    }

    private static void displayResults(String claimCode1, String claimCode2) {
        // Simple HTML display
        String html = "<html><body>" +
                      "<h2>Gift Card 1 Claim Code: " + claimCode1 + "</h2>" +
                      "<h2>Gift Card 2 Claim Code: " + claimCode2 + "</h2>" +
                      "</body></html>";

        // Open the results in the default browser
        openInBrowser(html);
    }

    private static void openInBrowser(String html) {
        try {
            // Create a temporary HTML file
            java.nio.file.Path path = java.nio.file.Files.createTempFile("web-crawler-results", ".html");
            java.nio.file.Files.write(path, html.getBytes());

            // Open the HTML file in the default web browser
            java.awt.Desktop.getDesktop().browse(path.toUri());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
