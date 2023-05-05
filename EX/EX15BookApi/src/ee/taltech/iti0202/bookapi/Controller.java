package ee.taltech.iti0202.bookapi;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Controller.
 */
public class Controller {

    private static final String DATA = "data";

    /**
     * Get string.
     *
     * @param path the path
     * @return the string
     */
    public static String get(String path) {
        if (path.equals("/books")) {
            return Helper.toJson(getAllBooks());
        } else if (path.contains("?")) {
            String parameters = path.split("\\?")[1];
            List<String> filters = Arrays.asList(parameters.split("&"));
            return Helper.toJson(filter(
                getAllBooks().stream().sorted(Comparator.comparingInt(book -> book.getId().orElse(0))),
                filters
            ).collect(Collectors.toList()));
        } else if (path.split("/").length == 3) {
            String id = path.split("/")[2];
            return Helper.toJson(getBookById(id));
        }
        return "Invalid path: " + path;
    }

    /**
     * Post.
     *
     * @param path the path
     * @param book the book
     */
    public static void post(String path, Book book) {
        if (path.equals("/books")) {
            int nextId = getMaxId() + 1;
            book.withId(nextId);
            saveBook(book, nextId);
        }
    }

    /**
     * Put.
     *
     * @param path the path
     * @param book the book
     */
    public static void put(String path, Book book) {
        if (path.split("/").length == 3) {
            int id = Integer.parseInt(path.split("/")[2]);
            book.withId(id);
            saveBook(book, id);
        }
    }

    /**
     * Delete.
     *
     * @param path the path
     */
    public static void delete(String path) {
        if (path.split("/").length == 3) {
            String id = path.split("/")[2];
            File file = new File(DATA + "/" + id + ".txt");
            file.delete();
        }
    }

    private static void saveBook(Book book, int id) {
        try {
            Request.saveToFile(Helper.toJson(book), DATA + "/" + id + ".txt");
        } catch (IOException e) {
            System.out.println("Problems saving book");
        }
    }

    /**
     * Gets max id.
     *
     * @return the max id
     */
    public static Integer getMaxId() {
        return Arrays.stream(Objects.requireNonNull(new File(DATA).listFiles()))
            .map(File::toString)
            .map(filename -> filename.split("\\\\")[1])
            .map(filename -> filename.split("\\.")[0])
            .mapToInt(Integer::parseInt)
            .max().orElse(1);
    }

    private static Stream<Book> filter(Stream<Book> bookStream, List<String> filters) {
        if (filters.size() == 0) {
            return bookStream;
        }
        String filter = filters.get(0);
        Stream<Book> filtered = filter(bookStream, filter);
        return filter(filtered, filters.subList(1, filters.size()));
    }

    private static Stream<Book> filter(Stream<Book> bookStream, String filter) {
        String[] splitFilter = filter.split("=");
        String keyword = splitFilter[0];
        String value = splitFilter[1];
        if (keyword.equals("offset")) {
            return bookStream.filter(book -> book.getId().isPresent()
                && book.getId().get() >= Integer.parseInt(value)
            );
        }
        if (keyword.equals("limit")) {
            return bookStream.limit(Long.parseLong(value));
        }
        if (keyword.equals("title")) {
            return bookStream.filter(book -> book.getTitle().isPresent()
                && book.getTitle().get().equals(value)
            );
        }
        if (keyword.equals("author")) {
            return bookStream.filter(book -> book.getAuthor().isPresent()
                && book.getAuthor().get().equals(value)
            );
        }
        if (keyword.equals("year")) {
            return bookStream.filter(book -> book.getYearOfPublishing().isPresent()
                && book.getYearOfPublishing().get().equals(Integer.parseInt(value))
            );
        }
        if (keyword.equals("sort")) {
            if (value.equals("alphabetical")) {
                return bookStream.sorted(Comparator.comparing(book -> book.getTitle().orElse("")));
            }
            if (value.equals("pages")) {
                return bookStream.sorted(Comparator.comparingInt(book -> book.getPageAmount().orElse(0)));
            }
            if (value.equals("year")) {
                return bookStream.sorted(Comparator.comparingInt(book -> book.getYearOfPublishing().orElse(0)));
            }
        }
        return bookStream;
    }

    /**
     * Gets book by id.
     *
     * @param id the id
     * @return the book by id
     */
    public static Book getBookById(String id) {
        try {
            String data = Request.readFromFile(DATA + "/" + id + ".txt");
            return Helper.toBook(data);
        } catch (FileNotFoundException e) {
            System.out.println("No such book");
            return null;
        }
    }

    /**
     * Gets all books.
     *
     * @return the all books
     */
    public static List<Book> getAllBooks() {
        return Arrays.stream(Objects.requireNonNull(new File(DATA).listFiles()))
            .map(file -> {
                try {
                    String content = Request.readFromFile(file);
                    Book book = new Gson().fromJson(content, Book.class);
                    book.withId(Integer.parseInt(file.toString().split("\\\\")[1].split("\\.")[0]));
                    return book;
                } catch (FileNotFoundException e) {
                    return null;
                }
            }).filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // System.out.println(get("/books"));
    }
}
