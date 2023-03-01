package _787_findCheapestPrice_GRAPH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Алгоритм Дейкстры
 */
class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<Node>> graph = fillGraph(n, flights);
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(src, 0));
        int[] minCosts = new int[n];
        Arrays.fill(minCosts, Integer.MAX_VALUE);

        int steps = 0;
        while (!queue.isEmpty() && steps <= k) {
            int size = queue.size();
            while (size-- > 0) {
                Node curr = queue.poll();
                List<Node> neighbours = graph.get(curr.index);
                checkNeighbours(queue, minCosts, curr, neighbours);
            }
            steps++;
        }

        return minCosts[dst] == Integer.MAX_VALUE ? -1 : minCosts[dst];
    }

    private Map<Integer, List<Node>> fillGraph(int n, int[][] flights) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] flight : flights) {
            graph
                    .get(flight[0])
                    .add(new Node(flight[1], flight[2]));
        }
        return graph;
    }

    private void checkNeighbours(Queue<Node> queue, int[] minCost, Node curr, List<Node> neighbours) {
        for (Node neighbour : neighbours) {
            int neighbourNode = neighbour.index;
            int price = neighbour.price;
            int sum = price + curr.price;
            if (sum >= minCost[neighbourNode]) {
                continue;
            }
            minCost[neighbourNode] = sum;
            queue.offer(new Node(neighbourNode, minCost[neighbourNode]));
        }
    }

    static class Node {
        public Node(int index, int price) {
            this.index = index;
            this.price = price;
        }
        int index;
        int price;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().findCheapestPrice(4, new int[][]{
                        new int[]{0, 1, 100},
                        new int[]{1, 2, 100},
                        new int[]{2, 0, 100},
                        new int[]{1, 3, 600},
                        new int[]{2, 3, 200},
                }, 0, 3, 1)
        );
    }
}