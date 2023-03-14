package leetCode._019_removeNthFromEnd;

import leetCode.common.ListNode;

import java.util.ArrayDeque;
import java.util.Queue;

import static leetCode.common.Utils.getListNode;
import static leetCode.common.Utils.printListNode;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        Queue<ListNode> queue = new ArrayDeque<>();

        int nodeSize = 0;
        while (node != null) {
            nodeSize++;
            queue.add(node);
            if (queue.size() > (n + 1)) {
                queue.poll();
            }
            node = node.next;
        }
        if (nodeSize == n) {
            return head.next;
        } else {
            ListNode previousNode = queue.poll();
            previousNode.next = getNext(previousNode);
            return head;
        }
    }

    private ListNode getNext(ListNode node) {
        if (node.next == null) {
            return null;
        }
        return node.next.next;
    }

    public static void main(String[] args) {
        ListNode n = new Solution().removeNthFromEnd(getListNode(new int[]{1, 2}), 1);
        printListNode(n);
    }
}