package br.com.RSSF.server.model;

import java.util.LinkedList;

/**
 *
 * @author Karolyne
 */

public class Sensor {
    private LinkedList<Sensor> filhos;
    private String ip, porta;
        
    private String idSensor;
    private String humidade;
    private String vlcVento;
    private String temp;
    private Boolean acesso;//Variável 

    public Sensor(String ip, String porta, String id) {
        this.ip = ip;
        this.porta = porta;
        this.idSensor = id;        
        filhos = new LinkedList<>();
    }

    public Sensor(String idSensor, String humidade, String vlcVento, String temp, Boolean acesso) {
        this.idSensor = idSensor;
        this.humidade = humidade;
        this.vlcVento = vlcVento;
        this.temp = temp;
        this.acesso = acesso;
        filhos = new LinkedList<>();
    }   
    
    public Boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(Boolean acesso) {
        this.acesso = acesso;
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

    public LinkedList<Sensor> getFilhos() {
        return filhos;
    }

    public void addFilhos(Sensor filho) {
        this.filhos.add(filho);
    }

    public String getIp() {
        return ip;
    }

    public String getPorta() {
        return porta;
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
}
