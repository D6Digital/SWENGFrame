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
	
	private String version;
	private ArrayList<Book> books;

	/**
	 * create the booklist and setup a new array list of books
	 * @param version
	 */
	public BookList(String version) {
		this.version = version;
		books = new ArrayList<Book>();
		books.clear();
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
	public String getVersion() {
		return version;
	}	
	
	/**
	 * @return title list of books available
	 */
	public ArrayList<String> getTitles(){
		ArrayList<String> titleList = new ArrayList<String>(0);
		int size = books.size();
		for (int i = 0; i < size ; i++){
			titleList.add(books.get(i).getTitle());
		}
		
		return titleList;
		
	}

	/**
	 * @return the array of books
	 */
	public ArrayList<Book> getList() {
		// TODO Auto-generated method stub
		return books;
	}
	
	/**
	 * Clears the array of books
	 */
	public void clearBooks() {
		books.clear();
	}
	
	

}
