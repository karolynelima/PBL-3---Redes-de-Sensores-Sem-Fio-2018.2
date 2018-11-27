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

    public Controller() {
        sensores = new LinkedList<>();
    }

    public void addNo(String IP, String portaNo, String id, String pai) {
        Sensor filho = new Sensor(IP, portaNo, id);
        Sensor dad = findSensor(pai);
        
        if(pai.equals("") || pai == null)
            filho.setAcesso(true);
        else if(dad!=null){
            dad.addFilhos(filho);
            dad.setAcesso(true);
        }
        sensores.add(filho);
    }
    
    public Sensor findSensor(String sensor){
        for(Sensor s: sensores){
            if(s.getIdSensor().equals(sensor))
                return s;
        }
        return null;
    }
    
    public String getIP(String id){
        return findSensor(id).getIp();
    }
    
    public String getPorta(String id){
        return findSensor(id).getPorta();
    }

    public String[] listarSensores() {
        String s[] = new String[sensores.size()];
        int i=0;
        for(Sensor sen: sensores){
            s[i] = sen.getIdSensor()+"->";
            for(Sensor sensor:sen.getFilhos()){
                s[i] +=","+sensor.getIdSensor();
            }
            i++;
        }
        return s;
    }
    
    
}
