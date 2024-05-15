package dev.iury.project.service;

import dev.iury.project.Exceptions.ResourceNotFoundException;
import dev.iury.project.Exceptions.RiqueredObjectsNullException;
import dev.iury.project.controllers.BookController;
import dev.iury.project.dataVO.BookVO;
import dev.iury.project.mapper.DozerMapper;
import dev.iury.project.mapper.custom.BookMapper;
import dev.iury.project.model.Book;
import dev.iury.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    public List<BookVO> findAll(){
        List<BookVO> listAqui = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        for(BookVO book : listAqui){
            book.setKey(book.getKey());
            book.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        }
        return listAqui;
    }

    public BookVO findById(Long key){
        var entity = bookRepository.findById(key).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(key)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO book){
        if(book == null) throw new RiqueredObjectsNullException();
        var entity = DozerMapper.parseObject(book, Book.class);
        var savedEntity = bookRepository.save(entity);
        var vo = DozerMapper.parseObject(savedEntity, BookVO.class);
        vo.setKey(savedEntity.getId());
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

//    public BookVOV2 createv2(BookVOV2 book){
//        var entity = bookMapper.convertVov2ToEntity(book);
//        var vo = bookMapper.convertEntityToVoV2(bookRepository.save(entity));
//        return vo;
//    }

    public BookVO update(BookVO book, Long key) {
        if(book == null) throw new RiqueredObjectsNullException();
        var entity = bookRepository.findById(key).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());

        var savedEntity = bookRepository.save(entity);
        var vo = DozerMapper.parseObject(savedEntity, BookVO.class);
        vo.setKey(savedEntity.getId());
        vo.add(linkTo(methodOn(BookController.class).findById(key)).withSelfRel());
        return vo;
    }

    public BookVO delete(Long key) {
        bookRepository.findById(key).map(p1 -> {
            bookRepository.delete(p1);
            return p1;
        });
        return null;
    }
}
