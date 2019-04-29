/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.baqshamninonimi.payment.entity;

/**
 *
 * @author assylbek
 */
class Email {
    private String name;
    private String server;
    private String domainExtension;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDomainExtension() {
        return domainExtension;
    }

    public void setDomainExtension(String domainExtension) {
        this.domainExtension = domainExtension;
    }
    
}
