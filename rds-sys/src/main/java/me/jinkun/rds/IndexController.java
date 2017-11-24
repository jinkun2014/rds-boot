package me.jinkun.rds;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.jinkun.rds.authorization.annotation.Authorization;
import me.jinkun.rds.core.resp.RespResult;
import me.jinkun.rds.core.utils.UtilDate;
import me.jinkun.rds.config.Constants;
import me.jinkun.rds.sys.domain.SysResource;
import me.jinkun.rds.sys.domain.SysUser;
import me.jinkun.rds.sys.service.SysResourceService;
import me.jinkun.rds.sys.service.SysUserService;
import me.jinkun.rds.sys.web.form.SysUserForm;
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
@Api(description = "首页")
@Controller
public class IndexController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysResourceService sysResourceService;

    @ApiOperation(value = "首页", notes = "首页页面")
    @Authorization
    @RequestMapping(value = {
            "/",
            "/index.html"
    }, method = RequestMethod.GET)
    public String index(ModelMap map, HttpSession session) {
        SysUser user = (SysUser) session.getAttribute(Constants.SESSION_USER_KEY);
        map.put("user", user.getName());
        map.put("time", UtilDate.formDate(new Date(), "yyyy/MM/dd"));
        return "index";
    }

    @ApiOperation(value = "文档", notes = "")
    @RequestMapping(value = {
            "/api"
    }, method = RequestMethod.GET)
    public String doc(ModelMap map) {
        return "api";
    }

    @ApiOperation(value = "登录页面", notes = "")
    @RequestMapping(value = {
            "/login.html"
    }, method = RequestMethod.GET)
    public String loginui(ModelMap map) {
        return "login";
    }

    @ApiOperation(value = "登录", notes = "")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RespResult login(SysUserForm form, HttpSession session) {
        SysUser user = sysUserService.findByLoginNameAndPassword(form.getLoginName(), form.getPassword());
        if (user == null) {
            return RespResult.fail("登录失败");
        }
        user.setPassword(null);
        session.setAttribute(Constants.SESSION_USER_KEY, user);
        return RespResult.ok("登录成功", "/");
    }

    @ApiOperation(value = "退出登录", notes = "")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public RespResult logout(HttpSession session) {
        session.removeAttribute(Constants.SESSION_USER_KEY);
        return RespResult.ok("退出成功");
    }

    @Authorization
    @ApiOperation(value = "菜单", notes = "加载菜单")
    @ApiImplicitParam(name = "id", value = "加载id的子菜单，1级菜单id为-1", required = true, paramType = "path", dataType = "Long")
    @RequestMapping(value = "/menu/{id}/children", method = RequestMethod.GET)
    @ResponseBody
    public RespResult<List<SysResource>> topMenu(@PathVariable("id") Long pid, HttpSession session) {
        SysUser user = (SysUser) session.getAttribute(Constants.SESSION_USER_KEY);
        List<SysResource> resourceList = sysResourceService.getByUserIdAndPid(user.getId(), pid);
        return RespResult.ok("查询成功", resourceList);
    }

}
