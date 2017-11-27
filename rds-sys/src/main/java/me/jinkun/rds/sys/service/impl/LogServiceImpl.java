package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.IPage;
import me.jinkun.rds.core.poi.ExcelUtil;
import me.jinkun.rds.core.sort.ISort;
import me.jinkun.rds.core.utils.UtilFile;
import me.jinkun.rds.sys.entity.Log;
import me.jinkun.rds.sys.mapper.ILogMapper;
import me.jinkun.rds.sys.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 日志-业务实现
 *
 * @author JinKun
 * @date 2017-11-27
 * @time 20:27
 */
@Service
public class LogServiceImpl implements ILogService {

    @Value("${temp.xls.dir}")
    String xlsPath;

    @Autowired
    ILogMapper iLogMapper;

    @Override
    public Optional<Log> loadByPK(Long id, Set<String> fields) {
        Log log = iLogMapper.loadByPK(id, fields);
        if (Objects.isNull(log)) {
            return Optional.empty();
        }
        return Optional.of(log);
    }

    @Override
    public List<Log> loads(Log log, Set<String> fields, Set<ISort> sortSet, IPage page) {
        return iLogMapper.loads(log, fields, sortSet, page);
    }

    @Override
    public int loadCount(Log log) {
        return iLogMapper.loadCount(log);
    }

    @Override
    public boolean saveOrUpdate(Log log) {
        boolean save = Objects.isNull(log.getId());
        if (save) {
            return iLogMapper.insert(log) > 0;
        }
        return iLogMapper.update(log) > 0;
    }

    @Override
    public boolean deleteByIds(Set<Long> ids) {
        return iLogMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean importXls(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                ExcelUtil<Log> excelUtil = new ExcelUtil<>(Log.class);
                List<Log> logList = excelUtil.importExcel("日志表", file.getInputStream());
                for (Log log : logList) {
                    saveOrUpdate(log);
                }
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String exportXls(Set<Long> ids) {
       List<Log> logList =  iLogMapper.loadByIds(null,ids);
        //文件名
        String fileName = System.currentTimeMillis() + "";

        //生成xls
        OutputStream out = UtilFile.getOutputStream(xlsPath, fileName);
        ExcelUtil<Log> excelUtil = new ExcelUtil<>(Log.class);
        excelUtil.exportExcel(logList, "日志表", out);
        return fileName;
    }
}