/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.controller;

import br.com.RSSF.sensor.main.Sensor;
import java.util.LinkedList;

/**
 *
 * @author kkaro
 */
public class Controller {
    private LinkedList<Sensor> sensoresFilhos;
    //private Sensor sensorPai;

    public Controller() {
        sensoresFilhos = new LinkedList<>();
    }
    
//    public void addPai(String ip, String porta){
//        sensorPai = new Sensor(ip, porta);
//    }
    
    public String addFilho(String ip, String porta){
        Sensor filho = new Sensor(ip, porta);
        
        if(!sensoresFilhos.contains(filho))
            sensoresFilhos.add(filho);
        else
            return "Filho já existe!";
        return "Adicionado com Sucesso!";
    }
    
    
    public String[] listarSensores(){
        String s[] = new String[sensoresFilhos.size()];
        sensoresFilhos.toArray(s);
        return s;
    }
    
}
