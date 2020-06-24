package com.biblio.springbootcrudoperation.repository;


import com.biblio.springbootcrudoperation.model.eBook;

import org.springframework.data.jpa.repository.JpaRepository;



public interface bookRepository extends JpaRepository <eBook, Long>{
	


}
