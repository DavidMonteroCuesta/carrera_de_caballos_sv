package es.etg.psp.dmc.caballos.logic.client;

import java.net.Socket;

import es.etg.psp.dmc.caballos.logic.util.DataTransferTCP;
import es.etg.psp.dmc.caballos.logic.util.PortData;
import es.etg.psp.dmc.caballos.logic.util.Responses;

public class Client implements DataTransferTCP, PortData{

    private static final String HORSE_NAME = "JUAN";

    public static void main(String[] args) throws Exception {
        /**String response;
    
        DataTransferUDP.send(HORSE_NAME);
    
        do {
            response = DataTransferUDP.receive(BUFFER_SIZE);
            System.out.println(response);
        } while (!(response.equals(Responses.LOSE.getValue()) || response.equals(Responses.WIN.getValue())));*/

        try (Socket cliente = new Socket(LOCALHOST, PORT)) {
            String response;

            DataTransferTCP.send(cliente, HORSE_NAME);

            do {
                response = DataTransferTCP.receive(cliente);
                System.out.println(response);
            } while (!(response.equals(Responses.LOSE.getValue()) || response.equals(Responses.WIN.getValue())));
        }
    }
}
