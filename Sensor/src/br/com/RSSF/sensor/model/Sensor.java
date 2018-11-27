/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.model;

/**
 *
 * @author Antonio Raian
 */
public class Sensor {
    private String IP;
    private String porta;
    
    private String idSensor;
    private String humidade;
    private String vlcVento;
    private String temp;
    private boolean acesso;

    public Sensor() {
        acesso = false;
    }

    public Sensor(String IP, String porta) {
        this.IP = IP;
        this.porta = porta;
        acesso = false;
    }

    public Sensor(String idSensor, String humidade, String vlcVento, String temp) {
        this.idSensor = idSensor;
        this.humidade = humidade;
        this.vlcVento = vlcVento;
        this.temp = temp;
        acesso = false;
    }  

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }  
    
    public String getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(String idSensor) {
        this.idSensor = idSensor;
    }

    public String getHumidade() {
        return humidade;
    }

    public void setHumidade(String humidade) {
        this.humidade = humidade;
    }

    public String getVlcVento() {
        return vlcVento;
    }

    public void setVlcVento(String vlcVento) {
        this.vlcVento = vlcVento;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public boolean isAcesso() {
        return acesso;
    }

    public void setAcesso(boolean acesso) {
        this.acesso = acesso;
    }
    
}
