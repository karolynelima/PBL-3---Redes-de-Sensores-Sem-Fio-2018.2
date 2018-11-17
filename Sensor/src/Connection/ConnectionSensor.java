/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author kkaro
 */
public class ConnectionSensor {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String adress;
    private int porta;
    
    public ConnectionSensor(String adress, String port) throws IOException{
        this.adress = adress;
        this.porta = Integer.parseInt(port);
        socket = new Socket(adress, porta);        
    }
    
    public String conecta() throws IOException, ClassNotFoundException {
        out = new ObjectOutputStream(socket.getOutputStream()); // CRIAMOS OUTPUTSTREAM USANDO O MÉTODO DO SOCKET PARA ENVIAR DADOS
        out.writeObject("S%CONECTA%"); //ESCREVEMOS OS DADOS NO OUTPUTSTREAM (ISSO BASTA PARA TRANSMITIR)        
        in = new ObjectInputStream(socket.getInputStream());
        
         return (String)in.readObject();        
    }
    
    
    
    
}
    

