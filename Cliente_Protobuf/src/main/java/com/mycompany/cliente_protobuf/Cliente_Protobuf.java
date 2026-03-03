/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cliente_protobuf;

import java.io.InputStream;
import java.net.Socket;
import com.mycompany.cliente_protobuf.ProductoProto;
/**
 *
 * @author PC
 */
public class Cliente_Protobuf {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 7000);
            InputStream input = socket.getInputStream();

            ProductoProto.ListaProductos lista =
                    ProductoProto.ListaProductos.parseFrom(input);

            for (ProductoProto.Producto p : lista.getProductosList()) {
                System.out.println("Producto recibido:");
                System.out.println(p.getClave() + " | "
                        + p.getNombre() + " | "
                        + p.getPrecio() + " | "
                        + p.getCantidad() + " | "
                        + p.getMarca() + " | "
                        + p.getFechaReg());
                System.out.println("---------------------------");
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
