package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

public interface IDistrictService {
	/**
	 * get child district by parent, state(86)->province->city->district
	 * @param parent
	 * @return list of childern 
	 */
	List<District> getListByParent(String parent);
	
	/**
	 * get district by code
	 * @param code
	 * @return district in DB
	 */
	District getByCode(String code);
}
