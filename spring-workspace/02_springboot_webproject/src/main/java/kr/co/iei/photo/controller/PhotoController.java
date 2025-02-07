package kr.co.iei.photo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.photo.model.service.PhotoService;
import kr.co.iei.photo.model.vo.Photo;
import kr.co.iei.util.FileUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping(value="/photo")
public class PhotoController {
	@Autowired
	private PhotoService photoService;
	@Value(value="${file.root}")
	private String root;
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping(value="/list")
	public String photoList(Model model) {
		int totalCount = photoService.selectTotalCount();
		model.addAttribute("totalCount", totalCount);
		return "photo/list";
	}
	
	@GetMapping(value="/writeFrm")
	public String writeFrm() {
		return "photo/writeFrm";
	}
	
	@PostMapping(value="/write")
	public String write(Photo p, MultipartFile imageFile) {
		if(!imageFile.isEmpty()) {
			String savepath = root + "/photo/";
			String filepath = fileUtils.upload(savepath, imageFile);
			p.setPhotoImg(filepath);
		}
		int result = photoService.insertPhoto(p);
		return "redirect:/photo/list";
	}
	
	@ResponseBody
	@GetMapping(value="/more")
	public List<Photo> more(int start, int amount) {
		List photoList = photoService.selectPhotoList(start,amount);
		return photoList;
	}
	
	
	
}
