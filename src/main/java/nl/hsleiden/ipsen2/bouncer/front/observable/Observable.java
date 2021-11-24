package nl.hsleiden.ipsen2.bouncer.front.observable;

import nl.hsleiden.ipsen2.bouncer.front.models.Response;
import nl.hsleiden.ipsen2.bouncer.front.models.UserAccount;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    List<Observer> observers = new ArrayList<>();
    Response response = new Response();

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers () {
        if (response.getResponseCode() == 0) {
            System.out.println("Je mist een repsonse");
        }

        for (Observer observer: observers) {
            observer.update(this);
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public Response getResponse() {
        return response;
    }
}
