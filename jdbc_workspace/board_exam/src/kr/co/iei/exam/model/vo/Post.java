package kr.co.iei.exam.model.vo;

import java.sql.Date;

public class Post {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String memberName;
	private int readCount;
	private Date writeDate;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(int boardNo, String boardTitle, String boardContent, String memberName, int readCount, Date writeDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberName = memberName;
		this.readCount = readCount;
		this.writeDate = writeDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	@Override
	public String toString() {
		return boardNo + "\t" + boardTitle +
				"\t" + memberName + "\t" + readCount + "\t" + writeDate;
	}
	
}
