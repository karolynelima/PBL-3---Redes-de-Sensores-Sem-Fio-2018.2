/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.server.controller;

import br.com.RSSF.server.model.Sensor;
import java.util.LinkedList;

/**
 *
 * @author kkaro
 */
public class Controller {
    private LinkedList<Sensor> sensores;
    private Sensor ponte;

    public void addNo(String IP, String portaNo, String caminho) {
        Sensor filho = new Sensor(IP, portaNo);
        if(caminho.equals("")){
            ponte = filho;
            filho.setAcesso(true);
        }else{
            LinkedList<String> list = new LinkedList<>();
            String aux[] = caminho.split("#");
            for(String a: aux){
                Sensor s = findSensor(a);
                if(s!=null)
                    if(s.getIp().equals(a))
                        list.add(a);
                    else
                        break;
                else
                    break;
            }
            filho.setCaminho(list);
        }        
        sensores.add(filho);
    }
    
    public Sensor findSensor(String sensor){
        for(Sensor s: sensores){
            if(s.getIp().equals(sensor))
                return s;
        }
        return null;
    }
}
