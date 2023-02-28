package bean;
import java.util.Map;

public class Message {

    private int id;         //用户编号
    private String title;    //用户名
    private String content;    //用户密码
    private String addtime;		//用户姓名
    private String bookname;         //用户性别
    private String addppid;            //用户年龄
    private String chapters;            //用户年龄
	private int bookId; //商品分类id
	private int lbt; 
	private Book book;
	
	public Message (){
		
	};


    public Message(String title, String content, String chapters, String addtime, String addppid, String bookname,int id,int bookId) {
    	super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.addtime = addtime;
		this.bookname = bookname;
		this.addppid = addppid;
		this.chapters = chapters;
		this.bookId = bookId;
    }

	public Message(Map<String,Object> map) {
    	id=(int) map.get("id");
		title=(String) map.get("title");
		content=(String) map.get("content");
		addtime=(String) map.get("addtime");
		bookname=(String) map.get("bookname");
		addppid=(String) map.get("addppid");
		chapters=(String) map.get("chapters");
		bookId=(int) map.get("bookId");
		this.lbt =  (int) map.get("lbt");
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddtime() {
		return addtime;
	}
	public Book getBook(Book book) {
		return book;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname =bookname;
	}

	public String getAddppid() {
		return addppid;
	}

	public void setAddppid(String addppid) {
		this.addppid = addppid;
	}
	public String getChapters() {
		return chapters;
	}

	public void setChapters(String chapters) {
		this.chapters = chapters;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", addtime='" + addtime + '\'' +
				", bookname='" + bookname + '\'' +
				", addppid='" + addppid + '\'' +
				", chapters='" + chapters + '\'' +
				", bookId='" + bookId + '\'' +
				'}';
	}

	public String getAddppname() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBookId() {
		// TODO Auto-generated method stub
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getLbt() {
		return lbt;
	}

	public void setLbt(int lbt) {
		this.lbt = lbt;
	}

}
