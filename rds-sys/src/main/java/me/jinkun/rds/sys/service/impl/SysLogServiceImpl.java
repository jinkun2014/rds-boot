package me.jinkun.rds.sys.service.impl;

import me.jinkun.rds.core.page.PageResponse;
import me.jinkun.rds.core.page.PageRequest;
import me.jinkun.rds.core.poi.ExcelUtil;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.utils.UtilFile;
import me.jinkun.rds.sys.convert.SysLogConvert;
import me.jinkun.rds.sys.dao.SysLogMapper;
import me.jinkun.rds.sys.domain.SysLog;
import me.jinkun.rds.sys.domain.SysLogExample;
import me.jinkun.rds.sys.service.SysLogService;
import me.jinkun.rds.sys.web.form.SysLogForm;
import me.jinkun.rds.sys.web.result.SysLogResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @Description: 日志, 数据库表为： sys_log<br/>
 * @Autor: Created by Jin Kun on 2017-05-27.
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Value("${temp.xls.dir}")
    String xlsPath;

    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public RespResult<SysLogResult> save(SysLogForm form) {
        if (form.getId() == null) {
            return add(form);
        } else {
            return update(form.getId(), form);
        }
    }

    @Override
    public RespResult<SysLogResult> add(SysLogForm form) {
        SysLog entity = SysLogConvert.formToEntity(form);
        entity.setId(null);
        sysLogMapper.insertSelective(entity);
        return RespResult.ok("保存成功");
    }

    @Override
    public RespResult<SysLogResult> update(Long id, SysLogForm form) {
        SysLog entity = SysLogConvert.formToEntity(form);
        entity.setId(id);
        sysLogMapper.updateByPrimaryKey(entity);
        return RespResult.ok("更新成功", form);
    }

    public RespResult<SysLogResult> delete(Long id) {
        sysLogMapper.deleteByPrimaryKey(id);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysLogResult> deleteByIds(String ids) {
        List<Long> idList = idsToList(ids);
        SysLogExample example = new SysLogExample();
        example.createCriteria().andIdIn(idList);
        sysLogMapper.deleteByExample(example);
        return RespResult.ok("删除成功");
    }

    @Override
    public RespResult<SysLogResult> get(Long id) {
        SysLog sysLog = sysLogMapper.selectByPrimaryKey(id);
        return RespResult.ok("查询成功", SysLogConvert.entityToResult(sysLog));
    }

    @Override
    public RespResult<PageResponse<SysLogResult>> listPage(PageRequest pageRequest, SysLogForm form) {
        SysLogExample example = new SysLogExample();
        //设置分页
        example.setStart((pageRequest.getPage() - 1) * pageRequest.getSize());
        example.setSize(pageRequest.getSize());
        example.setOrderByClause("time DESC");

        //查询条件
        if (form != null) {
            SysLogExample.Criteria criteria = example.createCriteria();

            //其它条件

        }

        //查询总记录
        long count = sysLogMapper.countByExample(example);
        //查询分页列表
        List<SysLog> sysLogList = sysLogMapper.selectPageByExample(example);

        List<SysLogResult> result = SysLogConvert.entityListToResultList(sysLogList);

        //返回结果
        return RespResult.ok("查询成功", new PageResponse<>(pageRequest.getPage(), pageRequest.getSize(), count, result));
    }

    @Override
    public RespResult<String> exportXls(String ids) {
        List<Long> idList = idsToList(ids);
        SysLogExample example = new SysLogExample();
        example.createCriteria().andIdIn(idList);
        List<SysLog> sysLogList = sysLogMapper.selectByExample(example);

        //文件名
        String fileName = new Date().getTime() + "";

        //生成xls
        OutputStream out = UtilFile.getOutputStream(xlsPath, fileName);
        ExcelUtil<SysLogResult> excelUtil = new ExcelUtil<>(SysLogResult.class);
        excelUtil.exportExcel(SysLogConvert.entityListToResultList(sysLogList), "日志表", out);

        return RespResult.ok("导出成功", fileName);
    }

    @Override
    public RespResult<String> importXls(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                ExcelUtil<SysLogForm> excelUtil = new ExcelUtil<>(SysLogForm.class);
                List<SysLogForm> formList = excelUtil.importExcel("日志表", file.getInputStream());
                for (SysLogForm form : formList) {
                    sysLogMapper.insertSelective(SysLogConvert.formToEntity(form));
                }
                return RespResult.ok("导入成功");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return RespResult.fail("导入失败");
    }

    @Override
    public RespResult<Map<String, Object>> pv() {
        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();

        List<Map<String, Object>> maps = sysLogMapper.selectPvMap();
        for (Map<String, Object> map : maps) {
            xList.add(map.get("moudle").toString());
            yList.add(map.get("num").toString());
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("xList", xList);
        resultMap.put("yList", yList);

        return RespResult.ok("统计成功", resultMap);
    }

    private List<Long> idsToList(String ids) {
        String[] id = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            idList.add(Long.parseLong(id[i]));
        }
        return idList;
    }
}