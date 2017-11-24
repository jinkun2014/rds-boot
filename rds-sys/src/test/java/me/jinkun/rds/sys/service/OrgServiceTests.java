package me.jinkun.rds.sys.service;

import com.google.common.collect.Sets;
import me.jinkun.rds.core.sort.Sorter;
import me.jinkun.rds.sys.entity.Org;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author JinKun
 * @date 2017-11-23
 * @time 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgServiceTests {

    @Autowired
    IOrgService iOrgService;

    @Test
    public void list() {
        Org org = new Org();
        org.setDelFlag(false);
        List<Org> orgList = iOrgService.loads(org, null, Sets.newHashSet(new Sorter("seq", true)), null);
        System.out.println(orgList);
    }
}
