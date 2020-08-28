package com.aether.xxljob.executor.service.jobhandler;

import com.aether.sharecommon.utils.RedisUtil;
import com.aether.xxljob.core.biz.model.ReturnT;
import com.aether.xxljob.core.handler.annotation.XxlJob;
import com.aether.xxljob.core.log.XxlJobLogger;
import com.aether.xxljob.executor.entity.TFluxMeal;
import com.aether.xxljob.executor.service.TDeviceFluxService;
import com.aether.xxljob.executor.service.TFluxMealService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 我走路带风
 * @since 2020/8/24 10:35
 */
@Component
public class FluxHandler {

    public static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    @Autowired
    private TFluxMealService tFluxMealService;

    @Autowired
    private TDeviceFluxService tDeviceFluxService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 将用户流量使用记录持久化
     * 1、简单任务（Bean模式）
     */
    @XxlJob("fluxJobHandler")
    public ReturnT<String> fluxJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        Integer splitSize = 100;
        //查询所有有效套餐记录
        List<String> deviceIdList = tFluxMealService.queryAllValid();
        List<List<String>> splitList = splitList(deviceIdList, splitSize);
        CountDownLatch cdl = new CountDownLatch(splitList.size());
        splitList.stream().forEach(list->{
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    list.stream().forEach(deviceId->{
                        List<TFluxMeal> needUpdateList = new ArrayList<>();
                        //将redis中的流量使用记录保存到数据库

                        //更新用户钱包与套餐用量


                        //deviceId的所有有效套餐
                        List<TFluxMeal> tFluxMeals = tFluxMealService.queryAllValidByDeviceId(deviceId);
//                        sortASC(tFluxMeals);
                        TFluxMeal minTFluxMeal = tFluxMeals.get(0);
                        //统计流量使用
                        Date startTime = minTFluxMeal.getStartTime();
                        final long[] used = {tDeviceFluxService.sumFluxByBeviceIdandTime(deviceId, startTime)};
                        for (TFluxMeal meal : tFluxMeals) {
                            long leftFlux = meal.getLeftFlux();
                            if (leftFlux <= used[0]){
                                meal.setUsedFlux(meal.getUsedFlux()+leftFlux);
                                meal.setLeftFlux(0);
                                meal.setStatus(2);
                                needUpdateList.add(meal);
                                if (leftFlux == used[0]){
                                    //本套餐正好用完
                                    break;
                                }
                                used[0] -= leftFlux;
                            }else{
                                meal.setUsedFlux(used[0]);
                                meal.setLeftFlux(leftFlux-used[0]);
                                needUpdateList.add(meal);
                                break;
                            }
                        }
                        //更新
                        needUpdateList.stream().forEach(tFluxMeal -> tFluxMealService.update(tFluxMeal));

                    });
                    cdl.countDown();
                }
            });
        });
        cdl.await();
        return ReturnT.SUCCESS;
    }

    /**
     * 每天统计用户的日流量使用与日收益
     * 1、简单任务（Bean模式）
     */
    @XxlJob("groupDayFluxJobHandler")
    public ReturnT<String> groupDayFluxJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        Integer splitSize = 100;
        //查询所有有效套餐记录
        List<String> deviceIdList = tFluxMealService.queryAllValid();
        List<List<String>> splitList = splitList(deviceIdList, splitSize);
        CountDownLatch cdl = new CountDownLatch(splitList.size());
        splitList.stream().forEach(list->{
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    list.stream().forEach(deviceId->{


                    });
                    cdl.countDown();
                }
            });
        });
        cdl.await();
        return ReturnT.SUCCESS;
    }

    /**
     * 每月统计用户的月流量使用与月收益
     * 1、简单任务（Bean模式）
     */
    @XxlJob("groupMonthFluxJobHandler")
    public ReturnT<String> groupMonthFluxJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        Integer splitSize = 100;
        //查询所有有效套餐记录
        List<String> deviceIdList = tFluxMealService.queryAllValid();
        List<List<String>> splitList = splitList(deviceIdList, splitSize);
        CountDownLatch cdl = new CountDownLatch(splitList.size());
        splitList.stream().forEach(list->{
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    list.stream().forEach(deviceId->{


                    });
                    cdl.countDown();
                }
            });
        });
        cdl.await();
        return ReturnT.SUCCESS;
    }

    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     * @param <T>
     *
     * @param list
     * @param len
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {

        if (list == null || list.isEmpty() || len < 1) {
            return Collections.emptyList();
        }
        List<List<T>> result = new ArrayList<>();

        int size = list.size();
        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }

        return result;
    }

    /**
     * 进行排序，升序
     * @param tFluxMeals
     * @return
     */
    public List<TFluxMeal> sortASC(List<TFluxMeal> tFluxMeals){
        Collections.sort(tFluxMeals, new Comparator<TFluxMeal>() {
            @Override
            public int compare(TFluxMeal o1, TFluxMeal o2) {
                Date o1StartTime = o1.getStartTime();
                Date o2StartTime = o2.getStartTime();
                if (o1StartTime.before(o2StartTime)) {
                    return 1;
                }else if (o1StartTime.after(o2StartTime)){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        return tFluxMeals;
    }

}
