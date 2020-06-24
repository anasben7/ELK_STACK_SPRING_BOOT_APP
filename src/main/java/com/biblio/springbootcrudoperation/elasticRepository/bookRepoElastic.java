package com.biblio.springbootcrudoperation.elasticRepository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.biblio.springbootcrudoperation.model.bookElas;


public interface bookRepoElastic extends ElasticsearchRepository<bookElas,String>{
	




}
