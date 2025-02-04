package kr.co.iei.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileUtils {

	public String upload(String savepath, MultipartFile file) {
		//원본파일명 추출
		String filename = file.getOriginalFilename();
		String onlyFilename = filename.substring(0,filename.lastIndexOf("."));
		//원본파일명에서 제일 뒤에있는 .부터 끝까지를 문자열로 가져옴
		String extention = filename.substring(filename.lastIndexOf("."));
		//실제 업로드할 파일명을 저장할 변수
		String filepath = null;
		//파일명이 중복되면 증가시키면서 뒤에 붙일 변수
		int count = 0;
		//파일명이 겹치지 않을때까지 반복해서 수행
		while(true) {
			if(count == 0) {
				filepath = onlyFilename + extention;
			}else {
				filepath = onlyFilename + "_" + count + extention;
			}
			
			//위에서 만든 파일명이 사용중인지 체크
			File checkFile = new File(savepath+filepath);
			if(!checkFile.exists()) {
				break;
			}
			count++;
		}
		
		File uploadFile = new File(savepath+filepath);
		
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filepath;
	}

	public void downloadFile(String savepath, String filename, String filepath, HttpServletResponse response) {
		String downFile = savepath + filepath;
		
		
		try {
			//첨부파일을 현재 서버프로그램으로 읽어오기 위한 주스트림생성
			FileInputStream fis = new FileInputStream(downFile);
			//속도개선을 위한 보조스트림 생성
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			//읽어온 파일을 사용자에게 내보낼 주 스트림 생성 -> response 객체 내부에 존재
			ServletOutputStream sos = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			
			String resFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
			
			while (true) {
				int read = bis.read();
				if(read != -1) {
					bos.write(read);
				}else {
					break;
				}
			}
			bos.close();
			bis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
