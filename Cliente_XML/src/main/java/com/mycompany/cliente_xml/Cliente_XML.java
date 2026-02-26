/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cliente_xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 *
 * @author PC
 */
public class Cliente_XML {

    public static void main(String[] args) {

        try {

            System.out.println("Conectando al servidor...");
            Socket socket = new Socket("localhost", 7000);

            BufferedReader reader
                    = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            // Guardar XML recibido
            BufferedWriter writer
                    = new BufferedWriter(
                            new FileWriter("producto.xml"));

            String linea;

            while ((linea = reader.readLine()) != null) {
                writer.write(linea);
                writer.newLine();
            }

            writer.close();
            socket.close();

            System.out.println("XML recibido correctamente.");

            // Validar contra XSD
            validarXML("producto.xml", "producto.xsd");

            System.out.println("XML validado correctamente");

        } catch (Exception e) {
            new File("producto.xml").delete(); //Elimina el archivo si no es valido
            System.out.println("Error durante la ejecucion:");
            e.printStackTrace();
        }
    }

    // Metodo para validar XML con XSD
    public static void validarXML(String xmlPath, String xsdPath) throws Exception {

        SchemaFactory factory
                = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema
                = factory.newSchema(new File(xsdPath));

        Validator validator
                = schema.newValidator();

        validator.validate(
                new StreamSource(new File(xmlPath)));

        System.out.println("Validacion contra XSD exitosa.");
    }
}
