package com.jeethink.project.system.service.impl;

import com.jeethink.project.system.domain.SysConfig;
import com.jeethink.project.system.utils.DataTimestamp;
import com.jeethink.common.constant.UserConstants;
import com.jeethink.common.utils.StringUtils;
import com.jeethink.project.system.mapper.SysConfigMapper;
import com.jeethink.project.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 参数配置 服务层实现
 * 
 @author  官方网址
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService
{
    @Autowired
    private SysConfigMapper configMapper;

    /**
     * 查询参数配置信息
     * 
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public SysConfig selectConfigById(Long configId)
    {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        return configMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey)
    {
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        return StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue() : "";
    }

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config)
    { Object endTime = config.getEndTime();
        if(endTime!=null && !"".equals(endTime)){
            String s = DataTimestamp.dataEndTime(endTime);
            config.setEndTime(Timestamp.valueOf(s));
            String s1 = DataTimestamp.dataBeginTime(config.getBeginTime());
            config.setBeginTime(Timestamp.valueOf(s1));

        }else {
            config.setEndTime(null);
            config.setBeginTime(null);
        }
        return configMapper.selectConfigList(config);
    }

    /**
     * 新增参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config)
    {
        config.setCreateTime(new Date());
        Long aLong = configMapper.selectConfigByMoaxId();
        if(aLong==null){
            config.setConfigId(1L);
        }else {
            config.setConfigId(aLong+1);
        }
        return configMapper.insertConfig(config);
    }

    /**
     * 修改参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config)
    {
        config.setUpdateTime(new Date());
        return configMapper.updateConfig(config);
    }

    /**
     * 删除参数配置信息
     * 
     * @param configId 参数ID
     * @return 结果
     */
    @Override
    public int deleteConfigById(Long configId)
    {
        return configMapper.deleteConfigById(configId);
    }

    /**
     * 批量删除参数信息
     * 
     * @param configIds 需要删除的参数ID
     * @return 结果
     */
    @Override
    public int deleteConfigByIds(Long[] configIds)
    {
        return configMapper.deleteConfigByIds(configIds);
    }

    /**
     * 校验参数键名是否唯一
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config)
    {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
