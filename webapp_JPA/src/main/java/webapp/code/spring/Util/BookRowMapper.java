package webapp.code.spring.Util;

import webapp.code.spring.Model.Book;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book>
{
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Book book = new Book();

        book.setBook_id(rs.getInt("book_id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        //book.setOwner(rs.getObject(""));

        return book;
    }
}
