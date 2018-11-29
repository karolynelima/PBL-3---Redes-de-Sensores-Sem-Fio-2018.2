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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kkaro
 */
public class AtividadeSensor extends Thread{
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
    private String IP;
    private ConnectionSensor connec;
    private String ipGrupo = "230.0.0.0";
    private int ptgrupo; //variáveis para o grupo multicast
    
    public AtividadeSensor(Socket cliente, Controller control, ConnectionSensor connect, int ptgrupo) throws IOException, ClassNotFoundException   {
        clienteTCP = cliente;//Recebe a conexão          
        this.control = control;//Seta o objeto que contém as informações do sistema        
        entradaTCP = new ObjectInputStream(clienteTCP.getInputStream());//Decifra as informações vindas do cliente
        System.out.println("Cliente TCP: "+clienteTCP.getInetAddress()+":"+clienteTCP.getPort());
        mensagem = (String) entradaTCP.readObject();  
        System.out.println(mensagem);
        this.connec = connect;
        this.ptgrupo = ptgrupo;
        
        IP = InetAddress.getLocalHost().getHostAddress();        
    }

    AtividadeSensor(DatagramSocket client, DatagramPacket p, Controller control) throws UnknownHostException {
        clienteUDP = client;
        this.control = control;
        this.entradaUDP = p;
        System.out.println("Conectou");
        
        System.out.println("Cliente UDP: "+entradaUDP.getAddress()+":"+entradaUDP.getPort());
        mensagem = new String(entradaUDP.getData(),0,entradaUDP.getLength());//Transforma o objeto passado em String
        System.out.println("Recebido: "+mensagem);
        
        IP = InetAddress.getLocalHost().getHostAddress();        
    }
    
 @Override
    public void run(){
        String array[] = mensagem.split("%");
        try{
            switch(array[0]){
                case "NOVO":
                    novoSensor(array[1], array[2], array[3], array[4], array[5]);
                break;
                case "DADOS":
                    updateNo(array[1], array[2]);
                break;
            }        
        } catch (IOException | ClassNotFoundException ex) {
         Logger.getLogger(AtividadeSensor.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    private void novoSensor(String ip, String porta,String id, String pai, String salto) throws UnknownHostException, IOException, ClassNotFoundException {        
        control.setAcesso(true); //Como ele está respondendo requisição ele já é um ponto de acesso
               
        int i = Integer.parseInt(salto);//Verifica se já foi adicionado por alguém
        if(i==1){                        
            control.addAdjacente(ip, porta, id);
            i--;
        }
        String resp = connec.addNo(ip, porta, id, pai,i);
        
        saidaTCP = new ObjectOutputStream(clienteTCP.getOutputStream());
        if(resp.contains("#") || resp.equals("CONECTADO e ADICIONADO!!"))
            saidaTCP.writeObject(control.getIdPai()+"#"+control.getIpPai()+"#"+control.getPortaPai()+"#"+ipGrupo+"#"+ptgrupo);
        else
            saidaTCP.writeObject("Não foi possível adicionar");
        saidaTCP.close();
    }

    private void updateNo(String id, String dados) throws IOException, ClassNotFoundException {
        String resp = connec.sendData(id, dados);
        saidaTCP = new ObjectOutputStream(clienteTCP.getOutputStream());
        saidaTCP.writeObject(resp);
        saidaTCP.close();
    }
}