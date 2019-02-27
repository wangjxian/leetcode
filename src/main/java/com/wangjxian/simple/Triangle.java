package com.wangjxian.simple;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 *
 * ]
 * @author wangjxian
 */
public class Triangle {

    private static List<List<Integer>> generate(int numRows) {
        if (numRows == 0){
            return new ArrayList<>();
        }
        List<Integer> tmp = new ArrayList<>();
        List<List<Integer>> rows = new ArrayList<>();
        tmp.add(1);
        rows.add(new ArrayList<>(tmp));
        if (numRows >= 2){
            tmp.add(1);
            rows.add(new ArrayList<>(tmp));
        }
        for (int i=3;i<=numRows;i++){
            tmp.clear();
            tmp.add(1);
            List<Integer> list = rows.get(i-2);
            for (int j=0;j<i-2;j++){
                tmp.add(list.get(j)+list.get(j+1));
            }
            tmp.add(list.get(i-2));
            rows.add(new ArrayList<>(tmp));
        }
        return rows;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = generate(6);
        for (List<Integer> list : lists){
            System.out.print("[");
            for (Integer in : list){
                System.out.print(in+",");
            }
            System.out.println("]");
        }
    }
}
