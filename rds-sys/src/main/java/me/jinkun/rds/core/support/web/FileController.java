package me.jinkun.rds.core.support.web;

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
@Controller
public class FileController {

    @Value("${temp.xls.dir}")
    String xlsPath;

    @RequestMapping(value = "/sys/file/download/{type}", method = RequestMethod.GET)
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
