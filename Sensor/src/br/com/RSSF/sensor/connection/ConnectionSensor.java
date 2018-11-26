/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.connection;

import br.com.RSSF.sensor.controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

/**
 *
 * @author kkaro
 */
public class ConnectionSensor {

    private Socket socket;
    private Controller control;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String adressPai;
    private int portaPai;
    private final String adress = Inet4Address.getByName("localhost").getHostAddress();
    
    public ConnectionSensor(String adress, String port) throws IOException{
        this.adressPai = adress;
        this.portaPai = Integer.parseInt(port);
        socket = new Socket(adress, portaPai);        
    }

    public ConnectionSensor(String adress, String port, Controller control) throws IOException {
        this.adressPai = adress;
        this.portaPai = Integer.parseInt(port);
        socket = new Socket(adress, portaPai);
        this.control = control;
    }
    
    public void addNo(String ipno, String porta, String caminho) throws IOException, ClassNotFoundException{
        out = new ObjectOutputStream(socket.getOutputStream()); // CRIAMOS OUTPUTSTREAM USANDO O MÉTODO DO SOCKET PARA ENVIAR DADOS
        out.writeObject("NOVO%"+ipno+"$"+porta+"$"+caminho); //ESCREVEMOS OS DADOS NO OUTPUTSTREAM (ISSO BASTA PARA TRANSMITIR)        
        in = new ObjectInputStream(socket.getInputStream());
        if(((String)in.readObject()).equals("CONECTADO")){
            control.addPai(adressPai, ""+portaPai);
        }
    }
}
    

