package com.example.viikko9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    ArrayList<TheaterInfo> theaterArray;
    Spinner theaterChoice;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theaterChoice = findViewById(R.id.spinnerTheaterList);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = MainActivity.this;
        theaterArray = readXML();
        ArrayAdapter<TheaterInfo> adapter = new ArrayAdapter<TheaterInfo>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,theaterArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theaterChoice.setAdapter(adapter);
    }

    public ArrayList<TheaterInfo> readXML() {
        ArrayList<TheaterInfo> tArray = new ArrayList<TheaterInfo>(20);
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println("Element is: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Teatterin nimi: " + element.getElementsByTagName("Name").item(0).getTextContent());
                    System.out.println("Teatterin ID: " + element.getElementsByTagName("ID").item(0).getTextContent());
                    tArray.add(new TheaterInfo(element.getElementsByTagName("Name").item(0).getTextContent(),element.getElementsByTagName("ID").item(0).getTextContent()));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            System.out.println("#####################WORK DONE#######");
        }
        return tArray;
    }



}