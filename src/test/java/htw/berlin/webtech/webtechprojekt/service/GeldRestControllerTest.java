package htw.berlin.webtech.webtechprojekt.service;

import htw.berlin.webtech.webtechprojekt.GeldRestController;
import htw.berlin.webtech.webtechprojekt.api.Geld;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GeldRestController.class)
public class GeldRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeldService geldService;

    @Test
    @DisplayName("Es sollte gelder gefunden von geld service zurück gegeben werden")
    void shouldReturnFoundGelderFormGerldeService() throws Exception{
        var gelder = List.of(
                new Geld(1, "Mittagessen", 15, false),
                new Geld(2, "Gehalt", 450, true)
        );
        doReturn(gelder).when(geldService).findAll();

        mockMvc.perform(get("/api/v1/gelder"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Mittagessen"))
                .andExpect(jsonPath("$[0].geldBetrag").value(15))
                .andExpect(jsonPath("$[0].einnahme").value(false))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Gehalt"))
                .andExpect(jsonPath("$[1].geldBetrag").value(450))
                .andExpect(jsonPath("$[1].einnahme").value(true));

    }

    @Test
    @DisplayName("soll 404 zurückgeben wenn geld nicht gefunden wird")
    void shouldReturn404IfGeldNotFound() throws Exception{

        doReturn(null).when(geldService).findById(anyLong());

        mockMvc.perform(get("/api/v1/gelder/41")).andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("soll 201 http stutus und Location header zurückgeben wenn ein geld erstellt wird")
    void shouldReturn201HttpStatusAndLocationHeaderIfCreationAGeld() throws Exception{

        String geldToCreateAsJson = "{\"name\": \"Schuhe\", \"geldBetrag\": 100, \"einnahme\": false}";
        var geld = new Geld(45, null, 0, true);
        doReturn(geld).when(geldService).create(any());

        mockMvc.perform(
                post("/api/v1/gelder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geldToCreateAsJson)
        )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo("/api/v1/gelder/" + geld.getId())));
    }



}
