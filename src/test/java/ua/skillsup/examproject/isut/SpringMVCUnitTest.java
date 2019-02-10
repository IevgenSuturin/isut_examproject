package ua.skillsup.examproject.isut;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.skillsup.examproject.isut.controller.ItemController;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.service.GetInformationService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class SpringMVCUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GetInformationService informationService;

    @Before
    public void setUpInformationService(){
        ItemDto item = new ItemDto(new Item("Phone Samsung", "Samsung", 20));
        List<ItemDto> allItems = Arrays.asList(item);

        Mockito.when(informationService.getAllItems()).thenReturn(allItems);
    }

    @Test
    public void givenItems_whenGetItem_thenReturnJsonArray() throws Exception{
        mvc.perform(get("/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Phone Samsung"));
    }
}
