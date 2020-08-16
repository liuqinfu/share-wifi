package com.aether.sharemainctlservice.service.impl;

import com.aether.sharemainctlservice.dao.TWifiEventDao;
import com.aether.sharemainctlservice.entity.TWifiEvent;
import com.aether.sharemainctlservice.service.TWifiEventService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 终端设备连接与断开热点的事件记录(TWifiEvent)表服务实现类
 *
 * @author makejava
 * @since 2020-08-16 13:26:20
 */
@Service("tWifiEventService")
public class TWifiEventServiceImpl implements TWifiEventService {
    @Resource
    private TWifiEventDao tWifiEventDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TWifiEvent queryById(String id) {
        return this.tWifiEventDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TWifiEvent> queryAllByLimit(int offset, int limit) {
        return this.tWifiEventDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tWifiEvent 实例对象
     * @return 实例对象
     */
    @Override
    public TWifiEvent insert(TWifiEvent tWifiEvent) {
        this.tWifiEventDao.insert(tWifiEvent);
        return tWifiEvent;
    }

    /**
     * 修改数据
     *
     * @param tWifiEvent 实例对象
     * @return 实例对象
     */
    @Override
    public TWifiEvent update(TWifiEvent tWifiEvent) {
        this.tWifiEventDao.update(tWifiEvent);
        return this.queryById(tWifiEvent.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tWifiEventDao.deleteById(id) > 0;
    }
}