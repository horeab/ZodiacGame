package service;

import org.junit.Test;

import libgdx.services.http.HttpService;

public class HttpServiceTest extends TestMain {

    @Test
    public void makePost() {
        HttpService httpService = new HttpService();
        httpService.makePost();
    }
}
