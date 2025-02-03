package kr.co.iei.notice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeFile {
	private int noticeFileNo;
	private int noticeNo;
	private String fileName;
	private String filePath;
}
