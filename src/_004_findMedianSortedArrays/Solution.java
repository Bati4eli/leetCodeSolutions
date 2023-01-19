package _004_findMedianSortedArrays;

import static common.Utils.countTime;

// WRONG ALGORITHM !
class Solution {

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int total = l1 + l2;
        int mid = total / 2;

        Integer firstItem1 = l1 > 0 ? nums1[0] : null;
        Integer firstItem2 = l2 > 0 ? nums2[0] : null;
        Integer lastItem1 = l1 > 0 ? nums1[l1 - 1] : null;
        Integer lastItem2 = l2 > 0 ? nums2[l2 - 1] : null;

        if (lastItem1 == null || firstItem2 == null || lastItem1 <= firstItem2) {
            if (total % 2 == 0) {
                int item1 = getItemFromSeqArrays(nums1, nums2, --mid);
                int item2 = getItemFromSeqArrays(nums1, nums2, ++mid);
                return (item1 + item2) / 2.0;
            }
            return getItemFromSeqArrays(nums1, nums2, mid);
        } else if (firstItem2 < firstItem1 && lastItem1 < lastItem2) {
            return findMedianSortedArrays(nums2, nums1);
        } else if (firstItem1 < firstItem2 && lastItem2 < lastItem1) {
            int mergePoint = getMergePoint(nums1, firstItem2);
            if (total % 2 == 0) {
                int item1 = getItemFromMidArrays(nums1, nums2, mergePoint, --mid);
                int item2 = getItemFromMidArrays(nums1, nums2, mergePoint, ++mid);
                return (item1 + item2) / 2.0;
            }
            return getItemFromMidArrays(nums1, nums2, mergePoint, mid);
        } else /*if (firstItem1 < firstItem2 && lastItem1 < lastItem2)*/ {
            return findBySlowMethod(nums1, nums2);
        }
    }

    private static int getMergePoint(int[] nums1, Integer firstItem2) {
        int mergePoint = -1, c = 0;
        while (mergePoint == -1) {
            if (nums1[c] > firstItem2) {
                mergePoint = c;
            }
            ++c;
        }
        return mergePoint;
    }

    public static double findBySlowMethod(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int total = l1 + l2;
        int mid = total / 2;

        int[] array = new int[total];

        int i = 0, j = 0, a = 0;
        while (a < total) {

            if (j == l2 || (i < l1 && nums1[i] < nums2[j])) {
                array[a++] = nums1[i++];
            } else {
                array[a++] = nums2[j++];
            }
        }

        if (total % 2 == 0) {
            --mid;
            return (array[mid] + array[mid + 1]) / 2.0;
        }
        return array[mid];
    }

    private static int getItemFromSeqArrays(int[] nums1, int[] nums2, int index) {
        int l1 = nums1.length;
        return index < l1 ? nums1[index] : nums2[index - l1];
    }

    private static int getItemFromMidArrays(int[] nums1, int[] nums2, int mergePoint, int index) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (index < mergePoint || index > mergePoint + l2 - 1) {
            return index < mergePoint ? nums1[index] : nums1[index - l2];
        }
        return nums2[index - mergePoint];
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 2}; //generate(1, 10000000);//
        int[] arr2 = new int[]{1, 2, 3}; //generate(10000001, 10);

        countTime(() ->
                System.out.println(
                        findBySlowMethod(arr1, arr2) // 55000.0
                ));

        countTime(() ->
                System.out.println(
                        findMedianSortedArrays(arr1, arr2) // 55000.0
                ));
    }


    public static int[] generate(int start, int count) {
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = start++;
        }
        return array;
    }
}