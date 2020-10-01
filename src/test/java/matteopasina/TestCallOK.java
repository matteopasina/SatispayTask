package matteopasina;

import matteopasina.authentication.Crypto;
import matteopasina.model.request.Request;
import matteopasina.model.request.body.RequestBody;
import matteopasina.model.response.Response;
import matteopasina.services.AuthService;
import matteopasina.services.IAuthService;
import matteopasina.utils.HTTPVerb;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class TestCallOK {
    private static IAuthService service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new AuthService(new Crypto());
    }

    @Test
    public void testGetOK() {
        Request request = new Request(HTTPVerb.GET);
        request.putHeader("Date", DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now()));

        Response response = service.call(request);


        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertNotEquals("PUBLIC", response.getResponseBody().getAuthenticationKey().getRole());
    }

    @Test
    public void testPostOK() {
        Request request = new Request(HTTPVerb.POST);
        request.putHeader("Date", DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now()));
        request.setBody(new RequestBody("Please let me in"));

        Response response = service.call(request);


        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertNotEquals("PUBLIC", response.getResponseBody().getAuthenticationKey().getRole());
    }

    @Test
    public void testPutOK() {
        Request request = new Request(HTTPVerb.PUT);
        request.putHeader("Date", DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now()));
        request.setBody(new RequestBody("Please let me in"));

        Response response = service.call(request);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertNotEquals("PUBLIC", response.getResponseBody().getAuthenticationKey().getRole());
    }

    @Test
    public void testDeleteOK() {
        Request request = new Request(HTTPVerb.DELETE);
        request.putHeader("Date", DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now()));

        Response response = service.call(request);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertNotEquals("PUBLIC", response.getResponseBody().getAuthenticationKey().getRole());
    }
}