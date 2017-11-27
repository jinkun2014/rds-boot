package me.jinkun.rds;

import me.jinkun.rds.authorization.annotation.Authorization;
import me.jinkun.rds.config.Constants;
import me.jinkun.rds.core.support.web.CommonController;
import me.jinkun.rds.core.support.web.ResultCode;
import me.jinkun.rds.core.utils.UtilDate;
import me.jinkun.rds.sys.entity.Resource;
import me.jinkun.rds.sys.entity.User;
import me.jinkun.rds.sys.service.IResourceService;
import me.jinkun.rds.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/9.
 */
@Controller
public class IndexController extends CommonController {

    @Autowired
    IUserService iUserService;
    @Autowired
    IResourceService iResourceService;

    @Authorization
    @RequestMapping(value = {
            "/",
            "/index.html"
    }, method = RequestMethod.GET)
    public String index(ModelMap map, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
        map.put("user", user.getName());
        map.put("time", UtilDate.formDate(new Date(), "yyyy/MM/dd"));
        return "index";
    }

    @RequestMapping(value = {
            "/api"
    }, method = RequestMethod.GET)
    public String doc(ModelMap map) {
        return "api";
    }

    @RequestMapping(value = {
            "/login.html"
    }, method = RequestMethod.GET)
    public String loginui(ModelMap map) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(User user, HttpSession session) {
        User u = iUserService.findByLoginNameAndPassword(user.getLoginName(), user.getPassword());
        if (u == null) {
            return setJsonViewData(ResultCode.NO_EXISTS);
        }
        u.setPassword(null);
        session.setAttribute(Constants.SESSION_USER_KEY, u);
        return setJsonViewData("/");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout(HttpSession session) {
        session.removeAttribute(Constants.SESSION_USER_KEY);
        return setJsonViewData(ResultCode.SUCCESS);
    }

    @Authorization
    @RequestMapping(value = "/menu/{id}/children", method = RequestMethod.GET)
    @ResponseBody
    public Object topMenu(@PathVariable("id") Long pid, HttpSession session) {
        pid = pid==-1?null:pid;
        User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
        List<Resource> resourceList = iResourceService.getByUserIdAndPid(user.getId(), pid);
        return setJsonViewData(resourceList);
    }

}
