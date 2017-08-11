import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.GsonBuilder;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;


import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bhaskar.teste.api.WebApplication;
import com.bhaskar.teste.api.exception.NotFoundException;

import com.bhaskar.teste.api.*;

import com.bhaskar.teste.api.service.PersonsApiService;

import com.bhaskar.teste.model.Object;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebApplication.class})
@WebAppConfiguration
public class PersonsApiTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private PersonsApiService PersonsApiServiceMock;
 
    @InjectMocks
    private PersonsApi PersonsApiMock;

    protected File file;
    protected Persons obj;
 
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(PersonsApiMock)
                .build();
    }
    
   @Before
    public void captureRequestFile() throws JsonSyntaxException, JsonIOException, FileNotFoundException
    {
		file = new File("src/test/resources/PersonsApiTestRequest.json");
 		obj =  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSSSX").create().fromJson(new FileReader(file), Persons.class);
    	
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /*Test POST API*/
    @Test
    public void addTest() throws Exception {
 
    	when(PersonsApiServiceMock.addPersons(obj)).thenReturn(obj);
 
    	mockMvc.perform(post("" + "/" + "persons").contentType("application/json").content(asJsonString(obj)))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().is2xxSuccessful());
    }
    
   /*Test PUT API*/
    @Test
    public void updateTest() throws Exception {
 
    	when(PersonsApiServiceMock.addPersons(obj)).thenReturn(obj);
 
    	mockMvc.perform(put("" + "/" + "persons").contentType("application/json").content(asJsonString(obj)))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().is2xxSuccessful());
    }
    
     /*Test FIND ALL API*/
     @Test
    public void findAllTest() throws Exception {
 
        when(PersonsApiServiceMock.findAllPersons()).thenReturn(Arrays.asList(obj));
    	
    	   
        mockMvc.perform(get("" + "/" + "persons").header("Content-type", "application/json"))
    	.andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().is2xxSuccessful());
    }
    
    /*Test FIND BY ID API*/
    @Test
    public void findByIdFoundTest() throws Exception {
		
        when(PersonsApiServiceMock.findByIdPersons()).thenReturn(obj);
    	
    	   
        mockMvc.perform(get("" + "/" + "persons/" + "{id}", ).header("Content-type", "application/json"))
    	.andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
        
        		
     }
     
    /*Test FIND BY ID 'NOT FOUND' API*/
    @Test
    public void findByIdNotFoundTest() throws Exception {
 
         when(PersonsApiServiceMock.findByIdPersons()).thenThrow(new NotFoundException(404,"Not Found"));
    	   
        mockMvc.perform(get("" + "/" + "persons/" + "{id}", ).header("Content-type", "application/json"))
    	.andExpect(status().isNotFound())
        .andDo(MockMvcResultHandlers.print());
     }
     
}