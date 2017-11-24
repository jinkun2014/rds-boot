package me.jinkun.rds.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import me.jinkun.rds.sys.domain.SysRole;
import me.jinkun.rds.sys.domain.SysRoleExample;
import java.util.Date;
import java.util.Date;


/**
 * @Description: 角色,数据库表为： sys_role <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Mapper
public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    List<SysRole> selectPageByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}