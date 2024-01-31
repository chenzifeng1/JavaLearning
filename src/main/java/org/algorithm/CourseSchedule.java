package org.algorithm;

import java.util.*;

/**
 * @author chenzifeng
 * @version 1.0
 * @description 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * @date 2024/1/30 9:06 PM
 */
public class CourseSchedule {

    private List<List<Integer>> edges = new ArrayList<>();

    // 是否可行
    private Boolean vaild = true;

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {0, 2}, {3, 4}, {4, 6}, {5, 6},  {2, 3}};
        CourseSchedule courseSchedule = new CourseSchedule();
        boolean result = courseSchedule.canFinish(7, prerequisites);
        boolean result1 = courseSchedule.canFinish1(7, prerequisites);
        System.out.println(result);
        System.out.println(result1);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            edges.get(prerequisite[0]).add(prerequisite[1]);
        }

        int[] mark = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            int after = prerequisite[0];
            if (mark[after] == 0) {
                dfs(mark, after);
            }
        }

        return vaild;
    }

    public void dfs(int[] mark, int i) {
        mark[i] = 1;
        List<Integer> integers = edges.get(i);
        // 不用判断无出度的点了，还有可能一门课有多个前置课程
        for (Integer integer : integers) {
            if (mark[integer] == 0) {
                dfs(mark, integer);
                if (!vaild) {
                    return;
                }
            } else if (mark[integer] == 1) {
                vaild = false;
            }

        }
        mark[i] = 2;
    }


    /**
     * 入度   广度优先遍历
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        int[] flag = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            flag[prerequisite[0]]++;
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            numCourses--;
            for (Integer cur : edges.get(poll)) {
                flag[cur] -= 1;
                if (flag[cur] == 0) {
                    queue.add(cur);
                }
            }
        }

        return numCourses == 0;
    }


}
