/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.servidor_protobuf;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.mycompany.servidor_protobuf.ProductoProto;
/**
 *
 * @author PC
 */
public class Servidor_Protobuf {

    public static void main(String[] args) {
        
         try {
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("Servidor esperando conexion...");

            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            ProductoProto.Producto p1 = ProductoProto.Producto.newBuilder()
                    .setClave("A1")
                    .setNombre("Laptop")
                    .setPrecio(25000.99)
                    .setCantidad(3)
                    .setMarca("Dell")
                    .setFechaReg("23/02/2026")
                    .build();
 
            ProductoProto.Producto p2 = ProductoProto.Producto.newBuilder()
                    .setClave("B2")
                    .setNombre("Mouse")
                    .setPrecio(350.50)
                    .setCantidad(10)
                    .setMarca("Logitech")
                    .setFechaReg("23/02/2026")
                    .build();

            ProductoProto.ListaProductos lista =
                    ProductoProto.ListaProductos.newBuilder()
                            .addProductos(p1)
                            .addProductos(p2)
                            .build();

            OutputStream output = socket.getOutputStream();
            lista.writeTo(output);

            System.out.println("Lista enviada.");
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
