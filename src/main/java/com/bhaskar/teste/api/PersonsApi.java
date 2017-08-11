package com.bhaskar.teste.api;

import com.bhaskar.teste.model.*;

import com.bhaskar.teste.api.service.PersonsApiService;

import com.bhaskar.teste.exception.NotFoundException;

import com.bhaskar.teste.model.Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping(value = "" , produces = "application/json" )
public class PersonsApi {

   @Autowired
   private PersonsApiService service;
  

  @RequestMapping(value = "/persons", 
    
    
    method = RequestMethod.GET)
  public ResponseEntity< List<Object>> personsGet(@RequestParam(value = "size", required = true) Double size
)
      throws NotFoundException {
       
  	    
       	  return new ResponseEntity<List<Object>>(service.personsGet(size), HttpStatus.OK);
   	    
  }

  
}
