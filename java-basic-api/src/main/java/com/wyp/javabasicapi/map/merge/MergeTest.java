package com.wyp.javabasicapi.map.merge;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyupeng
 * @date 2021年04月25日 9:07
 * 参考：https://mp.weixin.qq.com/s/AFyPmPVG792ORZfzSwL1BQ
 */
public class MergeTest {

    public static void main(String[] args) throws Exception {
        List<StudentScore> stuScoreList = buildATestList();
        ObjectMapper objectMapper = new ObjectMapper();
        //获取每个学生的总成绩  {"李四":228,"张三":215,"王五":235}
        Map<String, Integer> stuScoreMap = new HashMap<>();
        // getTotalScore1(stuScoreList, stuScoreMap);
        stuScoreMap = getTotalScore2(stuScoreList);

        System.out.println(objectMapper.writeValueAsString(stuScoreMap));

    }

    /**
     * 使用 map 传统方法
     */
    private static void getTotalScore1(List<StudentScore> stuScoreList, Map<String, Integer> stuScoreMap) {
        stuScoreList.forEach(studentScore -> {
            if (stuScoreMap.containsKey(studentScore.getStuName())) {
                stuScoreMap.put(studentScore.getStuName(),
                        stuScoreMap.get(studentScore.getStuName()) + studentScore.getScore());
            } else {
                stuScoreMap.put(studentScore.getStuName(), studentScore.getScore());
            }
        });
    }

    /**
     * 使用 map的merge 方法
     * merge() 可以这么理解：它将新的值赋值到 key （如果不存在）或更新给定的key 值对应的 value
     * 原理：该方法接收三个参数：key ，value，remappingFunction ，
     *      如果给定的key不存在，它就变成了 put(key, value)
     *      如果 key 已经存在，我们 remappingFunction 可以选择合并的方式，然后将合并得到的 newValue 赋值给原先的 key。
     */
    private static Map<String, Integer> getTotalScore2(List<StudentScore> stuScoreList) {
        Map<String, Integer> studentScoreMap2 = new HashMap<>();
        stuScoreList.forEach(studentScore -> studentScoreMap2.merge(
                studentScore.getStuName(),
                studentScore.getScore(),
                Integer::sum));
        return studentScoreMap2;
    }

    private static List<StudentScore> buildATestList() {
        List<StudentScore> studentScoreList = new ArrayList<>();
        StudentScore studentScore1 = new StudentScore("张三", "语文", 70);
        StudentScore studentScore2 = new StudentScore("张三", "数学", 80);
        StudentScore studentScore3 = new StudentScore("张三", "英语", 65);
        StudentScore studentScore4 = new StudentScore("李四", "语文", 68);
        StudentScore studentScore5 = new StudentScore("李四", "数学", 70);
        StudentScore studentScore6 = new StudentScore("李四", "英语", 90);
        StudentScore studentScore7 = new StudentScore("王五", "语文", 80);
        StudentScore studentScore8 = new StudentScore("王五", "数学", 85);
        StudentScore studentScore9 = new StudentScore("王五", "英语", 70);
        studentScoreList.add(studentScore1);
        studentScoreList.add(studentScore2);
        studentScoreList.add(studentScore3);
        studentScoreList.add(studentScore4);
        studentScoreList.add(studentScore5);
        studentScoreList.add(studentScore6);
        studentScoreList.add(studentScore7);
        studentScoreList.add(studentScore8);
        studentScoreList.add(studentScore9);
        return studentScoreList;
    }



}
