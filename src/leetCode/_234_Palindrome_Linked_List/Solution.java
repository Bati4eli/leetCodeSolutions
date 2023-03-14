package leetCode._234_Palindrome_Linked_List;

import leetCode.common.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {

    public static boolean isPalindrome(ListNode head) {

        int size = 0;
        ListNode first = head;
        ListNode reverse = null;

        while (head != null) {
            reverse = new ListNode(head.val, reverse);

            head = head.next;
            ++size;
            //-----
        }

        int cursor = 0;
        head = first;

        while (cursor < (size / 2 )) {
            int val1 = reverse.val;
            int val2 = head.val;

            if (val1 != val2) {
                return false;
            }
            reverse = reverse.next;
            head = head.next;
            ++cursor;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(1, null);
        ListNode listNode3 = new ListNode(2, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        boolean palindrome = isPalindrome(listNode1);

        System.out.println(palindrome);
    }
}

