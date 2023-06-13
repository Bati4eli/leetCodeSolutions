package leetCode.common;

import java.util.LinkedList;
import java.util.Queue;

public class Utils {
    public static void countTime(Runnable runnable) {
        long start_time = System.nanoTime();

        runnable.run();

        long end_time = System.nanoTime();
        double difference = (end_time - start_time) / 1e6;

        System.out.println("TOTAL TIME: " + difference + " ms.");
    }

    public static TreeNode getTreeNode(Integer[] ints) {
        Queue<TreeNode> lvlNodes = new LinkedList<>();
        TreeNode rootNode = new TreeNode(ints[0]);
        lvlNodes.add(rootNode);

        for (int i = 1; i < ints.length; i = i + 2) {
            TreeNode node = lvlNodes.poll();
            node.left = createNode(ints[i]);
            node.right = createNode(ints[i + 1]);
            if (node.left != null) lvlNodes.add(node.left);
            if (node.right != null) lvlNodes.add(node.right);
        }
        return rootNode;
    }

    private static TreeNode createNode(Integer val) {
        if (val == null) {
            return null;
        }
        return new TreeNode(val);
    }

    public static ListNode getListNode(int[] ints) {
        ListNode node = new ListNode(0);
        ListNode first = node;
        for (int i = 0; i < ints.length; i++) {
            if (i == 0) {
                node.val = ints[i];
            } else {
                node.next = new ListNode(ints[i]);
                node = node.next;
            }
        }
        return first;
    }

    public static void printListNode(ListNode l1) {
        System.out.println("ListNode: ");
        while (l1 != null) {
            System.out.println(l1.val);
            l1 = l1.next;
        }
    }
}
