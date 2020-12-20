/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillboardControlPanel.billboardXML;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * Read and get information from XML file
 * @author Asus
 */
public class ReadXmlFile {

    private File xmlFile;
    private String backgroundColor;
    private String messageColor;
    private String infoColor;
    private String pictureUrl;
    private String message;
    private String information;

    public String getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


    /**
     * Read the XML file
     * @throws ParserConfigurationException if cannot config documentation
     * @throws IOException if cannot parse File
     * @throws SAXException if cannot parse File
     */
    public void readFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(this.xmlFile);
        doc.getDocumentElement().normalize();

        NodeList billboard = doc.getElementsByTagName("billboard");

        Node nNode = billboard.item(0);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            String picture = "", msColor = "#000000",infoColor="#000000", information = "", bgColor = "#FFFFFF", message = "";

            Node pictureElement = eElement.getElementsByTagName("picture").item(0);
            Node informationElement = eElement.getElementsByTagName("information").item(0);
            Node bgColorElement = doc.getElementsByTagName("billboard").item(0).getAttributes().item(0);
            Node messageElement = eElement.getElementsByTagName("message").item(0);

            if (pictureElement != null) {
                picture = pictureElement.getAttributes().item(0).getTextContent();

            }

            if (informationElement != null) {

                Node infoColorElement = eElement.getElementsByTagName("information").item(0).getAttributes().item(0);
                if (infoColorElement != null) {
                    infoColor = infoColorElement.getTextContent();
                }
                information = informationElement.getTextContent();
            }
            if (bgColorElement != null) {
                bgColor = bgColorElement.getTextContent();
            }

            if (messageElement != null) {
                Node msColorElement = eElement.getElementsByTagName("message").item(0).getAttributes().item(0);
                if (msColorElement != null) {
                    msColor = msColorElement.getTextContent();
                }
                message = messageElement.getTextContent();
            }

            setBackgroundColor(bgColor);
            setMessageColor(msColor);
            setMessage(message);
            setPictureUrl(picture);
            setInformation(information);
            setInfoColor(infoColor);


        }

    }
}
