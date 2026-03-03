/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.servidor_binario;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Servidor_Binario {

    public static void main(String[] args) throws Exception{
       
        ServerSocket server = new ServerSocket(6000);
        System.out.println("Servidor Binario esperando conexion...");
        Socket socket = server.accept();

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        int N = 3;

        for (int i = 0; i < N; i++) {

            Producto p = new Producto(
                    "ABC123",
                    "Laptop Gamer",
                    25000.99,
                    3,
                    "Dell",
                    LocalDate.now()
            );

            byte[] data = ProductoBinario.toBytes(p);

            long start = System.nanoTime();

            out.write(data);
            out.flush();

            long end = System.nanoTime();

            System.out.println("Bytes enviados: " + data.length);
            System.out.println("Tiempo envio: " + (end - start) + " ns\n");
        }

        socket.close();
        server.close();
    }
}
