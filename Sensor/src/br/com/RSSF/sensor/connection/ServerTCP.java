/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.connection;

import br.com.RSSF.sensor.controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author kkaro
 */
public class ServerTCP implements Runnable{
    private final ServerSocket server;
    private final Controller control;
    private final ConnectionSensor conection;

    public ServerTCP(int porta, Controller control, ConnectionSensor connec) throws IOException {
        //Informa a porta que o servidor vai tá "ouvindo"
        server = new ServerSocket(porta);//Abre a conexão para uma determinada porta
        System.out.println("TCP: Ouvindo a porta "+porta);
        this.control = control;
        new Thread(this).start();//Criando e iniciando uma thread principal
        this.conection = connec;
    }

    @Override
    public void run() {
        try{
            while(!server.isClosed()){//Laço de repetição para a criação de várias threads a medida que receber novas conexões
                // aceitando a conexão com o cliente e inicializando uma thread
                //Ao receber uma conexão, cria-se uma thread do tipo AtividadeServidor que irá tratar as informações recebidas
                System.out.println("esperando");
                new AtividadeSensor(server.accept(),control, conection).start();
                System.out.println("Mais um cliente TCP atendido!");
            }

            System.out.println("Saiu do laço?");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
}