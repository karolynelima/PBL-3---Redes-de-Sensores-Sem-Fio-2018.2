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
    private LinkedList<Sensor> sensoresFilhos;
    private Sensor sensor;

    public Controller() {
        sensoresFilhos = new LinkedList<>();
        sensor = new Sensor();
    }
    
//    public void addPai(String ip, String porta){
//        sensorPai = new Sensor(ip, porta);
//    }
    
    public String addFilho(String ip, String porta, String id){
        Sensor filho = new Sensor(ip, porta);
        filho.setIdSensor(id);
        if(!sensoresFilhos.contains(filho))
            sensoresFilhos.add(filho);
        else
            return "Filho já existe!";
        return "Adicionado com Sucesso!";
    }
    
    public String getIdPai(){
        return sensoresFilhos.getFirst().getIdSensor();
    }
    
    public String getIpPai(){
        return sensoresFilhos.getFirst().getIP();
    }
    
    public String getPortaPai(){
        return sensoresFilhos.getFirst().getPorta();
    }
    
    public String[] listarSensores(){
        String s[] = new String[sensoresFilhos.size()];
        int i=0;
        for(Sensor sen: sensoresFilhos){
            s[i] = sen.getIdSensor()+"-"+sen.getIP()+":"+sen.getPorta();
            i++;
        }
        return s;
    }

    public void setUmidade(String umidade) {
        sensor.setHumidade(umidade);
    }

    public void setVento(String vento) {
        sensor.setVlcVento(vento);
    }

    public void setTemperatura(String temp) {
        sensor.setTemp(temp);
    }

    public String getDados() {
        return sensor.getHumidade()+"#"+sensor.getTemp()+"#"+sensor.getVlcVento();
    }

    public void setAcesso(boolean b) {
        sensor.setAcesso(b);
    }
    
}
