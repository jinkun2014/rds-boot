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
    public String icon(@PathVariable("ui") String ui) {
        return "sys/sys-icon/sys-icon-" + ui;
    }

    @RequestMapping(value = "/sys/org/ui/{ui}.html", method = RequestMethod.GET)
    public String org(@PathVariable("ui") String ui) {
        return "sys/sys-org/sys-org-" + ui;
    }

    @RequestMapping(value = "/sys/resource/ui/{ui}.html", method = RequestMethod.GET)
    public String resource(@PathVariable("ui") String ui) {
        return "sys/sys-resource/sys-resource-" + ui;
    }

    @RequestMapping(value = "/sys/user/ui/{ui}.html", method = RequestMethod.GET)
    public String user(@PathVariable("ui") String ui) {
        return "sys/sys-user/sys-user-" + ui;
    }

    @RequestMapping(value = "/sys/role/ui/{ui}.html", method = RequestMethod.GET)
    public String role(@PathVariable("ui") String ui) {
        return "sys/sys-role/sys-role-" + ui;
    }

    @RequestMapping(value = "/sys/log/ui/{ui}.html", method = RequestMethod.GET)
    public String log(@PathVariable("ui") String ui) {
        return "sys/sys-log/sys-log-" + ui;
    }

}
