package _024_swapPairs;

import common.ListNode;

import static common.Utils.getListNode;
import static common.Utils.printListNode;

// https://leetcode.com/problems/swap-nodes-in-pairs/

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prevNode = head.next;
        ListNode nextNode = getNextNext(head);

        prevNode.next = head;
        head.next = swapPairs(nextNode);
        return prevNode;
    }

    private ListNode getNextNext(ListNode node) {
        if (node.next == null) {
            return null;
        }
        return node.next.next;
    }

    public static void main(String[] args) {
        ListNode n = new Solution().swapPairs(getListNode(new int[]{1, 2, 3, 4, 5}));
        printListNode(n);
    }
}