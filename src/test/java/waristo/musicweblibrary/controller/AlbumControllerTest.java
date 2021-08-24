package waristo.musicweblibrary.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import waristo.musicweblibrary.entity.Album;
import waristo.musicweblibrary.service.AlbumService;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class AlbumControllerTest {
    private MockMvc mvc;

    @Mock
    AlbumService albumService;

    @InjectMocks
    AlbumController albumController;

    private JacksonTester<Page<Album>> albumPageTester;
    private JacksonTester<Iterable<Album>> albumIterTester;
    private JacksonTester<Album> albumTester;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(albumController)
                .build();
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
        JsonNode albumPageNode = mapper.readTree(albumPageTester.write(albumPage).getJson());
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
    public void albumById_OK() throws Exception {
        //given
        Album album = new Album(1L, "a", null, "a", null, 1);
        given(albumService.searchAlbum(1L)).willReturn(Optional.of(album));
        ObjectMapper mapper = new ObjectMapper();

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/album/1")
                    .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        JsonNode responseNode = mapper.readTree(response.getContentAsString());
        JsonNode albumNode = mapper.readTree(albumTester.write(album).getJson());
        assertThat(responseNode).isEqualTo(albumNode);
    }

    @Test
    public void albumById_NOT_FOUND() throws Exception {
        //given
        given(albumService.searchAlbum(1L)).willReturn(Optional.empty());

        //when
        MockHttpServletResponse response = mvc.perform(
                get("/album/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}