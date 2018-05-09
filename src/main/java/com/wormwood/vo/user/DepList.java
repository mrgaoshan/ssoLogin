package com.wormwood.vo.user;

import com.wormwood.vo.BaseVO;

import java.util.List;

/**
 * Created by kasimodo on 2018-05-09.
 */
public class DepList extends BaseVO {
    private List<Long> sub_dept_id_list;

    public List<Long> getSub_dept_id_list() {
        return sub_dept_id_list;
    }

    public void setSub_dept_id_list(List<Long> sub_dept_id_list) {
        this.sub_dept_id_list = sub_dept_id_list;
    }
}
