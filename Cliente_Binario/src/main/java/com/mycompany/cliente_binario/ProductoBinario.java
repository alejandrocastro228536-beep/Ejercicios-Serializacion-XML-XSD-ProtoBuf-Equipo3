package com.mycompany.cliente_binario;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ProductoBinario {

    public static final int SIZE = 6 + 30 + 8 + 4 + 30 + 8;

    public static byte[] toBytes(Producto p) {

        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        buffer.order(ByteOrder.BIG_ENDIAN);

        buffer.put(fixedLength(p.getClave(), 6));
        buffer.put(fixedLength(p.getNombre(), 30));
        buffer.putDouble(p.getPrecio());
        buffer.putInt(p.getCantidad());
        buffer.put(fixedLength(p.getMarca(), 30));

        long epoch = p.getFechaReg()
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();

        buffer.putLong(epoch);

        return buffer.array();
    }

    public static Producto fromBytes(byte[] data) {

        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.BIG_ENDIAN);

        byte[] claveBytes = new byte[6];
        buffer.get(claveBytes);
        String clave = new String(claveBytes, StandardCharsets.UTF_8).trim();

        byte[] nombreBytes = new byte[30];
        buffer.get(nombreBytes);
        String nombre = new String(nombreBytes, StandardCharsets.UTF_8).trim();

        double precio = buffer.getDouble();
        int cantidad = buffer.getInt();

        byte[] marcaBytes = new byte[30];
        buffer.get(marcaBytes);
        String marca = new String(marcaBytes, StandardCharsets.UTF_8).trim();

        long epoch = buffer.getLong();

        LocalDate fecha = Instant.ofEpochSecond(epoch)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return new Producto(clave, nombre, precio, cantidad, marca, fecha);
    }

    private static byte[] fixedLength(String s, int length) {
        byte[] bytes = new byte[length];
        byte[] src = s.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(src, 0, bytes, 0, Math.min(src.length, length));
        return bytes;
    }
}