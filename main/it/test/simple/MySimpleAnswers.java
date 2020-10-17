package it.test.simple;

import java.util.*;

public class MySimpleAnswers {

    //两数之和-暴力解法 两层遍历
    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return (new int[]{i, j});
                }
            }
        }
        throw new RuntimeException();
    }

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;
    }

    public boolean isPalindrome(int x) {
        int orig = x;
        int b = 0;
        while (x > 0) {
            int a = x % 10;
            b = b * 10 + a;
            x = x / 10;
        }
        return b == orig;
    }

    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String minLengthStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLengthStr.length()) {
                minLengthStr = strs[i];
            }
        }
        boolean f;
        while (minLengthStr.length() > 0) {
            f = true;
            for (String s : strs) {
                if (!s.startsWith(minLengthStr)) {
                    minLengthStr = minLengthStr.substring(0, minLengthStr.length() - 1);
                    f = false;
                }
            }
            if (f) {
                return minLengthStr;
            }
        }
        return "";
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String minLengthStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(minLengthStr) != 0) {
                minLengthStr = minLengthStr.substring(0, minLengthStr.length() - 1);
                if (minLengthStr.length() == 0) {
                    return "";
                }
            }
        }
        return minLengthStr;
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while (i != j) {
            int temp = (j - i) * Math.min(height[i], height[j]);
            if (temp > maxArea) {
                maxArea = temp;
            }
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) j++; // 去重
                    while (j < k && nums[k] == nums[k - 1]) k--; // 去重
                    j++;
                    k--;
                }
            }
        }
        return ans;
    }

    public int threeSumClosest(int[] nums, int target) {
        int ans = nums[0] + nums[1] + nums[2];
        if (nums == null || nums.length < 3) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[j] + nums[k] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if (sum > target)
                    k--;
                else if (sum < target)
                    j++;
                else
                    return ans;
            }
        }
        return ans;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length<4) return lists;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if (i>0 && nums[i] == nums[i-1]) continue;
            //第一层循环最小值, 如果还大于target那么一定都不满足 直接跳出
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            //第一层循环最大值, 如果还小于target那么一定都不满足, 直接跳出
            if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) continue;

            for (int j = i + 1; j < len - 2; j++) {
                if (j>i+1 && nums[j] == nums[j-1]) continue;
                //第二层循环最小值, 如果还大于target, 那么一定都不满足 直接退出
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                //第二层循环最大值, 如果还小于target, 那么一定都不满足 直接退出
                if (nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target) continue;
                int k = j + 1;
                int m = len - 1;
                while (k < m) {
                    //第三层循环最小值
                    int sum = nums[i] + nums[j] + nums[k] + nums[m];
                    if (sum == target) {
                        lists.add(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
                        k++;
                        while (k < m && nums[k] == nums[k-1]) k++;
                        m--;
                        while (k < m && nums[m] == nums[m+1]) m--;
                    }
                    if (sum<target) k++;
                    if (sum>target) m--;
                }
            }
        }
        return lists;

    }

    public int removeDuplicates(int[] nums) {
        if (nums.length==0) return 0;
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;

    }

    public int removeElement(int[] nums, int val) {

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        boolean b = true;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                b = false;
                int temp = nums[i-1];
                int j = len-1;
                while (j >i-1) {
                    if (nums[j] > temp) {
                        nums[i - 1] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                    j--;
                }
                Arrays.sort(nums, i, len);
                break;
            }
        }
        if (b) {
            Arrays.sort(nums);
        }
    }

    //###33. 思路: 二分法分成的两部分始终有一部分是有序的  我们只需要用有序的那一部分去判断边界就可以
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;
        if (len == 1) return nums[0] == target ? 0 : -1;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]) {
                //有序
                if (target >= nums[0] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                //有序
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length==0) return new int[]{-1, -1};
        if (nums.length==1) return target == nums[0] ? new int[]{0, 0} : new int[]{-1, -1};
        int leftRange = getLeft(nums, target);
        if (leftRange == -1) return new int[]{-1, -1};
        int rightRange = getRight(nums, target);
        return new int[]{leftRange, rightRange};
    }

    private int getLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else if (target < nums[mid]) {
                r = mid - 1;
            }
        }
        if (l >= nums.length || nums[l] != target) return -1;
        return l;
    }

    private int getRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                l = mid + 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else if (target < nums[mid]) {
                r = mid - 1;
            }
        }
        return r;

    }

    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = ((r - l) >> 1) + l;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque();
        dfs(0, len, res, path, target, candidates);
        return res;

    }

    private void dfs(int begin, int len, List<List<Integer>> res, Deque<Integer> path, int target, int[] candidates) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);
            dfs(i, len, res, path, target-candidates[i], candidates);
            path.removeLast();

        }
    }


    public static void main(String[] args) {
        MySimpleAnswers mySimpleAnswers = new MySimpleAnswers();
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = mySimpleAnswers.combinationSum(candidates, target);
        System.out.println("输出 => " + res);

    }
}
