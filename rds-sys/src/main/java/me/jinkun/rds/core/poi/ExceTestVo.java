package me.jinkun.rds.core.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/31.
 */
public class ExceTestVo {
    @ExcelAttribute(columnName = "姓名", column = "A")
    private String name;
    @ExcelAttribute(columnName = "密码", column = "B")
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static void main(String[] args) throws Exception {
//        export();
        imp();
    }

    private static void imp() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:\\export.xls");
            // 创建excel工具类
            ExcelUtil<ExceTestVo> util = new ExcelUtil<ExceTestVo>(ExceTestVo.class);
            List<ExceTestVo> list = util.importExcel("学生信息", fis);// 导入

            for (ExceTestVo vo : list) {
                System.out.println("name:" + vo.getName() + ",pass=" + vo.getPass());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void export() {
        // 初始化数据
        List<ExceTestVo> list = new ArrayList<ExceTestVo>();

        ExceTestVo vo = new ExceTestVo();
        vo.setName("Hello1");
        vo.setPass("pass1");
        list.add(vo);

        ExceTestVo vo2 = new ExceTestVo();
        vo2.setName("Hello2");
        vo2.setPass("123");
        list.add(vo2);

        ExceTestVo vo3 = new ExceTestVo();
        vo3.setName("Hello3");
        vo3.setPass("123");
        list.add(vo3);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\export.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelUtil<ExceTestVo> util = new ExcelUtil<ExceTestVo>(ExceTestVo.class);// 创建工具类.
        util.exportExcel(list, "学生信息", out);// 导出

    }
}
