package me.jinkun.rds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/9.
 */
@Controller
public class IndexController {
    @Value("${default.db.dialect}")
    String dialect;

    @Value("${default.db.url}")
    String url;

    @Value("${default.db.username}")
    String username;

    @Value("${default.db.password}")
    String password;

    @Value("${default.basePackage}")
    String basePackage;

    @Value("${default.moudle}")
    String moudle;

    @Value("${default.user}")
    String user;

    @Value("${default.template}")
    String template;

    @RequestMapping("/")
    public String index(ModelMap map) {
        return "index";
    }

    @RequestMapping("/set-db.html")
    public String setDb(ModelMap map) {
        map.addAttribute("dialect", dialect);
        map.addAttribute("url", url);
        map.addAttribute("username", username);
        map.addAttribute("password", password);
        return "set-db";
    }

    @RequestMapping("/set-gen.html")
    public String setGen(ModelMap map) {
        map.addAttribute("base", basePackage);
        map.addAttribute("moudle", moudle);
        map.addAttribute("user", user);
        map.addAttribute("template", template);
        return "set-gen";
    }
}
