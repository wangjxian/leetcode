package com.enzo.selfproj;

//反转l链表
//https://leetcode-cn.com/problems/reverse-linked-list/
public class Solution_206 {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static ListNode reverseList(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode cur = head;
        ListNode pre = null;

        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }


        return pre;
    }




}
