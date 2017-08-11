package com.bhaskar.teste.api.service;

import com.bhaskar.teste.model.Object;

import com.bhaskar.teste.exception.NotFoundException;
import java.util.List;
public interface PersonsApiService {
  
      List<Object> personsGet(Double size)
      throws NotFoundException;
  
}
