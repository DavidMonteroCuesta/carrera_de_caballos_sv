package es.etg.psp.dmc.caballos.logic.server;

import java.net.ServerSocket;
import java.net.Socket;

import es.etg.psp.dmc.caballos.logic.server.core.Function;
import es.etg.psp.dmc.caballos.logic.util.PortData;

public class Server implements PortData{
    private static final int MAX = 4;

    public static void main(String[] args) throws Exception{
        Thread[] functions = new Thread[MAX];

        /**try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            socket.receive(packet);
            function = new Thread(new Function(packet));
            function.start();
        }*/

        try (ServerSocket server = new ServerSocket(PORT)) {
            for (Thread function : functions) {
                Socket cliente = server.accept();
                function = new Thread(new Function(cliente));
                function.start();
            }
        }
    }


}