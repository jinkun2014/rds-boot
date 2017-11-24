package me.jinkun.rds.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import me.jinkun.rds.sys.domain.SysIcon;
import me.jinkun.rds.sys.domain.SysIconExample;


/**
 * @Description: 图标,数据库表为： sys_icon <br/>
 * @Autor: Created by Jin Kun on 2017-06-01.
 */
@Mapper
public interface SysIconMapper {
    long countByExample(SysIconExample example);

    int deleteByExample(SysIconExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysIcon record);

    int insertSelective(SysIcon record);

    List<SysIcon> selectByExample(SysIconExample example);

    List<SysIcon> selectPageByExample(SysIconExample example);

    SysIcon selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysIcon record, @Param("example") SysIconExample example);

    int updateByExample(@Param("record") SysIcon record, @Param("example") SysIconExample example);

    int updateByPrimaryKeySelective(SysIcon record);

    int updateByPrimaryKey(SysIcon record);
}