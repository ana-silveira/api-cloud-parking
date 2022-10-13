package com.parkingapi.cloudparking.controller;

import com.parkingapi.cloudparking.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT ) // Como está rodando num CI, vou rodar o teste em porta aleatória
class ParkingControllerTest  extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        //System.out.println(randomPort);
        RestAssured.port = randomPort;
    }
    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .auth()
                .basic("user", "123")
                .when()
                .get("/parking")
                .then()
                // .statusCode(201) -> Para confirmar se o status code apresentado é o esperado
                // .statusCode(HttpStatus.OK.value()) -> Outra forma de escrever, mais apresentável
                //.extract().response().body().prettyPrint(); (para exibir os itens da Array)
                .body("license[0]", Matchers.equalTo("WRT-5555"));
    }

    @Test
    void wherCreateThenCheckIsCreated() {

        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("WRT-5555");
        createDTO.setModel("BRASILIA");
        createDTO.setState("SP");
        RestAssured.given()
                .auth()
                .basic("user", "123")
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                // .statusCode(201) -> Para confirmar se o status code apresentado é o esperado
                .statusCode(HttpStatus.CREATED.value()) // Melhor apresentação do statusCOde
                .body("license", Matchers.equalTo("WRT-5555")) // Vai funcionar: O retorno é igual ao esperado.
                .body("color", Matchers.equalTo("AMARELO")); // Vai falhar: Está esperando retorno de AZUL, veio AMARELO.
                //.extract().response().body().prettyPrint(); (para exibir os itens da Array)

    }
}