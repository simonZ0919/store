package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;

public interface DistrictMapper {
	/**
	 * get child district by parent, state(86)->province->city->district
	 * @param parent
	 * @return list of childern 
	 */
	List<District> findByParent(String parent);
	
	/**
	 * get district by code
	 * @param code
	 * @return district in DB
	 */
	District findByCode(String code);
}
