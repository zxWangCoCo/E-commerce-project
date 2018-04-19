package com.atguigu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class MyFileUpload {

	public static List<String> upload_image(MultipartFile[] files) throws Exception {
	String path = MyPropertyUtil.getPropert("myUpload.properties" , "windows_path");
	
	List<String> list = new ArrayList<String>();
	
	for (int i = 0; i < files.length; i++) {
		if (!files[i].isEmpty()) {
			String filename = files[i].getOriginalFilename();
			//获取UUID
			String uuid = UUID.randomUUID().toString().substring(0, 10);
			//拼串
			String upload_name = path+"/"+uuid+filename;
			//上传
			files[i].transferTo(new File(upload_name));
			//将文件名添加到集合
			list.add(filename);
		}
	}
		return list;
	}

}
