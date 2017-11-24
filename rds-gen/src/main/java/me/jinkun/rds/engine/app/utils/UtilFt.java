package me.jinkun.rds.engine.app.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/12/1.
 */
public class UtilFt {
    /**
     * 获取模板
     *
     * @param templatesDir 例如"/templates"
     * @return
     */
    public Template getTemplate(String templatesDir, String name) {
        try {
            //通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration();
            //设定去哪里读取相应的ftl模板文件
            cfg.setClassForTemplateLoading(this.getClass(), templatesDir);
            cfg.setDefaultEncoding("UTF-8");
            //在模板文件目录中找到名称为name的文件
            Template temp = cfg.getTemplate(name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Description:  <br/>
     */
    public void generateFile(String templatesDir, String templateName, Map root, String outDir, String outFileName) {
        Writer out = null;
        try {
            //通过一个文件输出流，就可以写到相应的文件中
            //out = new FileWriter(new File(getOutDir, outFileName));
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(outDir, outFileName)), "UTF-8"));
            Template temp = this.getTemplate(templatesDir, templateName);
            temp.setEncoding("UTF-8");
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Description:  <br/>
     *
     * @param packageName me.jinkun.entity
     *                    Autor: Created by jinkun on 2017/2/25.
     */
    public void generateFile(String templatesDir, String templateName, Map<String, Object> root, String packageName, String outDir, String outFileName) {
        if (packageName != null && !"".equals(packageName)) {
            String packagePath = packageName.replaceAll("\\.", "/");
            outDir = outDir +"/"+ packagePath;
        }
        System.out.println(outDir);
        File file = new File(outDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        generateFile(templatesDir, templateName, root, outDir, outFileName);
    }
}
