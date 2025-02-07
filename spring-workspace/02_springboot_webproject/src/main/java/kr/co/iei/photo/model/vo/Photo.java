package kr.co.iei.photo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Photo {
	private int photoNo;
	private String photoWriter;
	private String photoTitle;
	private String photoContent;
	private String photoImg;
}
