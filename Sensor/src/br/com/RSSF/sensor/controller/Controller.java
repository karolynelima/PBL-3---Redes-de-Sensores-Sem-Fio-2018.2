/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.controller;

import br.com.RSSF.sensor.model.Sensor;
import java.util.LinkedList;

/**
 *
 * @author kkaro
 */
public class Controller {
    private LinkedList<Sensor> sensoresAdj;
    private Sensor sensor, vo;

    public Controller(String id, String ip, String porta) {
        sensoresAdj = new LinkedList<>();
        sensor = new Sensor(ip, porta);
        sensor.setIdSensor(id);
    }
        
    public String addAdjacente(String ip, String porta, String id){
        Sensor novo = new Sensor(ip, porta);
        novo.setIdSensor(id);
        if(!sensoresAdj.contains(novo))
            sensoresAdj.add(novo);
        else
            return "Nó já adicionado!";
        return "Adicionado com Sucesso!";
    }
    
    public String getIdPai(){
        return sensoresAdj.getFirst().getIdSensor();
    }
    
    public String getIpPai(){
        return sensoresAdj.getFirst().getIP();
    }
    
    public String getPortaPai(){
        return sensoresAdj.getFirst().getPorta();
    }
    
    public String getIdVo(){
        return vo.getIdSensor();
    }
    
    public String getIpVo(){
        return vo.getIP();
    }
    
    public String getPortaVo(){
        return vo.getPorta();
    }
    public String getId(){
        return sensor.getIdSensor();
    }
    
    public void setVo(String id, String ip, String porta){
        vo = new Sensor(ip, porta);
        vo.setIdSensor(id);
    }
    
    public String[] listarSensores(){
        String s[] = new String[sensoresAdj.size()];
        int i=0;
        for(Sensor sen: sensoresAdj){
            s[i] = sen.getIdSensor()+"-"+sen.getIP()+":"+sen.getPorta();
            i++;
        }
        return s;
    }
    
    public void setAcesso(boolean b) {
        sensor.setAcesso(b);
    }
    
    public boolean listaVazia(){
        return sensoresAdj.size()==1;
    }

    public void setPai(String id, String ip, String porta) {
        Sensor novo = new Sensor(ip, porta);
        novo.setIdSensor(id);
        sensoresAdj.removeFirst();
        sensoresAdj.addFirst(novo);
    }
}
