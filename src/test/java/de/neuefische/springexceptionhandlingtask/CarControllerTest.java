package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    void getCarBrand() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/cat"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "errorMsg": "Only 'porsche' allowed"
                        }
                """));
                //JsonPath.isNotEmpty checkt ob Feld vorhanden UND befüllt, aber nicht den tatsächlichen Value
                // -> wird genutzt z. B. für Ids, zeitstempel oder anderes, schlecht errechenbares!
                //.andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    void getAllCars() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "errorMsg": "No Cars found"
                        }
                """));
    }
}