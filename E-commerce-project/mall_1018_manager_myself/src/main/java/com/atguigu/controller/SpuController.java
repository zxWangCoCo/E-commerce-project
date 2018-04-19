package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SupService;
import com.atguigu.util.MyFileUpload;

@Controller
public class SpuController {
	
	@Autowired
	private SupService spuService;
	
	
	@ResponseBody
	@RequestMapping("/get_spu_list")
	public Object get_spu_list(int pp_id,int flbh2) {
	List<T_MALL_PRODUCT> spu_list = spuService.get_spu_list(pp_id,flbh2);
		return spu_list;
	}
	
	@RequestMapping("/goto_spu_add")
	public String goto_spu_add(ModelMap map,T_MALL_PRODUCT spu) {
		map.put("spu", spu);
		return "spuAdd";
	}
		@RequestMapping("/spu_add")//如果使用ajax异步提交表单，必需用files[]接收
		public ModelAndView spu_add(T_MALL_PRODUCT spu,
				@RequestParam("files") MultipartFile[] files) throws Exception {
			//上传图片
			List<String> list_image = MyFileUpload.upload_image(files);
			//保存信息
			spuService.save_spu(spu , list_image);
			//将3个数据传到goto_spu_add页面
			ModelAndView mv = new ModelAndView("redirect:/goto_spu_add.do");
			mv.addObject("flbh1", spu.getFlbh1());
			mv.addObject("flbh2", spu.getFlbh2());
			mv.addObject("pp_id", spu.getPp_id());
			return mv;
		}
}
