/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.connection;

import br.com.RSSF.controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author kkaro
 */
public class AtividadeServidor {
 private Socket server;
    private Controller control;//INSTANCIAÇÃO DO CONTROLLER
    private Socket clienteTCP;//INSTANCIAÇÃO DO CLIENTE TCP (CAMINHÃO)
    private DatagramSocket clienteUDP;//INSTANCIAÇÃO DO CLIENTE UDP (LIXEIRA)
    private ObjectInputStream entradaTCP;
    private ObjectOutputStream saidaTCP;
    private Socket socket;
    private String mensagem;
    private DatagramPacket entradaUDP;
    private byte[] saidaUDP;
    private MulticastPublisher multEnviar; 
    private MulticastReceiver multReceber;
    private String IP;
    
    public AtividadeServidor(Socket cliente, Controller control) throws IOException, ClassNotFoundException   {
        clienteTCP = cliente;//Recebe a conexão          
        this.control = control;//Seta o objeto que contém as informações do sistema        
        entradaTCP = new ObjectInputStream(clienteTCP.getInputStream());//Decifra as informações vindas do cliente
        System.out.println("Cliente TCP: "+clienteTCP.getInetAddress()+":"+clienteTCP.getPort());
        mensagem = (String) entradaTCP.readObject();  
        System.out.println(mensagem);
        
        IP = InetAddress.getLocalHost().getHostAddress();        
        multEnviar = new MulticastPublisher();
        multReceber = new MulticastReceiver(control);
    }

    AtividadeServidor(DatagramSocket client, DatagramPacket p, Controller control) throws UnknownHostException {
        clienteUDP = client;
        this.control = control;
        this.entradaUDP = p;
        System.out.println("Conectou");
        
        System.out.println("Cliente UDP: "+entradaUDP.getAddress()+":"+entradaUDP.getPort());
        mensagem = new String(entradaUDP.getData(),0,entradaUDP.getLength());//Transforma o objeto passado em String
        System.out.println("Recebido: "+mensagem);
        
        IP = InetAddress.getLocalHost().getHostAddress();        
        multEnviar = new MulticastPublisher();
        multReceber = new MulticastReceiver(control);
    }
    

    
    
}
