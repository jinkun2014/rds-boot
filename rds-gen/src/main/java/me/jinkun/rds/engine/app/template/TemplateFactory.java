package me.jinkun.rds.engine.app.template;

import me.jinkun.rds.engine.app.template.impl.*;

/**
 * @author JinKun
 * @date 2017-11-14
 * @time 10:34
 */
public class TemplateFactory {
    public static Template getTemplate(String template) {
        Template tmp = null;
        if ("BOOT".equals(template)) {
            tmp = new BootTemplate();
        }
        if ("SQLSERVER".equals(template)) {
            tmp = new SqlTemplate();
        }
        if ("ssm-mysql-v1".equals(template)) {
            tmp = new SsmMysqlV1Template();
        }
        if ("ssm-wjdc".equals(template)) {
            tmp = new SsmWjdcTemplate();
        }
        if ("ssm-rds".equals(template)) {
            tmp = new SsmRdsTemplate();
        }

        if (template == null) {
            throw new RuntimeException("请配置正确的模版");
        }
        return tmp;
    }
}
