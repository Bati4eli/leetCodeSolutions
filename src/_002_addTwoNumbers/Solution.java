package _002_addTwoNumbers;

import common.ListNode;

import static common.Utils.getListNode;
import static common.Utils.printListNode;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode nextRes = new ListNode(-1);
        ListNode firstRes = nextRes;
        int tail = 0;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + tail;
            int digit = sum % 10;
            tail = sum / 10;
            nextRes = addResult(nextRes, digit);
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + tail;
            int digit = sum % 10;
            tail = sum / 10;
            nextRes = addResult(nextRes, digit);
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + tail;
            int digit = sum % 10;
            tail = sum / 10;
            nextRes = addResult(nextRes, digit);
            l2 = l2.next;
        }

        if (tail > 0 ){
            addResult(nextRes, tail);
        }

        return firstRes;
    }

    private ListNode addResult(ListNode result, int sum) {
        if (result.val == -1) {
            result.val = sum;
        } else {
            result.next = new ListNode(sum);
            result = result.next;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = getListNode(new int[]{9,9,9,9,9,9,9});
        ListNode l2 = getListNode(new int[]{9,9,9,9});
        printListNode(
                new Solution().addTwoNumbers(l1, l2)
        );
    }
}