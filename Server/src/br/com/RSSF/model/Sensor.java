package br.com.RSSF.model;

/**
 *
 * @author Karolyne
 */

public class Sensor {
    private String idSensor;
    private String humidade;
    private String vlcVento;
    private String temp;
    private Boolean acesso;//Variável 

    public Sensor(String idSensor, String humidade, String vlcVento, String temp, Boolean acesso) {
        this.idSensor = idSensor;
        this.humidade = humidade;
        this.vlcVento = vlcVento;
        this.temp = temp;
        this.acesso = acesso;
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
