package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.Icon;
import me.jinkun.rds.sys.service.IIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 图标-控制器
 *
 * @author JinKun
 * @date 2017-11-23
 * @time 17:43
 */
@RestController
@RequestMapping("/sys/icon")
public class IconController extends CommonController {

    @Autowired
    IIconService iIconService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Icon icon) {
        boolean flag = iIconService.saveOrUpdate(icon);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, Icon icon) {
        icon.setId(id);
        boolean flag = iIconService.saveOrUpdate(icon);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String ids) {
        Set<Long> idSets = idsToSets(ids);
        boolean flag = iIconService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<Icon> iconOptional = iIconService.loadByPK(id, null);
        if (iconOptional.isPresent()) {
            return setJsonViewData(iconOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Icon icon) {
        int totalRecordCount = iIconService.loadCount(icon);
        IPage page = new SimplePage(pageNo, pageSize);
        Set<ISort> sortSet = Sets.newHashSet(new Sorter("id", false));
        List<Icon> iconList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iIconService.loads(icon, null, sortSet, page);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalRecordCount", totalRecordCount);
        resultMap.put("list", iconList);
        return setJsonViewData(resultMap);
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public Object load() {
        boolean flag = iIconService.load();
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

}
