package me.jinkun.rds.sys.mapper;

import me.jinkun.rds.sys.entity.Icon;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Date;
import java.util.*;


/**
 *
 * 图标-Dao接口
 * @author JinKun
 * @date 2017-11-25
 * @time 09:57
 */
@Mapper
@Repository
public interface IIconMapper {

    /**
     * 新增图标
     * @param icon
     */
    int insert(@Param("icon") Icon icon);

    /**
     * 编辑图标
     * @param icon
     */
    int update(@Param("icon") Icon icon);

    /**
     * 删除图标
     * @param icon
     */
    int delete(@Param("icon") Icon icon);

    /**
     * 根据ID批量删除
     * @param ids
     */
    int deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 查询图标记录数
     * @param icon
     * @return
     */
    int loadCount(@Param("icon") Icon icon);

    /**
     * 根据id查询图标
     * @param id 图标id
     * @param fields 查询属性
     * @return
     */
    Icon loadByPK(
            @Param("id") Long id,
            @Param("fields") Set<String> fields
    );

    /**
     * 查询图标
     * @param icon 图标
     * @param fields 查询属性
     * @param sortSet 排序器
     * @param page 分页
     * @return
     */
    List<Icon> loads(
            @Param("icon") Icon icon,
            @Param("fields") Set<String> fields,
            @Param("sorts") Set<ISort> sortSet,
            @Param("page") IPage page
    );
}