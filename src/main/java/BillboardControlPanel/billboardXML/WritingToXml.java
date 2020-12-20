/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillboardControlPanel.billboardXML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 * Create a XML file
 */
public class WritingToXml {
    private String fileName;  
    private String backgroundColor; 
    private String messageColor;
    private String infoColor;
    private String pictureUrl;
    private String message;
    private String information;
    private String data;

    public String getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
     * Generate XML file
     */
    public void generateXml() {
        try {
            String xmlFilePath = "src/main/java/BillboardControlPanel/billboardXML/xmlFile/" + this.fileName;
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("billboard");
            document.appendChild(root);                        
            root.setAttribute("background", this.backgroundColor);

            // message element
            if (!this.message.equals("")){
                Element message = document.createElement("message");
                root.appendChild(message);
                message.setAttribute("colour", this.messageColor);
                message.appendChild(document.createTextNode(this.message));
            }

            // picture element
            if (!this.pictureUrl.equals("")){
                Element picture = document.createElement("picture");
                root.appendChild(picture);
                picture.setAttribute("url", this.pictureUrl);
            }
            
            			
            if (!this.information.equals("")){
                // information element
                Element information = document.createElement("information");
                root.appendChild(information);
                information.setAttribute("colour", this.infoColor);
                information.appendChild(document.createTextNode(this.information));
            }

            if (!this.data.equals("")){
                // information element
                Element picture = document.createElement("picture");
                root.appendChild(picture);
                picture.setAttribute("data", this.data);
            }
            






            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

            } catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
            } catch (TransformerException tfe) {
                    tfe.printStackTrace();
            }
    }

    private static boolean hasElement(NodeList nlList){
        Node nValue = (Node) nlList.item(0);
        if(nValue == null) {
            return false;
        }
        return true;
    }


}