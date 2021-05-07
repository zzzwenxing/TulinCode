package com.tuling.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by smlz on 2019/3/26.
 */
@Data
public class UserInfoVo {

    private String userName;

    private List<OrderVo> orderVoList;
}
