package me.jinkun.rds.core.support.web;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Description: 通用Controller <br/>
 * Autor: Created by JinKun on 2017/11/22.
 */
public class CommonController extends BaseController {

    protected Set<Long> idsToSets(String ids) {
        String[] id = ids.split(",");
        Set<Long> idSets = Sets.newHashSet();
        for (int i = 0; i < id.length; i++) {
            idSets.add(Long.parseLong(id[i]));
        }
        return idSets;
    }

}
