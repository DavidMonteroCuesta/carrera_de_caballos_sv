package es.etg.psp.dmc.caballos.logic.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public interface DataTransferTCP {

    static String receive(Socket cliente) throws IOException {
        DataInputStream input = new DataInputStream(cliente.getInputStream());
        return input.readUTF();
    }

    static void send(Socket cliente, String msg) throws IOException{
        DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
        output.writeUTF(msg);
    }
}
