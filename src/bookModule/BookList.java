/**
 * 
 */
package bookModule;

import java.util.ArrayList;

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

	public void addBook(Book currentBook) {
		books.add(currentBook);
		}

	public Book getBook(int i){
		return books.get(i);
	}

	public String getVersion() {
		return version;
	}	
	
	public ArrayList getTitles(){
		ArrayList<String> titleList = new ArrayList<String>(0);
		int size = books.size();
		for (int i = 0; i < size ; i++){
			titleList.add(books.get(i).getTitle());
		}
		
		return titleList;
		
	}
	
	

}
