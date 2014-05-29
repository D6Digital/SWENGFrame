/**
 * 
 */
package bookModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Lambert
 * @author Robert Mills
 *
 */
public class BookList {
	
	private static String version;
	private static ArrayList<Book> books;

	public BookList(String version) {
		BookList.version = version;
		books = new ArrayList<Book>();
	}

	/**
	 * @param adds current book
	 */
	public void addBook(Book currentBook) {
		books.add(currentBook);
		}

	/**
	 * @return books 
	 */
	public Book getBook(int i){
		return books.get(i);
	}

	/**
	 * @return version
	 */
	public static String getVersion() {
		return version;
	}	
	
	/**
	 * @return title list of books available
	 */
	public ArrayList getTitles(){
		ArrayList<String> titleList = new ArrayList<String>(0);
		int size = books.size();
		for (int i = 0; i < size ; i++){
			titleList.add(books.get(i).getTitle());
		}
		
		return titleList;
		
	}

	public static ArrayList<Book> getList() {
		// TODO Auto-generated method stub
		return books;
	}

	public void clearList() {
		books.clear();
		
	}
	
	

}
