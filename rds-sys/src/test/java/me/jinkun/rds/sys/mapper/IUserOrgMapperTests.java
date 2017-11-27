package me.jinkun.rds.sys.mapper;

import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

/**
 * @author JinKun
 * @date 2017-11-23
 * @time 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserOrgMapperTests {

    @Autowired
    IUserOrgMapper iUserOrgMapper;

    @Test
    public void insertBatch() {
        HashSet<Long> orgIds = Sets.newHashSet();
        orgIds.add(500L);
        orgIds.add(501L);
        orgIds.add(502L);
        iUserOrgMapper.insertBatch(100L, orgIds);
    }
}
