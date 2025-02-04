package kr.co.iei.board.model.vo;

import kr.co.iei.notice.model.vo.NoticeFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardFile {
	private int boardFileNo;
	private int boardNo;
	private String filename;
	private String filepath;
}
