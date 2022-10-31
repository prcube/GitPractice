package DTO;

public class CommentsDTO {
	
	private int seq;
	private String writer;
	private String contents;
	private String write_date;
	private int parentseq;
	
	
	
	
	
	public CommentsDTO() {
		super();
	}
	
	
	public CommentsDTO(int seq, String writer, String contents, String write_date, int parentseq) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.write_date = write_date;
		this.parentseq = parentseq;
	}
	public CommentsDTO(String contents2) {
		this.contents = contents2;
	}


	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public int getParentseq() {
		return parentseq;
	}
	public void setParentseq(int parentseq) {
		this.parentseq = parentseq;
	}

	
	
	
}
