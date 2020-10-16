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

    public static void main(String[] args) {
        MySimpleAnswers mySimpleAnswers = new MySimpleAnswers();
        System.out.println("....." + mySimpleAnswers.fourSum(new int[]{-5,-5,-3,-1,0,2,4,5}, -7));

    }
}
