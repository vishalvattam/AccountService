package com.anz.ws.accounts.IT;

import com.anz.ws.accounts.AccountServiceApplication;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AccountControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Test
    public void
    findAccount_shouldReturnEmptyArray_forNotFoundAccountForUser() {

        String userIdWithNoAccount = "userIdWithNoAccount";
        given()
                .port(port)
                .when()
                .get("/v1/account/" + userIdWithNoAccount)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON)
                .log().all();
    }

    @Test
    public void findAccount_shouldReturnAccountList_forAccountsForUser() {
        String userId = "anz-user-test";
        String responseBody = given()
                .port(port)
                .when()
                .get("/v1/account/" + userId)
                .then().assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        assertThat(responseBody.contains("anz-test-user") );

    }

    @Test
    public void findTransaction_shouldReturnEmptyList_forNotFoundTransactionForAccount() {

        String accNoWithNoTxn = "accNoWithNOTxn";
        given()
                .port(port)
                .when()
                .get("/v1/account/" + accNoWithNoTxn + "/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON)
                .log().all();
    }

    @Test
    public void findTransaction_shouldReturnAccountList_forTransactionsForAccount() {

        String responseBody = given()
                .port(port)
                .when()
                .get("/v1/account/100001/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().body().asString();
        assertThat(responseBody.contains("100001") );
    }
}
