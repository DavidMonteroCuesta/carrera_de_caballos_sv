package es.etg.psp.dmc.caballos.logic.server.core;

import java.io.IOException;
import java.net.Socket;

import es.etg.psp.dmc.caballos.logic.server.util.Condition;
import es.etg.psp.dmc.caballos.logic.util.DataTransferTCP;
import es.etg.psp.dmc.caballos.logic.util.Responses;


public class Function implements DataTransferTCP, Runnable{
    private static final String OK = "OK";
    private final Condition condition = Condition.getInstance();
    private final Socket client;

    public Function(Socket client) {
        this.client = client;
    }
    
    @Override
    public void run() {
        try {
            Horse horse = new Horse(DataTransferTCP.receive(client));
            DataTransferTCP.send(client, OK);

            condition.count();

            do { 
                DataTransferTCP.send(client, Integer.toString(horse.move()));
            } while (horse.getDistance() < 100 && condition.isCondition());

            condition.setCondition();
            DataTransferTCP.send(client, (horse.getDistance() >= 100) ? Responses.WIN.getValue() : Responses.LOSE.getValue());

        } catch (Exception e) {throw new RuntimeException(e);
        } finally { close();}
    }

    private void close (){
        try { this.client.close();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public Socket getCliente() {
        return client;
    }
}

    

    /**private final InetAddress clientAddress;
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
    }*/ 
