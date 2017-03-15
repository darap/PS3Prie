package exceptions;

import pkgLibrary.Book;

public class BookException extends Exception{
	private Book ErrorBook;
	public BookException(Book b){
		ErrorBook= b;
	}
	public Book getErrorBook() {
		return ErrorBook;
	}
}
