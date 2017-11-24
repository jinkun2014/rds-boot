package me.jinkun.rds.sys.dao;

import me.jinkun.rds.sys.domain.SysResource;
import me.jinkun.rds.sys.domain.SysResourceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description: 资源, 数据库表为： sys_resource <br/>
 * @Autor: Created by Jin Kun on 2017-05-25.
 */
@Mapper
public interface SysResourceMapper {
    long countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    List<SysResource> selectByExample(SysResourceExample example);

    List<SysResource> selectPageByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

    //自定义SQL见XML
    List<SysResource> selectByUserIdAndPid(@Param("userId") Long userId, @Param("pid") Long pid);
}