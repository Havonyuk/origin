package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            try {
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setNamespaceAware(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse("foods.xml");

                    Element element = document.getDocumentElement();

                    if ("foods".equals(element.getTagName())) {
                        NodeList foods = element.getElementsByTagName("food"); //дочерние элементы
                        for (int i = 0; i < foods.getLength(); ++i) {
                            Element food = (Element) foods.item(i);
                            String shop = null, name = null, maker = null, group = null, composition = null, price = null;
                            NodeList props = food.getElementsByTagName("*"); // дочерние элементы
                            for (int j = 0; j < props.getLength(); ++j) { //кол-во дочерних узлов
                                Element prop = (Element) props.item(j);
                                if ("shop".equals(prop.getTagName())) { //имя данного элемента
                                    //number = prop.getAttribute("n");
                                    shop = prop.getTextContent();
                                } else if ("name".equals(prop.getTagName())) {
                                    name = prop.getTextContent();
                                } else if ("maker".equals(prop.getTagName())) {
                                    maker = prop.getTextContent();
                                } else if ("group".equals(prop.getTagName())) {
                                    group = prop.getTextContent();
                                } else if ("composition".equals(prop.getTagName())) {
                                    composition = prop.getTextContent();
                                    price = prop.getAttribute("price");
                                }
                            }
                            if (shop != null) {
                                System.out.println("В магазине " + shop);
                            }
                            if (name != null) {
                                System.out.println(" продается " + name);
                            }
                            if (maker != null) {
                                System.out.println(" от производителя " + maker);
                            }
                            if (group != null) {
                                System.out.println(" принадлежащий к группе товаров " + group);
                            }
                            if (composition != null) {
                                System.out.println(" состоящий из " + composition + " по цене " + price);
                            }
                            System.out.println('\n');
                        }
                    }

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SAXException e) {
                e.printStackTrace();
            }
    }
}

