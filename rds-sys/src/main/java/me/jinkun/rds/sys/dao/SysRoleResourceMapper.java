package me.jinkun.rds.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import me.jinkun.rds.sys.domain.SysRoleResource;
import me.jinkun.rds.sys.domain.SysRoleResourceExample;


/**
 * @Description: 角色-资源,数据库表为： sys_role_resource <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Mapper
public interface SysRoleResourceMapper {
    long countByExample(SysRoleResourceExample example);

    int deleteByExample(SysRoleResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleResource record);

    int insertSelective(SysRoleResource record);

    List<SysRoleResource> selectByExample(SysRoleResourceExample example);

    List<SysRoleResource> selectPageByExample(SysRoleResourceExample example);

    SysRoleResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRoleResource record, @Param("example") SysRoleResourceExample example);

    int updateByExample(@Param("record") SysRoleResource record, @Param("example") SysRoleResourceExample example);

    int updateByPrimaryKeySelective(SysRoleResource record);

    int updateByPrimaryKey(SysRoleResource record);
}