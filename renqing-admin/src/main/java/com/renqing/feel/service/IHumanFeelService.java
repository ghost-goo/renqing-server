package com.renqing.feel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.renqing.feel.entity.HumanFeel;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IHumanFeelService extends IService<HumanFeel> {

    /**
     * 分页查询
     */
    IPage<HumanFeel> queryPage(HumanFeel humanFeel);

}
