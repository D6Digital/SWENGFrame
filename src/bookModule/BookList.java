/**
 * 
 */
package bookModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Lambert
 *
 */
public class BookList {
	
	private static String version;
	private static List<Book> books;

	public BookList(String version) {
		BookList.version = version;
		books = new ArrayList<Book>();
	}

	public void addBook(Book currentBook) {
		books.add(currentBook);
		}

	public static List<Book> getList() {
		return books;
	}

	public static String getVersion() {
		return version;
	}	

}
