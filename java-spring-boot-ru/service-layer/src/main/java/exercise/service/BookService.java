package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        return books.stream().map(bookMapper::map).toList();
    }

    public BookDTO findById(long id) {
        var book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("book with id " + id + "not found"));
        return bookMapper.map(book);
    }

    public BookDTO create(BookCreateDTO data) {
        var book = bookMapper.map(data);
        bookRepository.save(book);
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public BookDTO update(BookUpdateDTO data, long id) {
        var book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("book with id " + id + " not found"));
        var author = authorRepository.findById(data.getAuthorId().get()).orElseThrow(() ->
                new ResourceNotFoundException("author with id " + id + " not found"));
        bookMapper.update(data, book);
        book.setAuthor(author);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(long id) {
        var book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("book with id " + id + " not found"));
        bookRepository.delete(book);
    }
    // END
}
