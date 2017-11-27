package me.jinkun.rds.sys.controller;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.page.SimplePage;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.sys.entity.Log;
import me.jinkun.rds.sys.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


/**
 * 日志-控制器
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 20:27
 */
@RestController
@RequestMapping("/sys/log")
public class LogController extends CommonController {

    @Autowired
    ILogService iLogService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Log log) {
        boolean flag = iLogService.saveOrUpdate(log);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, Log log) {
        log.setId(id);
        boolean flag = iLogService.saveOrUpdate(log);
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
        boolean flag = iLogService.deleteByIds(idSets);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        Optional<Log> logOptional = iLogService.loadByPK(id, null);
        if (logOptional.isPresent()) {
            return setJsonViewData(logOptional.get());
        }
        return setJsonViewData(ResultCode.NO_EXISTS);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       Log log) {
        int totalRecordCount = iLogService.loadCount(log);
        List<Log> logList = totalRecordCount == 0 ? Collections.EMPTY_LIST : iLogService.loads(log, null, Sets.newHashSet(new Sorter("id", false)), new SimplePage(pageNo, pageSize));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", totalRecordCount);
        resultMap.put("list", logList);
        return setJsonViewData(resultMap);
    }


    @RequestMapping(value = "/exportXls", method = RequestMethod.POST)
    public Object exportXls(String ids) {
        Set<Long> idList = idsToSets(ids);
        String fileName = iLogService.exportXls(idList);
        if (Objects.nonNull(fileName)) {
            return setJsonViewData(fileName);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }

    @RequestMapping(value = "/importXls", method = RequestMethod.POST)
    public Object importXls(@RequestParam("file") MultipartFile file) {
        boolean flag = iLogService.importXls(file);
        if (flag) {
            return setJsonViewData(ResultCode.SUCCESS);
        }
        return setJsonViewData(ResultCode.SYSTEM_ERROR);
    }
}
