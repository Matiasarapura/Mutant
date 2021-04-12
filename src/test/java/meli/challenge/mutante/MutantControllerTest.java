package meli.challenge.mutante;

import com.fasterxml.jackson.databind.ObjectMapper;
import meli.challenge.mutante.component.MutantAnalizer;
import meli.challenge.mutante.controller.MutantController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//integration test
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MutantController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MutantAnalizer mutantAnalizer;

    @Test
    void validMutanDnaReturn200() throws Exception {
        var body = new HashMap<String,Object>();
        given(mutantAnalizer.isMutant(any())).willReturn(true);
        given(mutantAnalizer.validateSequenceDna(any())).willReturn(true);
        body.put("dna", new ArrayList(Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG")));
        mvc.perform(post("/mutant").content(objectMapper.writeValueAsBytes(body)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void humanDnaReturn403() throws Exception {
        var body = new HashMap<String,Object>();
        given(mutantAnalizer.isMutant(any())).willReturn(false);
        given(mutantAnalizer.validateSequenceDna(any())).willReturn(true);
        body.put("dna", new ArrayList(Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG")));
        mvc.perform(post("/mutant").content(objectMapper.writeValueAsBytes(body)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
    }

    @Test
    void invalidDnaReturn400() throws Exception {
        var body = new HashMap<String,Object>();
        given(mutantAnalizer.isMutant(any())).willReturn(false);
        given(mutantAnalizer.validateSequenceDna(any())).willReturn(false);
        body.put("dna", new ArrayList(Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG")));
        mvc.perform(post("/mutant").content(objectMapper.writeValueAsBytes(body)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}