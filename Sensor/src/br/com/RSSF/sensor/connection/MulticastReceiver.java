/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.connection;

import br.com.RSSF.sensor.controller.Controller;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karolyne
 */

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    private String IP;
    private ObjectOutputStream out;
    private Controller controller;
    private String ipGrupo;
    private int portaGrupo;

    public MulticastReceiver(Controller control, String ip, String porta) throws UnknownHostException{
        controller = control;
        IP = InetAddress.getLocalHost().getHostAddress();
        ipGrupo = ip;
        portaGrupo = Integer.parseInt(porta);
    }
    
    @Override
    public void run() {
        try {
            socket = new MulticastSocket(portaGrupo);
            InetAddress group = InetAddress.getByName(ipGrupo);
            socket.joinGroup(group);
            //socket.setSoTimeout(10000);
            while (true) {
                System.out.println("entrou no while");
                
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);                
                String received = new String(packet.getData(), 0, packet.getLength());
                trataMens(received);
                
                System.out.println(received);
                if ("end".equals(received)) {
                    break;
                }
            }
            socket.leaveGroup(group);
            socket.close();
        }catch (IOException ex) {
            Logger.getLogger(MulticastReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public byte[] buffer(){
        return buf;
    }
    private void trataMens(String mensagem){
        String aux[] = mensagem.split("#");
        switch (aux[0]){
            case "NOVO PAI":
                if(!aux[1].equals(controller.getId()))
                    controller.setPai(aux[1], aux[2], aux[3]);
                else
                    controller.setPai(controller.getIdVo(), controller.getIpVo(), controller.getPortaVo());
            break;
        }
    }
}

