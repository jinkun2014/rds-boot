package ${EntityInfo.packageInfo.web};

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import ${EntityInfo.packageInfo.entity}.${EntityInfo.entityName};
import ${EntityInfo.packageInfo.service}.${EntityInfo.serviceName};
import cn.com.header.wjdc.web.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 *
 * ${EntityInfo.remarks}-控制器
 * @author JinKun
 * @date ${EntityInfo.createTime}
 * @time ${EntityInfo.time}
 */
@RestController
@RequestMapping("/${EntityInfo.tableName ? replace("_","/")}")
public class ${EntityInfo.entityName}Controller extends CommonController {

    @Autowired
    ${EntityInfo.serviceName} ${EntityInfo.serviceName ? uncap_first };

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first }) {
        boolean flag = ${EntityInfo.serviceName ? uncap_first }.saveOrUpdate(${EntityInfo.entityName ? uncap_first });
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first }) {
        ${EntityInfo.entityName ? uncap_first }.setId(id);
        boolean flag = ${EntityInfo.serviceName ? uncap_first }.saveOrUpdate(${EntityInfo.entityName ? uncap_first });
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    /**
     * ids: 1,2,3或1
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String ids) {
        Set<Long> idSets = idsToSets(ids);
        boolean flag = ${EntityInfo.serviceName ? uncap_first }.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<${EntityInfo.entityName}> ${EntityInfo.entityName ? uncap_first }Optional = ${EntityInfo.serviceName ? uncap_first }.loadByPK(id, null);
        if (${EntityInfo.entityName ? uncap_first }Optional.isPresent()) {
            return setJsonViewData(${EntityInfo.entityName ? uncap_first }Optional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       ${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first }) {
        int totalRecordCount = ${EntityInfo.serviceName ? uncap_first }.loadCount(${EntityInfo.entityName ? uncap_first });
        List<${EntityInfo.entityName}> ${EntityInfo.entityName ? uncap_first }List = totalRecordCount == 0 ? Collections.EMPTY_LIST : ${EntityInfo.serviceName ? uncap_first }.loads(${EntityInfo.entityName ? uncap_first },null, Sets.newHashSet(new Sorter("id", false)), new SimplePage(pageNo, pageSize));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", ${EntityInfo.entityName ? uncap_first }List);
        return setJsonViewData(resultMap);
    }

}
