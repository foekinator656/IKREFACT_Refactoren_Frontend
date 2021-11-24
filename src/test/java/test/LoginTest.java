package test;

import nl.hsleiden.ipsen2.bouncer.front.views.LoginView;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginTest {
    @Test
    public void should_return_True_when_correct_email() {
        //arrange
        boolean expectedResult = true;
        LoginView loginView = new LoginView();
        ArrayList<String> goodEmails = new ArrayList<String>();
        String email = "test@test.com";
        goodEmails.add(email);
        goodEmails.add("123@test.com");
        goodEmails.add("1234567890123456789012345678901234567890123456789012345678901234@example.com");
        goodEmails.add("12345678901234567890123456789012@example.com");

        for (String goodEmail : goodEmails){
            System.out.println(goodEmail);
            //act
            boolean actualResult = loginView.verify_email(goodEmail);
            //assert
            assertThat(expectedResult,is(actualResult));
        }
    }
    @Test
    public void should_return_False_when_incorrect_email() {
        //arrange
        boolean expectedResult = false;
        LoginView loginView = new LoginView();

        ArrayList<String> badEmails = new ArrayList<String>();
        badEmails.add("test");
        badEmails.add("test@test");
        badEmails.add("test@");
        badEmails.add("@test");
        badEmails.add(".com");
//        badEmails.add("123@test.com");
//        badEmails.add("1234567890123456789012345678901234567890123456789012345678901234@example.com");
//        badEmails.add("12345678901234567890123456789012@example.com");

        for (String badEmail : badEmails){
            System.out.println(badEmail);
            //act
            boolean actualResult = loginView.verify_email(badEmail);
            //assert
            assertThat(expectedResult,is(actualResult));
        }
    }
    @Test
    public void should_return_True_when_correct_UUID() {
        //arrange
        boolean expectedResult = true;
        LoginView loginView = new LoginView();

        ArrayList<String> goodUUIDs = new ArrayList<String>();

        for (int i = 0; i <= 10; i++){
            UUID uuid = UUID.randomUUID();
            String UUIDAsString = uuid.toString();
            goodUUIDs.add(UUIDAsString);
        }
        for (String goodUUID : goodUUIDs){
            System.out.println(goodUUID);
        }
        System.out.println(" ");
        for (String goodUUID : goodUUIDs){
            System.out.println(goodUUID);
            //act
            boolean actualResult = loginView.verify_email(goodUUID);
            //assert
            assertThat(expectedResult,is(actualResult));
        }
    }
    @Test
    public void should_return_False_when_incorrect_UUID() {
        //arrange
        boolean expectedResult = false;
        LoginView loginView = new LoginView();

        ArrayList<String> badUUIDs = new ArrayList<String>();
        badUUIDs.add("b84f463e-b4f9-472d-bd4a");
        badUUIDs.add("b84f463e-b4f9bd4a-b9a4717d4552");
        badUUIDs.add("b84f463eb4f9472dbd4ab9a4717d4552");

        for (String badUUID : badUUIDs){
            System.out.println(badUUID);
            //act
            boolean actualResult = loginView.verify_email(badUUID);
            //assert
            assertThat(expectedResult,is(actualResult));
        }
    }

}
