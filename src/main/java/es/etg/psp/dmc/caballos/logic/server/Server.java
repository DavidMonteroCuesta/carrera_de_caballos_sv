package es.etg.psp.dmc.caballos.logic.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import es.etg.psp.dmc.caballos.PortData;
import es.etg.psp.dmc.caballos.logic.server.core.Function;

public class Server implements PortData{
    private static final int MAX = 4;

    public static void main(String[] args) throws Exception{
        Thread function;

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            socket.receive(packet);
            function = new Thread(new Function(packet));
            function.start();
        }
    }


}