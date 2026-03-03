/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cliente_texto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author zarex
 */
public class Cliente_Texto {

    public static void main(String[] args) throws Exception{
       
        Socket socket = new Socket("localhost", 5000);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        String line;

        while ((line = reader.readLine()) != null) {

            long start = System.nanoTime();

            System.out.println("Datos crudos: " + line);

            Producto p = Producto.fromText(line);
            System.out.println("Objeto reconstruido: " + p);

            long end = System.nanoTime();

            System.out.println("Bytes recibidos: " +
                    line.getBytes(StandardCharsets.UTF_8).length);
            System.out.println("Tiempo procesamiento: " +
                    (end - start) + " ns\n");
        }

        socket.close();
    }
    }

