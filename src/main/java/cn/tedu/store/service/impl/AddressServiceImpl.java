package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.exception.AccessDeniedException;
import cn.tedu.store.exception.AddressNotFoundException;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.UpdateException;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;

@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired private AddressMapper mapper;
	@Autowired private IDistrictService dService;

	@Override
	public Address create(String username, Address address) throws InsertException {
		Integer count=countAddress(address.getUid());
		// setdefault if first created
		address.setIsDefault(count==0?1:0);
		// set modified user/time
		address.setModifiedUser(username);
		address.setCreatedUser(username);
		address.setCreatedTime(new Date());
		address.setModifiedTime(new Date());
		//covert to district from code
		String district=getDistrict(address.getProvince(), 
				address.getCity(), address.getArea());
		address.setDistrict(district);
		
		// insert address
		addNew(address);
		return address;
	}
	
	// for transaction
	@Override
	@Transactional
	public void setDefault(Integer uid, Integer id) {
		Address data=findById(id);
		if (data==null) {
			throw new AddressNotFoundException("address not found");
		}
		
		if (data.getUid()!=uid) {
			throw new AccessDeniedException("fail to verify data");
		}
		updateNonDefault(uid);
		updateDefault(id);
	}
	
	@Override
	public List<Address> getListByUid(Integer uid) {
		return findByUid(uid);
	}
	
	private void addNew(Address address) {
		Integer rows=mapper.addNew(address);
		if(rows!=1) {
			throw new InsertException("fail to add address");
		}
	}
	
	private void updateNonDefault(Integer uid) {
		Integer rows=mapper.updateNonDefault(uid);
		if(rows<1) {
			throw new UpdateException("fail to reset default");
		}
	}
	
	private void updateDefault(Integer id) {
		Integer rows=mapper.updateDefault(id);
		if(rows!=1) {
			throw new UpdateException("fail to set default");
		}
	}
	
	
	private Integer countAddress(Integer uid) {
		return mapper.countAddress(uid);
	}
	
	/**
	 * get string by code of province/city/area
	 * @param province
	 * @param city
	 * @param area
	 * @return
	 */
	private String getDistrict(String province, String city, String area) {
		District p=dService.getByCode(province);
		District c=dService.getByCode(city);
		District a=dService.getByCode(area);
		
		province=p!=null?p.getName():null;
		city=c!=null?c.getName():null;
		area=a!=null?a.getName():null;
		return province+","+city+","+area;
	}
	
	private List<Address> findByUid(Integer uid) {
		return mapper.findByUid(uid);
	}
	
	private Address findById(Integer id){
		return mapper.findById(id);
	}

}
