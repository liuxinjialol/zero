package net.guides.service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.guides.bean.entity.SysUser;
import net.guides.dto.SysUserCreateDTO;
import net.guides.dto.SysUserUpdateDTO;
import net.guides.dto.SysUserViewDTO;
import net.guides.repository.SysUserRepository;

@Service
public class SysUserService {

	@Autowired
	SysUserRepository repository;

	public Optional<SysUser> findById(String id) {
		return this.repository.findById(id);
	}

	@Transactional
	public SysUserViewDTO create(SysUserCreateDTO sysUser) {
		SysUser sysUserEntity = new SysUser();
		BeanUtils.copyProperties(sysUser, sysUserEntity);
		this.repository.save(sysUserEntity);
		SysUserViewDTO sysUserViewDTO = new SysUserViewDTO();
		BeanUtils.copyProperties(sysUserEntity, sysUserViewDTO);
		return sysUserViewDTO;
	}

	@Transactional
	SysUserViewDTO update(SysUserUpdateDTO sysUser){
		SysUser sysUserEntity = new SysUser();
		BeanUtils.copyProperties(sysUser, sysUserEntity);
		this.repository.save(sysUserEntity);
		SysUserViewDTO sysUserViewDTO = new SysUserViewDTO();
		BeanUtils.copyProperties(sysUserEntity, sysUserViewDTO);
		return sysUserViewDTO;
	}

	@Transactional
	public void delete(String id) {
		this.repository.deleteById(id);
	}

	// public Page<Employee> spage() {
	// 	Pageable pageable = PageRequest.of(1, 10);
	// 	return this.repository.findByPage("zhang", pageable);
	// }


}
