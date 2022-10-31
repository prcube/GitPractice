package DTO;

public class MessagesDTO {
	private int seq;
	private String writer;
	private String messages;

	public MessagesDTO(int seq, String writer, String messages) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.messages = messages;
	}

	public MessagesDTO() {
		// TODO Auto-generated constructor stub
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
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}


}
