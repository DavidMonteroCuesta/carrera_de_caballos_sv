package es.etg.psp.dmc.caballos.logic.server.core;

import java.net.DatagramPacket;
import java.net.InetAddress;

import es.etg.psp.dmc.caballos.logic.server.util.Condition;
import es.etg.psp.dmc.caballos.logic.util.DataTransferUDP;
import es.etg.psp.dmc.caballos.logic.util.Responses;

public class Function implements DataTransferUDP, Runnable{
    private static final String OK = "OK";

    private final InetAddress clientAddress;
    private final int clientPort;

    public Function(DatagramPacket packet) {
        this.clientAddress = packet.getAddress();
        this.clientPort = packet.getPort();
    }

    @Override
    public void run() {
        Condition condition = Condition.getInstance();
        try {
            Horse horse = new Horse(DataTransferUDP.receive(BUFFER_SIZE));
            DataTransferUDP.send(OK, clientAddress, clientPort);

            do { 
                DataTransferUDP.send(Integer.toString(horse.move()), clientAddress, clientPort);
            } while (horse.getDistance() < 100 && condition.isCondition());

            DataTransferUDP.send((horse.getDistance() > 100) ? Responses.LOSE.getValue() : Responses.WIN.getValue(), clientAddress, clientPort);
        } catch (Exception e) {throw new RuntimeException(e);}
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public int getClientPort() {
        return clientPort;
    }    
}
