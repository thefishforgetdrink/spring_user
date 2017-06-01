package com.test.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.test.model.User;
import com.test.service.IUserService;
import com.test.util.Pager;
//只要属性名为loginUser那么那就是属于session的类型 就放到session中了
//@SessionAttributes("loginUser") model.addAttribute("loginUser",u)
@Controller
@RequestMapping("/user")
public class UserController {
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("_page",userService.find(new Pager()));
		return "user/list";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String list(Model model,Pager p){
		model.addAttribute("_page",userService.find(p));
		return "user/list";
	}
	
	//跳转到add页面
	//@ModelAttribute("user") User user  开启modeldriver的一种方法
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		//开启modelDriver
		model.addAttribute(new User());
		return "user/add";	//服务器端跳转
	}
	
	//添加用户  post请求  @RequestParam("attachs")MultipartFile[] attachs 因为使用了数组所有不会自动转换，必须使用@RequestParam
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user , BindingResult br,@RequestParam("attachs")MultipartFile[] attachs,HttpServletRequest req) throws IOException{//一定要紧跟validated之后，写验证结果类
		if(br.hasErrors()){
			//如果有错误显示add试图
			return "user/add";
		}
		
		//文件上传
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realpath);
		for(MultipartFile attach:attachs){
			if(attach.isEmpty()) continue;
			File f = new File(realpath+"/"+attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
		}
		
		userService.save(user);
		return "redirect:/user/users";	//客户端跳转
	}
	
	//@PathVariable 路径里面的值做参数  显示信息
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model){
		model.addAttribute(userService.findById(id));
		return "user/show";
	}
	
	//@PathVariable 路径里面的值做参数  显示信息
	@RequestMapping(value="/{id}",method=RequestMethod.GET,params="json")
	//传json数据
	@ResponseBody
	public User show(@PathVariable int id){
		//model.addAttribute(users.get(username));
		return userService.findById(id);
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		model.addAttribute(userService.findById(id));
		return "user/update";
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
	public String update(@PathVariable int id, @Validated User user , BindingResult br){
		if(br.hasErrors()){
			//如果有错误显示修改试图
			return "user/update";
		}
		userService.update(user);
		return "redirect:/user/users";	//客户端跳转
	}
	
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		User user = userService.findById(id);
		userService.delete(user);
		return "redirect:/user/users";	//客户端跳转
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username , String password , HttpSession session){
		User user = userService.login(username, password);
		session.setAttribute("loginUser", user);
		return "redirect:/user/users";	//客户端跳转	
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();//session失效
		return "redirect:/user/login";
	}
	
}
