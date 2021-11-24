package nl.hsleiden.ipsen2.bouncer.front.repository;

import nl.hsleiden.ipsen2.bouncer.front.application.RequestManager;
import nl.hsleiden.ipsen2.bouncer.front.models.AuthenticationResponse;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UserRepository extends Observable {
    public void apiLoginResponse(String username, String password) {
        Map<String, String> params = new HashMap<>();

        params.put("username", username);
        params.put("password", password);
        // response -> httpResponse
        // uit elkaar houden van de response van de superclass Observable en de locale response van het type HttpResponse
        HttpResponse httpResponse = RequestManager.makePostRequest("/login", params, false);
        this.getResponse().setResponseCode(httpResponse.getStatusLine().getStatusCode());

        if (httpResponse.getEntity() != null) {
            AuthenticationResponse result = (AuthenticationResponse) RequestManager.getModel(httpResponse.getEntity(), AuthenticationResponse.class);
            this.getResponse().setResult(result);
        }

        this.notifyObservers();
    }
}
