package com.gurianova.aquariumapi.controller;

import com.gurianova.aquariumapi.TestingConstants;
import com.gurianova.aquariumapi.dto.RequestSearchFishDTO;
import com.gurianova.aquariumapi.dto.ResponseFishDTO;
import com.gurianova.aquariumapi.dto.ResponseSearchFishDTO;
import com.gurianova.aquariumapi.dto.ResponseSearchFishErrorDTO;
import com.gurianova.aquariumapi.exception.AquariumErrorCodes;
import com.gurianova.aquariumapi.exception.ServedByOtherMethodException;
import com.gurianova.aquariumapi.persistance.entity.Fish;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class FishControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void t01_createFishSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = TestingConstants.ENDPOINT_URL + randomServerPort + "/fishes";
        URI uri = new URI(baseUrl);

        HttpEntity<Fish> request = new HttpEntity<>(new Fish(1, "AngelFish", 3, "Flakes", "25.07.22"));
        ResponseEntity<Fish> response = restTemplate
                .exchange(uri, HttpMethod.PUT, request, Fish.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Fish actualFish = response.getBody();

        Assertions.assertNotNull(actualFish);
        Assertions.assertEquals(1, actualFish.getId());
        Assertions.assertEquals("AngelFish", actualFish.getName());
        Assertions.assertEquals(3, actualFish.getAgeYears());
        Assertions.assertEquals("Flakes", actualFish.getPreferredFood());
        Assertions.assertEquals("25.07.22", actualFish.getDateOfPurchase());
    }


    @Test
    public void t02_replaceFishSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(TestingConstants.ENDPOINT_URL + randomServerPort + "/fishes");

        HttpEntity<Fish> request = new HttpEntity<>(new Fish(1, "ReplacedFish", 2, "Flakes", "25.07.22"));
        ResponseEntity<Fish> response = restTemplate
                .exchange(uri, HttpMethod.PUT, request, Fish.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Fish actualFish = response.getBody();

        Assertions.assertNotNull(actualFish);

        Assertions.assertEquals(1, actualFish.getId());
        Assertions.assertEquals("ReplacedFish", actualFish.getName());
        Assertions.assertEquals(2, actualFish.getAgeYears());
        Assertions.assertEquals("Flakes", actualFish.getPreferredFood());
        Assertions.assertEquals("25.07.22", actualFish.getDateOfPurchase());
    }

    @Test
    public void t03_testGetAllFishesIsSuccessful() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = TestingConstants.ENDPOINT_URL + randomServerPort + "/fishes";
        URI uri = new URI(baseUrl);


        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("ReplacedFish"));
    }

    @Test
    public void t04_replaceWhenIdIsNotInDbSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = TestingConstants.ENDPOINT_URL + randomServerPort + "/fishes";
        URI uri = new URI(baseUrl);

        HttpEntity<Fish> request = new HttpEntity<>(new Fish(999, "AngelFish", 3, "Flakes", "25.07.22"));
        ResponseEntity<Fish> response = restTemplate
                .exchange(uri, HttpMethod.PUT, request, Fish.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Fish actualFish = response.getBody();

        Assertions.assertNotNull(actualFish);
        Assertions.assertEquals(2, actualFish.getId());
        Assertions.assertEquals("AngelFish", actualFish.getName());
        Assertions.assertEquals(3, actualFish.getAgeYears());
        Assertions.assertEquals("Flakes", actualFish.getPreferredFood());
        Assertions.assertEquals("25.07.22", actualFish.getDateOfPurchase());
    }

    @Test
    public void t05_testSearchFishesIsSuccessful() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = TestingConstants.ENDPOINT_URL + randomServerPort + "/fishes/search";
        URI uri = new URI(baseUrl);
        HttpEntity<RequestSearchFishDTO> request = new HttpEntity(new RequestSearchFishDTO(1, "AngelFish", 3, "Flakes", "25.07.22"));
        ResponseEntity<ResponseSearchFishDTO[]> response = restTemplate
                .exchange(uri, HttpMethod.POST, request, ResponseSearchFishDTO[].class);


        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals("AngelFish", (response.getBody()[0].getName()));
        Assert.assertEquals((Integer) 3, (response.getBody()[0].getAgeYears()));
        Assert.assertEquals("Flakes", (response.getBody()[0].getPreferredFood()));
        Assert.assertEquals("25.07.22", (response.getBody()[0].getDateOfPurchase()));
    }

    @Test
    public void t06_testSearchFishesIsUnSuccessful() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = TestingConstants.ENDPOINT_URL + randomServerPort + "/fishes/search";
        URI uri = new URI(baseUrl);
        HttpEntity<RequestSearchFishDTO> request = new HttpEntity(new RequestSearchFishDTO());

        ResponseEntity<ResponseSearchFishErrorDTO[]> response = restTemplate
                .exchange(uri, HttpMethod.POST, request, ResponseSearchFishErrorDTO[].class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals(AquariumErrorCodes.SEARCH_FISH_PAYLOAD_HAS_NO_PARAMETERS.getCode(), (response.getBody()[0].getCode()));
        Assert.assertEquals(AquariumErrorCodes.SEARCH_FISH_PAYLOAD_HAS_NO_PARAMETERS.getDescription(), (response.getBody()[0].getDescription()));
    }
}