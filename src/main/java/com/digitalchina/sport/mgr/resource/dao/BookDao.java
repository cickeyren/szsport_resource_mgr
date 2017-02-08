package com.digitalchina.sport.mgr.resource.dao;


import com.digitalchina.sport.mgr.resource.model.Book;
import com.digitalchina.sport.mgr.resource.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by xingding on 2016/11/12.
 */
@Mapper
public interface BookDao {

    List<Book> findAll(Map<String, Object> params);

    int insertBook(Book book);

    int updateBook(Book book);

    int deleteBook(Long id);

    Book selectBookById(Long id);

    List<Category> findCategorys();

    int findTotalCount(Map<String, Object> params);

}
