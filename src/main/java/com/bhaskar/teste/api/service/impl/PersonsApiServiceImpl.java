package com.bhaskar.teste.api.service.impl;

import com.bhaskar.teste.api.service.*;
import com.bhaskar.teste.model.*;
import org.springframework.stereotype.Service;
import com.bhaskar.teste.model.repository.*;
import org.springframework.http.HttpStatus;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.bhaskar.teste.model.Object;

import java.util.List;
import com.bhaskar.teste.exception.NotFoundException;

@Service
@Transactional
public class PersonsApiServiceImpl implements PersonsApiService {
		  	  
	  @Autowired
	
  
  
				
			
      @Override
      public List<Object> personsGet(Double size)
      throws NotFoundException {
      
        
  		
  		
  		
  		
  		
  		
  		
  		 
  		  return null;
  		
  }
  
}
