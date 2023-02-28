package bean;

import java.util.Map;

public class Book {

    private int bookId;          //分类编号

    private String bookName;     //分类名称

    private long bookSize;
    
    public Book() {
    }

    public Book(Map<String,Object> map) {
        this.bookId= (int) map.get("bookId");
        this.bookName= (String) map.get("bookName");
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    
	public long getBookSize() {
		return bookSize;
	}

	public void setBookSize(long bookSize) {
		this.bookSize = bookSize;
	}

	@Override
	public String toString() {
		return "book[bookId=" + bookId + ", bookName="
				+ bookName + "]";
	}

	public Book getBook() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
