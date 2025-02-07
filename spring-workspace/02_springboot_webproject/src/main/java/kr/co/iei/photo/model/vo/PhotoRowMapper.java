package kr.co.iei.photo.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoRowMapper implements RowMapper<Photo>{

	@Override
	public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Photo photo = new Photo();
		photo.setPhotoContent(rs.getString("photo_content"));
		photo.setPhotoImg(rs.getString("photo_img"));
		photo.setPhotoNo(rs.getInt("photo_no"));
		photo.setPhotoTitle(rs.getString("photo_title"));
		photo.setPhotoWriter(rs.getString("photo_writer"));
		return photo;
	}
	
}
