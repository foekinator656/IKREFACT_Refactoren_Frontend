package nl.hsleiden.ipsen2.bouncer.front.response;

import java.io.Serializable;

public class QrCodeResponse implements Serializable {
    private String qrCode;

    public QrCodeResponse () {}

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
