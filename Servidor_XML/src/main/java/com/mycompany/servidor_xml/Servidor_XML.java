/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.servidor_xml;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Servidor_XML {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(7000);
        System.out.println("Servidor XML esperando...");
        Socket socket = server.accept();

        BufferedWriter writer
                = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        int N = 3;

        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.newLine();
        writer.write("<productos>");
        writer.newLine();

        for (int i = 0; i < N; i++) {
            Producto p = new Producto(
                    "ABC123",
                    "Laptop Gamer",
                    25000.99,
                    3,
                    "Dell",
                    LocalDate.now()
            );

            writer.write(p.toXML());
            writer.newLine();
        }

        writer.write("</productos>");
        writer.flush();

        socket.close();
        server.close();
    }
}
