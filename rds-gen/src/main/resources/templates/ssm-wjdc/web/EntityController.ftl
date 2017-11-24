package ${EntityInfo.packageInfo.web};

import cn.com.header.core.IPage;
import cn.com.header.core.ISort;
import cn.com.header.core.page.SimplePage;
import cn.com.header.core.sort.Sorter;
import cn.com.header.core.support.web.JsonViewData;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,${EntityInfo.entityName} ${EntityInfo.entityName ? uncap_first }) {
        int totalRecordCount = ${EntityInfo.serviceName ? uncap_first }.loadCount(${EntityInfo.entityName ? uncap_first });
        IPage page = new SimplePage(pageNo, pageSize);
        Set<ISort> sortSet = Sets.newHashSet(new Sorter("id", false));
        List<${EntityInfo.entityName}> ${EntityInfo.entityName ? uncap_first }List = totalRecordCount == 0 ? Collections.EMPTY_LIST : ${EntityInfo.serviceName ? uncap_first }.loads(${EntityInfo.entityName ? uncap_first },null, sortSet, page);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalRecordCount", totalRecordCount);
        resultMap.put("${EntityInfo.entityName ? uncap_first }List", ${EntityInfo.entityName ? uncap_first }List);
        return setJsonViewData(resultMap);
    }

}
