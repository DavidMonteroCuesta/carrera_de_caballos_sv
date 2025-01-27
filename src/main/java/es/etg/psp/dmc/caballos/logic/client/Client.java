package es.etg.psp.dmc.caballos.logic.client;

import es.etg.psp.dmc.caballos.logic.util.DataTransferUDP;
import es.etg.psp.dmc.caballos.logic.util.Responses;

public class Client implements DataTransferUDP{

    private static final String HORSE_NAME = "JUAN";

    public static void main(String[] args) throws Exception {
        String response;
    
        DataTransferUDP.send(HORSE_NAME);
    
        do {
            response = DataTransferUDP.receive(BUFFER_SIZE);
            System.out.println(response);
        } while (!(response.equals(Responses.LOSE.getValue()) || response.equals(Responses.WIN.getValue())));
    }
}
