package com.wangjxian.simple;

/**
 * 合并两个列表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：
 * 1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 *
 * @author wangjxian
 */
public class MergeTwoLists {

    //pilipala

    public class ListNode {
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode listNode1, ListNode listNode2){

        ListNode temp = new ListNode(0);
        ListNode pointer = temp;
        while (listNode1 != null && listNode2 != null){
            if (listNode1.val < listNode2.val){
                pointer.next = listNode1;
                pointer = pointer.next;
                listNode1 = listNode1.next;
            }else {
                pointer.next = listNode2;
                pointer = pointer.next;
                listNode2 = listNode2.next;
            }
        }
        pointer.next = (listNode1 == null) ? listNode2:listNode1;

        return temp.next;
    }

}
