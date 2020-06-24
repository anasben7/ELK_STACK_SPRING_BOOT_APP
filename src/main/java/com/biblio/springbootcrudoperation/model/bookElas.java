package com.biblio.springbootcrudoperation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName="bookelas",type="doc",shards=2)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class bookElas {
	
	@Id
	public Long bookid;
	public String title;
	public String description;
	public String category;
	public String language;
	public int nbrepages;
	public String year;
	public String authorname;


}