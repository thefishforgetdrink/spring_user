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
//ֻҪ������ΪloginUser��ô�Ǿ�������session������ �ͷŵ�session����
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
	
	//��ת��addҳ��
	//@ModelAttribute("user") User user  ����modeldriver��һ�ַ���
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		//����modelDriver
		model.addAttribute(new User());
		return "user/add";	//����������ת
	}
	
	//����û�  post����  @RequestParam("attachs")MultipartFile[] attachs ��Ϊʹ�����������в����Զ�ת��������ʹ��@RequestParam
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user , BindingResult br,@RequestParam("attachs")MultipartFile[] attachs,HttpServletRequest req) throws IOException{//һ��Ҫ����validated֮��д��֤�����
		if(br.hasErrors()){
			//����д�����ʾadd��ͼ
			return "user/add";
		}
		
		//�ļ��ϴ�
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realpath);
		for(MultipartFile attach:attachs){
			if(attach.isEmpty()) continue;
			File f = new File(realpath+"/"+attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
		}
		
		userService.save(user);
		return "redirect:/user/users";	//�ͻ�����ת
	}
	
	//@PathVariable ·�������ֵ������  ��ʾ��Ϣ
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model){
		model.addAttribute(userService.findById(id));
		return "user/show";
	}
	
	//@PathVariable ·�������ֵ������  ��ʾ��Ϣ
	@RequestMapping(value="/{id}",method=RequestMethod.GET,params="json")
	//��json����
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
			//����д�����ʾ�޸���ͼ
			return "user/update";
		}
		userService.update(user);
		return "redirect:/user/users";	//�ͻ�����ת
	}
	
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		User user = userService.findById(id);
		userService.delete(user);
		return "redirect:/user/users";	//�ͻ�����ת
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username , String password , HttpSession session){
		User user = userService.login(username, password);
		session.setAttribute("loginUser", user);
		return "redirect:/user/users";	//�ͻ�����ת	
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();//sessionʧЧ
		return "redirect:/user/login";
	}
	
}
