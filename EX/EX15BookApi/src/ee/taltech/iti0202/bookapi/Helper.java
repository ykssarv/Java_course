package ee.taltech.iti0202.bookapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Helper.
 */
public class Helper {

    /**
     * To json string.
     *
     * @param books the books
     * @return the string
     */
    public static String toJson(List<Book> books) {
        return new Gson().toJson(books);
    }

    /**
     * To json string.
     *
     * @param book the book
     * @return the string
     */
    public static String toJson(Book book) {
        return new Gson().toJson(book);
    }

    /**
     * To book book.
     *
     * @param bookJson the book json
     * @return the book
     */
    public static Book toBook(String bookJson) {
        return new Gson().fromJson(bookJson, Book.class);
    }

    /**
     * To book list list.
     *
     * @param bookListJson the book list json
     * @return the list
     */
    public static List<Book> toBookList(String bookListJson) {
        Type listType = new TypeToken<ArrayList<Book>>() { }.getType();
        return new Gson().fromJson(bookListJson, listType);
    }

}
