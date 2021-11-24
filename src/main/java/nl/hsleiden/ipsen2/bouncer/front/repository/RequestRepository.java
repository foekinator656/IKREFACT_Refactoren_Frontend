package nl.hsleiden.ipsen2.bouncer.front.repository;

import nl.hsleiden.ipsen2.bouncer.front.application.RequestManager;
import nl.hsleiden.ipsen2.bouncer.front.application.ViewManager;
import nl.hsleiden.ipsen2.bouncer.front.exceptions.NotAuthorizedException;
import nl.hsleiden.ipsen2.bouncer.front.models.Request;
import nl.hsleiden.ipsen2.bouncer.front.models.RequestUpdate;
import nl.hsleiden.ipsen2.bouncer.front.models.Status;
import nl.hsleiden.ipsen2.bouncer.front.observable.Observable;
import org.apache.http.HttpResponse;

import java.util.List;

public class RequestRepository extends Observable {
//    public String createNewRequest(String targetURL, String urlParameters) {
//        try {
//            //Create connection
//            URL url = new URL(targetURL);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
//
//            connection.setRequestProperty("Content-Length",
//                    Integer.toString(urlParameters.getBytes().length));
//            connection.setRequestProperty("Content-Language", "en-US");
//
//            connection.setUseCaches(false);
//            connection.setDoOutput(true);
//
//            //Send request
//            DataOutputStream wr = new DataOutputStream (
//                    connection.getOutputStream());
//            wr.writeBytes(urlParameters);
//            wr.close();
//
//            //Get Response
//            InputStream is = connection.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
//            }
//            rd.close();
//            return response.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//    }
//    public String findAllRequests(String targetURL, String urlParameters) {
//        try {
//            //Create connection
//            URL url = new URL(targetURL);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
//
//            connection.setRequestProperty("Content-Length",
//                    Integer.toString(urlParameters.getBytes().length));
//            connection.setRequestProperty("Content-Language", "en-US");
//
//            connection.setUseCaches(false);
//            connection.setDoOutput(true);
//
//            //Send request
//            DataOutputStream wr = new DataOutputStream (
//                    connection.getOutputStream());
//            wr.writeBytes(urlParameters);
//            wr.close();
//
//            //Get Response
//            InputStream is = connection.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
//            }
//            rd.close();
//            return response.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//    }
//    public Object findOneRequest(int id, String targetURL, String urlParameters) {
//
//        try {
//        //Create connection
//        URL url = new URL(targetURL);
//        connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type",
//        "application/x-www-form-urlencoded");
//
//        connection.setRequestProperty("Content-Length",
//        Integer.toString(urlParameters.getBytes().length));
//        connection.setRequestProperty("Content-Language", "en-US");
//
//        connection.setUseCaches(false);
//        connection.setDoOutput(true);
//
//        //Send request
//        DataOutputStream wr = new DataOutputStream (
//        connection.getOutputStream());
//        wr.writeBytes(urlParameters);
//        wr.close();
//
//        //Get Response
//        InputStream is = connection.getInputStream();
//        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//        StringBuilder response = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//        response.append(line);
//        response.append('\r');
//        }
//        rd.close();
//        return response.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (connection != null) {
//            connection.disconnect();
//            }
//        }
//    }
    public void findAll () {
        try {
            HttpResponse response = RequestManager.makeGetRequest("/request");
            this.getResponse().setResponseCode(response.getStatusLine().getStatusCode());

            if (response.getEntity() != null) {
                List<Request> requests = (List<Request>) RequestManager.getList(response.getEntity(), Request.class);

                this.getResponse().setResult(requests);
            }

            this.notifyObservers();
        } catch (NotAuthorizedException e) {
            e.printStackTrace();

            ViewManager.setShowingScene("Login");
        }
    }

    public void findById (Long id) {
        try {
            HttpResponse response = RequestManager.makeGetRequest("/request/" + id.toString());
            this.getResponse().setResponseCode(response.getStatusLine().getStatusCode());

            if (response.getEntity() != null) {
                Request requests = (Request) RequestManager.getModel(response.getEntity(), Request.class);

                this.getResponse().setResult(requests);
            }

            this.notifyObservers();
        } catch (NotAuthorizedException e) {
            e.printStackTrace();

            ViewManager.setShowingScene("Login");
        }
    }

    public void reviewRequest (Long id, Status newStatus) {
        try {
            HttpResponse response = RequestManager.makeGetRequest(
                    String.format("/request/%s/%s/review",
                            id.toString(),
                            newStatus.getValue()));
            this.getResponse().setResponseCode(response.getStatusLine().getStatusCode());

            if (response.getEntity() != null) {
                RequestUpdate requestUpdate = (RequestUpdate) RequestManager.getModel(response.getEntity(), RequestUpdate.class);

                this.getResponse().setResult(requestUpdate);
            }
        } catch (NotAuthorizedException e) {
            e.printStackTrace();

            ViewManager.setShowingScene("Login");
        }
    }
}
