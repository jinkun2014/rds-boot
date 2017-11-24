package me.jinkun.rds;

import me.jinkun.rds.core.support.web.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 图标-控制器
 *
 * @author JinKun
 * @date 2017-11-23
 * @time 17:43
 */
@Controller
public class PageController extends CommonController {

    @RequestMapping(value = "/sys/icon/ui/{ui}.html", method = RequestMethod.GET)
    public String ui(@PathVariable("ui") String ui) {
        return "sys/sys-icon/sys-icon-" + ui;
    }

}
