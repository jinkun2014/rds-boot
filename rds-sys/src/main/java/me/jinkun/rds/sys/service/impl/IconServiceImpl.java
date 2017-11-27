package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.support.exception.ServiceException;
import me.jinkun.rds.sys.entity.Icon;
import me.jinkun.rds.sys.mapper.IIconMapper;
import me.jinkun.rds.sys.service.IIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.*;

/**
 * 图标-业务实现
 *
 * @author JinKun
 * @date 2017-11-23
 * @time 20:29
 */
@Service
public class IconServiceImpl implements IIconService {

    @Autowired
    IIconMapper iIconMapper;

    @Override
    public Optional<Icon> loadByPK(Long id, Set<String> fields) {
        Icon icon = iIconMapper.loadByPK(id, fields);
        if (Objects.isNull(icon)) {
            return Optional.empty();
        }
        return Optional.of(icon);
    }

    @Override
    public List<Icon> loads(Icon icon, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iIconMapper.loads(icon, fields, sortSet, page);
    }

    @Override
    public int loadCount(Icon icon) {
        return iIconMapper.loadCount(icon);
    }

    @Override
    public boolean saveOrUpdate(Icon icon) {
        boolean save = Objects.isNull(icon.getId());
        if (save) {
            return iIconMapper.insert(icon) > 0;
        }
        return iIconMapper.update(icon) > 0;
    }

    @Override
    public boolean deleteByIds(Set<Long> ids) {
        return iIconMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean load() {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File images = new File(path.getAbsolutePath(), "static/images/32x32");
            Date now = new Date();
            for (String name : images.list()) {
                Icon icon = new Icon();
                icon.setName(name);
                icon.setType("32x32");
                icon.setUrl("/images/32x32/" + name);
                icon.setDelMark(false);
                icon.setUpdateTime(now);
                icon.setCreateTime(now);
                iIconMapper.insert(icon);
            }
        } catch (Exception e) {
            throw new ServiceException("初始化图标错误", e);
        }
        return true;
    }
}