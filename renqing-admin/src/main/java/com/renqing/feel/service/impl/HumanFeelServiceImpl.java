package com.renqing.feel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.renqing.feel.entity.HumanFeel;
import com.renqing.feel.mapper.HumanFeelMapper;
import com.renqing.feel.service.IHumanFeelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renqing
 * @since 2022-06-17
 */
@Service
public class HumanFeelServiceImpl extends ServiceImpl<HumanFeelMapper, HumanFeel> implements IHumanFeelService {

    @Autowired
    private HumanFeelMapper feelMapper;

    @Override
    public IPage<HumanFeel> queryPage(HumanFeel humanFeel) {

        LambdaQueryWrapper<HumanFeel> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(humanFeel.getName())) {
            lqw.like(HumanFeel::getName, humanFeel.getName());
        }
        if (StringUtils.isNotBlank(humanFeel.getType())) {
            lqw.eq(HumanFeel::getType, humanFeel.getType());
        }
        if (StringUtils.isNotBlank(humanFeel.getCategory())) {
            lqw.eq(HumanFeel::getCategory, humanFeel.getCategory());
        }
        if (StringUtils.isNotBlank(humanFeel.getAddress())) {
            lqw.like(HumanFeel::getAddress, humanFeel.getAddress());
        }
        if (humanFeel.getCreateDate() != null) {
            lqw.eq(HumanFeel::getCreateDate, humanFeel.getCreateDate());
        }

        Page<HumanFeel> page = new Page<>(humanFeel.getPageNum(), humanFeel.getPageSize());
        IPage<HumanFeel> iPage = feelMapper.selectPage(page, lqw);
        return iPage;

    }
}
