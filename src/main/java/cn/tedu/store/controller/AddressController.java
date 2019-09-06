package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.IAddressService;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
	@Autowired private IAddressService service;

	@PostMapping("/create")
	public ResponseResult<Void> handleCreate
		(Address address, HttpSession session){
		// get username and uid from session 
		String username=session.getAttribute("username").toString();
		Integer uid=getIdFromSession(session);
		
		address.setUid(uid);
		service.create(username, address);
		return new ResponseResult<Void>(SUCCESS);
		
	}
	
	@RequestMapping("/list")
	public ResponseResult<List<Address>> getListByUid(HttpSession session){
		// get id
		Integer uid=getIdFromSession(session);
		// get list of address
		List<Address> list=service.getListByUid(uid);
		return new ResponseResult<List<Address>>(SUCCESS,list);
	}
}
