package es.etg.psp.dmc.caballos.logic.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface DataTransferUDP extends PortData{
    
    @SuppressWarnings("ConvertToTryWithResources")
    static String receive(int bufferSize) throws Exception {
        byte[] buffer = new byte[bufferSize];
        DatagramPacket msg = new DatagramPacket(buffer, buffer.length);
    
        DatagramSocket socket = new DatagramSocket(PORT);
        socket.receive(msg);
        String data = new String(msg.getData(), 0, msg.getLength());

        socket.close();
        return data;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    static void send(String msg) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        byte[] data = msg.getBytes();
        InetAddress serverAddress = InetAddress.getByName(LOCALHOST);
        
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, PORT);
        socket.send(packet);

        socket.close();
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    static void send(String msg, InetAddress clientAddress, int clientPort) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        byte[] data = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(data, data.length, clientAddress, clientPort);
        socket.send(packet);
        
        socket.close();
    }
}
