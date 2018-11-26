/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.RSSF.sensor.main;

/**
 *
 * @author Antonio Raian
 */
public class Sensor {
    private String IP;
    private String porta;

    public Sensor(String IP, String porta) {
        this.IP = IP;
        this.porta = porta;
    }

    public Sensor() {
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
}
