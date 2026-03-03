/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.servidor_texto;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Servidor_Texto {

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(5000);
        Socket socket = server.accept();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        int N = 5;

        long startTime = System.nanoTime();

        for (int i = 0; i < N; i++) {
            Producto p = new Producto(
                    "ABC123",
                    "Laptop Gamer",
                    25000.99,
                    3,
                    "Dell",
                    LocalDate.now()
            );

            String data = p.toTextFormat();
            writer.write(data);
            writer.newLine();
            writer.flush();

            System.out.println("Bytes enviados: " + data.getBytes(StandardCharsets.UTF_8).length);
        }

        long endTime = System.nanoTime();
        System.out.println("Tiempo envio: " + (endTime - startTime) + " ns");

        socket.close();
        server.close();
    }
}
