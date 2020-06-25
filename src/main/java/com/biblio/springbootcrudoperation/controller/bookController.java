package com.biblio.springbootcrudoperation.controller;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.biblio.springbootcrudoperation.elasticRepository.bookRepoElastic;
import com.biblio.springbootcrudoperation.exception.ResourceNotFoundException;
import com.biblio.springbootcrudoperation.model.bookElas;
import com.biblio.springbootcrudoperation.model.eBook;
import com.biblio.springbootcrudoperation.repository.bookRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
@RestController
@RequestMapping("/api/book")
@CrossOrigin("http://localhost:4200")
public class bookController {
	@Autowired
	bookRepository bookRepository;
	@Autowired
	bookRepoElastic bookRepoElastic;
	@Autowired
	ElasticsearchTemplate template;
	Runtime rt = Runtime.getRuntime();
	
		//List all books 
		@GetMapping("/all")
		//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public List<eBook> findAllBooks() {
			return bookRepository.findAll();
				}
		
		
		//save book
		@PostMapping("/save")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public eBook saveUser(@RequestBody eBook ebook) {
			return bookRepository.save(ebook);
					
				}	
	
		//Get book by title
		@GetMapping("/search/{title}")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public List<bookElas> findbyTitle(@PathVariable String title) {
	        String search = ".*"+title+".*";
	        SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("title", search)).build();
	        List<bookElas> books = template.queryForList(searchQuery, bookElas.class);
	        return books;
	    }
		
		
	
	
		//Update book
		@PutMapping("/update/{bookid}")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public eBook updateBook(@PathVariable(value = "bookid") Long bookid,
                @Valid @RequestBody eBook bookDetails) {

			eBook book = bookRepository.findById(bookid)
							.orElseThrow(() -> new ResourceNotFoundException("eBook", "bookid", bookid));

				book.setTitle(bookDetails.getTitle());
				book.setDescription(bookDetails.getDescription());
				book.setLanguage(bookDetails.getLanguage());
				book.setNbrepages(bookDetails.getNbrepages());
				book.setYear(bookDetails.getYear());
				book.setAuthorname(bookDetails.getAuthorname());
				book.setCategory(bookDetails.getCategory());

				eBook updatedBook = bookRepository.save(book);
					return updatedBook;
		}
		
		//Delete book
		@DeleteMapping("/delete/{bookid}")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<?> deleteBook(@PathVariable(value = "bookid") Long bookid) throws IOException {
			eBook book = bookRepository.findById(bookid)
		            .orElseThrow(() -> new ResourceNotFoundException("eBook", "bookid", bookid));

			bookRepository.delete(book);
			Process proc = rt.exec("curl -XDELETE http://localhost:9200/bookelas/doc/"+bookid);
		
				

		    return ResponseEntity.ok().build();
		} 
	

}