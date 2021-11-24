package nl.hsleiden.ipsen2.bouncer.front.controllers;

import nl.hsleiden.ipsen2.bouncer.front.application.ViewManager;
import nl.hsleiden.ipsen2.bouncer.front.models.Status;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observer;
import nl.hsleiden.ipsen2.bouncer.front.repository.RequestRepository;

public class RequestReviewController {
    private RequestRepository requestRepository;

    public RequestReviewController (Observer observer) {
        this.requestRepository = new RequestRepository();
        this.requestRepository.register(observer);
    }

    public void findById(Long id) {
        this.requestRepository.findById(id);
    }

    public void updateRequestStatus(Long id, Status status) {
        this.requestRepository.reviewRequest(id, status);
        ViewManager.backToLastScene();
    }
}
