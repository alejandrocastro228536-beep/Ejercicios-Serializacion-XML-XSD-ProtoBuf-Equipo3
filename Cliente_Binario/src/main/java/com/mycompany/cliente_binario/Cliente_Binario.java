package com.mycompany.cliente_binario;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Cliente_Binario {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 6000);
        DataInputStream in = new DataInputStream(socket.getInputStream());

        byte[] buffer = new byte[ProductoBinario.SIZE];

        while (in.read(buffer) == ProductoBinario.SIZE) {

            long start = System.nanoTime();

            Producto p = ProductoBinario.fromBytes(buffer);
            
            System.out.println("Bytes crudos:");
            System.out.println(Arrays.toString(buffer));
            System.out.println("Binario en HEX:");
            System.out.println(bytesToHex(buffer));
            System.out.println("Producto recibido: " + p);
            System.out.println("Bytes recibidos: " + buffer.length);

            long end = System.nanoTime();
            System.out.println("Tiempo procesamiento: " +
                    (end - start) + " ns\n");
        }

        socket.close();
    }
    
    public static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
        sb.append(String.format("%02X ", b));
    }
    return sb.toString();
}
    
}