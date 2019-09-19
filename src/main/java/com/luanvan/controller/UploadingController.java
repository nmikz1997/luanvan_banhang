package com.luanvan.controller;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanvan.dto.request.CreateProduct;

@RestController
@RequestMapping("upload")
public class UploadingController {

	public static final String uploadingDir = System.getProperty("user.dir") + "/";

//	@RequestMapping("/")
//	public String uploading(Model model) {
//		File file = new File(uploadingDir);
//		model.addAttribute("files", file.listFiles());
//		return "uploading";
//	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional
	public String uploadingPost(
			@RequestParam(value = "model") String product,
			@RequestParam(value = "file") MultipartFile fileupload
	) throws IOException {
		System.out.println(product);
		
		ObjectMapper mapper = new ObjectMapper();
		CreateProduct modelDTO = mapper.readValue(product, CreateProduct.class);
		System.out.println(modelDTO);
		File file = new File(uploadingDir + fileupload.getOriginalFilename());
		fileupload.transferTo(file);
		return "upload thành công";
	}
	

}
