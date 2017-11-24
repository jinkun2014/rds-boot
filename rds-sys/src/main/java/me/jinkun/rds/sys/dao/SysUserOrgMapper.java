package me.jinkun.rds.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import me.jinkun.rds.sys.domain.SysUserOrg;
import me.jinkun.rds.sys.domain.SysUserOrgExample;


/**
 * @Description: 用户-机构,数据库表为： sys_user_org <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Mapper
public interface SysUserOrgMapper {
    long countByExample(SysUserOrgExample example);

    int deleteByExample(SysUserOrgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserOrg record);

    int insertSelective(SysUserOrg record);

    List<SysUserOrg> selectByExample(SysUserOrgExample example);

    List<SysUserOrg> selectPageByExample(SysUserOrgExample example);

    SysUserOrg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserOrg record, @Param("example") SysUserOrgExample example);

    int updateByExample(@Param("record") SysUserOrg record, @Param("example") SysUserOrgExample example);

    int updateByPrimaryKeySelective(SysUserOrg record);

    int updateByPrimaryKey(SysUserOrg record);
}