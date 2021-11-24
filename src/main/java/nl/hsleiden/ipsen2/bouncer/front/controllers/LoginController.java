package nl.hsleiden.ipsen2.bouncer.front.controllers;

import nl.hsleiden.ipsen2.bouncer.front.application.ViewManager;
import nl.hsleiden.ipsen2.bouncer.front.models.Role;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;
import nl.hsleiden.ipsen2.bouncer.front.repository.UserRepository;

public class LoginController {
    private final UserRepository userRepository;

    public LoginController(Observer observer) {
        this.userRepository = new UserRepository();
        userRepository.register(observer);
    }

    public void userLogin(String username, String password) {
         userRepository.apiLoginResponse(username, password);
    }

    public void redirectUser(Role role) {
        switch (role) {
            default:
                ViewManager.addScene("ModeratorDashboard", "Moderator Index");
                ViewManager.setShowingScene("Moderator Index");
        }
    }

    /**
     * @deprecated Moved to the LoginView.java and started using regex
     */
    public boolean eMail_OK(String eMailAdress) {
        int firstIndex = eMailAdress.indexOf("@");
        int lastIndex = eMailAdress.lastIndexOf("@");
        if (firstIndex != lastIndex) {
            return false;
        } else {
            String[] fieldsMail = eMailAdress.split("@");
            String userInDomain = fieldsMail[0];
            String domainName = fieldsMail[1];

            boolean userInDomainOK = userInDomain.length() > 0;
            firstIndex = domainName.indexOf(".");
            lastIndex = domainName.lastIndexOf(".");
            int lastPosOfDomainName = domainName.length() - 1;
            boolean domainNameOK = (firstIndex > 0) && (lastIndex < lastPosOfDomainName);
            if (!userInDomainOK || !domainNameOK) {
                return false;
            }
            return true;
        }
    }

    /**
     * UUID Check
     * https://www.javatpoint.com/java-uuid
     * UUID voorbeeld: 237e9877-e79b-12d4-a765-321741963000
     *
     * time_low = low 32-bits of the time
     * time_mid = middle 16-bits of the time
     * time_hi_and_version = 4-bit version in the MSB, followed by the high 12-bits of the time
     * clock_seq_hi_and_res = 1-3 bit variant in the most significant bits followed by the 13-15 bit clock sequence
     * node = 48-bit node ID
     *
     * @deprecated Moved to LoginView.java and used a different method
     */
    public boolean UUID_OK(String UUIDLogin){
        int userNameLength = UUIDLogin.length();
        String[] fieldsUUID = UUIDLogin.split("-");
        String time_low = fieldsUUID[0];
        String time_mid = fieldsUUID[1];
        String time_hi_and_version = fieldsUUID[2];
        String clock_seq_hi_and_res = fieldsUUID[3];
        String node = fieldsUUID[4];

        if (userNameLength == 36 && time_low.length() == 8 && time_mid.length() == 4 && time_hi_and_version.length() == 4 && clock_seq_hi_and_res.length() == 4 && node.length() == 12) {
            System.out.println("goede UUID");
        } else {
            System.out.println("slechte UUID");
        }
        return true;
    }
}

