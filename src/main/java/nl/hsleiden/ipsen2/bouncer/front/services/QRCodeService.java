package nl.hsleiden.ipsen2.bouncer.front.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import nl.hsleiden.ipsen2.bouncer.front.views.RequestNewView;


/**
 * @author Stef Haring
 */
public class QRCodeService {

    /**
     * Generating the QR code image based on the given uid
     *
     * @param QRtext the uid that the QR code must contain
     * @return the Image that can be given to a imageView
     */
    public Image generateQRCode(String QRtext){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 450;
        int height = 450;
        BufferedImage bufferedImage = null;

        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(QRtext, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

        } catch (WriterException ex) {
            Logger.getLogger(RequestNewView.class.getName()).log(Level.SEVERE, null, ex);
        }

        Image qrImage = SwingFXUtils.toFXImage(bufferedImage, null);

        return qrImage;
    }


}