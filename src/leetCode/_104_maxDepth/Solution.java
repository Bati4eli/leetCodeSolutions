package leetCode._104_maxDepth;


import leetCode.common.TreeNode;

import static leetCode.common.Utils.getTreeNode;

class Solution {
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) throws Exception {
        TreeNode root = getTreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});

        System.out.println(
                new Solution().maxDepth(root)
        );
    }
}