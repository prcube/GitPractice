package DTO;

public class MoviesDTO {

	private int seq;
	private String title;
	private String genre;
	private String launch_date;



	public MoviesDTO(int seq, String title, String genre, String launch_date) {
		super();
		this.seq = seq;
		this.title = title;
		this.genre = genre;
		this.launch_date = launch_date;
	}
	
	public MoviesDTO() {
		
	}


	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getgenre() {
		return genre;
	}
	public void setgenre(String genre) {
		this.genre = genre;
	}
	public String getlaunch_date() {
		return launch_date;
	}
	public void setlaunch_date(String launch_date) {
		this.launch_date = launch_date;
	}



}
