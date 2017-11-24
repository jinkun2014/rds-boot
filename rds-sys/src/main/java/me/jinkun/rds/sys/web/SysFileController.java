package me.jinkun.rds.sys.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/31.
 */
@Api(description = "文件操作")
@Controller
public class SysFileController {

    @Value("${temp.xls.dir}")
    String xlsPath;

    @ApiOperation(value = "下载文件", notes = "下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "例如：xls,word,png", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "fileName", value = "例如：123456789", required = true, dataType = "String")
    })
    @RequestMapping(value = "/sys/files/download/{type}", method = RequestMethod.GET)
    @ResponseBody
    public void testDownload(@PathVariable("type") String type, String fileName, HttpServletResponse res) throws IOException {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");

        File file = new File(xlsPath, fileName);
        FileInputStream fis = new FileInputStream(file);

        res.setContentLengthLong(file.length());
        OutputStream os = res.getOutputStream();


        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        fis.close();
        os.close();
    }
}
