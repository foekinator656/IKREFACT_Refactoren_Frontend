package nl.hsleiden.ipsen2.bouncer.front.models;

import java.util.Date;

public class QRCode {
    private long id;
    private String code;
    private Request request;
    private Date validTill;

    private QRCode(String code,Request request, Date validTill){
        this.code = code;
        this.request = request;
        this.validTill = validTill;
    }
}
