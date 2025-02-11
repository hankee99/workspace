package kr.co.iei.notice.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private int readCount;
	private String regDate;
	private List<NoticeFile> fileList;
	private List<NoticeComment> commentList;
	private List<NoticeComment> reCommentList;
	
//	public String getNoticeContentBr() {
//		return noticeContent.replaceAll("\n", "<br>");
//	}
}
