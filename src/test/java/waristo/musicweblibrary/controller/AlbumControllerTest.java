package waristo.musicweblibrary.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import waristo.musicweblibrary.entity.Album;
import waristo.musicweblibrary.exception.CustomException;
import waristo.musicweblibrary.service.AlbumService;

import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class AlbumControllerTest {
    private MockMvc mvc;

    @Mock
    AlbumService albumService;

    @InjectMocks
    AlbumController albumController;

    private JacksonTester<Page<Album>> albumPageJson;
    private JacksonTester<Iterable<Album>> albumIterJson;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(albumController)
                .build();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getAlbums_NO_PARAM() throws Exception {
        //given
        List<Album> arr = new LinkedList<>();
        arr.add(new Album(1L, "a", null, "a", null, 1));
        arr.add(new Album(2L, "b", null, "b", null, 2));
        Page<Album> albumPage = new PageImpl<>(arr);
        ObjectMapper mapper = new ObjectMapper();

        given(albumService.searchAlbums(0, 10, "desc", "releaseDate")).willReturn(albumPage);

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/album")
                    .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JsonNode responseNode = mapper.readTree(response.getContentAsString());
        JsonNode albumPageNode = mapper.readTree(albumPageJson.write(albumPage).getJson());
        assertThat(responseNode).isEqualTo(albumPageNode);
    }

    @Test
    public void getAlbums_WRONG_PARAM() throws Exception {
        //given
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("size", "abc");

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/album")
                        .params(param)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void getAdcancedSearch() {
    }

    @Test
    public void postAlbum() {
    }

    @Test
    public void albumById() {
    }

    @Test
    public void deleteAlbum() {
    }
}