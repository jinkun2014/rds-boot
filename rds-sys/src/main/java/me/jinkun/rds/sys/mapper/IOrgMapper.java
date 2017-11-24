package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.sys.entity.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 *
 * 组织机构-Dao接口
 * @author JinKun
 * @date 2017-11-24
 * @time 13:56
 */
@Mapper
@Repository
public interface IOrgMapper {

    /**
     * 新增组织机构
     * @param org
     */
    int insert(@Param("org") Org org);

    /**
     * 编辑组织机构
     * @param org
     */
    int update(@Param("org") Org org);

    /**
     * 删除组织机构
     * @param org
     */
    int delete(@Param("org") Org org);

    /**
     * 根据ID批量删除
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询组织机构记录数
     * @param org
     * @return
     */
    int loadCount(@Param("org") Org org);

    /**
     * 根据id查询组织机构
     * @param id 组织机构id
     * @param fields 查询属性
     * @return
     */
    Org loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询组织机构
     * @param org 组织机构
     * @param fields 查询属性
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Org> loads(
            @Param("org") Org org,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );
}