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
class AnimalControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    void getAnimalSpecies_shouldReturn418AndOnlyDogIsAllowed_whenCalledWithCat() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(MockMvcResultMatchers.status().isIAmATeapot())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "errorMsg": "Only 'dog' is allowed"
                        }
                """));
    }

    @Test
    void getAllAnimals() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "errorMsg": "No Animals found"
                        }
                """));
    }
}