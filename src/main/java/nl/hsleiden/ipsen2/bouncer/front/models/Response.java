package nl.hsleiden.ipsen2.bouncer.front.models;

public class Response {
    private int responseCode;
    private Object result;

    public Response() {
        this.responseCode = 0;
    }

    public Response(int responseCode, Object result) {
        this.responseCode = responseCode;
        this.result = result;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
