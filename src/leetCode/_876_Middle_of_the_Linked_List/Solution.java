package leetCode._876_Middle_of_the_Linked_List;

import leetCode.common.ListNode;

public class Solution {
    public ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode first = head;

        while (head != null) {
            head = head.next;
            ++size;
            //-----
        }

        head = first;
        int cursor = 0;
        int middle = size / 2;

        while (cursor < middle) {
            head = head.next;
            ++cursor;
        }

        return head;
    }
}
