package it.test.simple.jichu;

import java.util.*;

public class Solution {


    //盛水最多的容器
    public int maxArea(int[] height) {
        int i = 0; int j = height.length-1;
        int ans = 0;
        while (i < j) {
            ans = Math.max(ans, Math.min(height[j], height[i]) * (j-i));
            if (height[j] >= height[i]) {
                i++;
            } else {
                j--;
            }
        }

        return ans;
    }

    //移动零
    public void moveZeroes(int[] nums) {
        //用快指针遍历数组, 慢指针去填充不为0的数值
        //最后慢指针之后的数据全为0

        int slow = 0;
        for (int fast = 0; fast<nums.length; fast++){
            if (nums[fast]!=0){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        for (; slow < nums.length; slow++) {
            nums[slow] = 0;
        }
    }


    //爬楼梯
    public int climbStairs(int n) {
        //这个结果等于去掉第一项的斐波拉契数列
        //因为从0开始, 所以返回dp[n-1]
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];

    }

    //三数之和

    public List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> ans = new ArrayList<>();
//        if (nums.length < 3 || nums == null) {
//            return ans;
//        }
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] > 0) break;
//            if (i > 0 && nums[i] == nums[i - 1]) continue;
//            int j = i + 1;
//            int k = nums.length - 1;
//            while (j < k) {
//                if (nums[i] + nums[k] + nums[j] > 0) {
//                    k--;
//                } else if (nums[i] + nums[j] + nums[k] < 0) {
//                    j++;
//                } else {
//                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
//                    k--;
//                    j++;
//                    while (j < k && nums[j] == nums[j - 1]) j++;
//                    while (j < k && nums[k] == nums[k + 1]) k--;
//                }
//            }
//        }
//        return ans;
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i< nums.length-1; i++){
            if (nums[i]>0) break;
            if (i>0 && nums[i] == nums[i-1]) continue;
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                if (nums[i]+nums[j]+nums[k] <0){
                    j++;
                } else if(nums[i]+nums[j]+nums[k]>0){
                    k--;
                } else{
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;k--;
                    while(j<k && nums[j] == nums[j-1]) j++;
                    while(j<k && nums[k] == nums[k+1]) k--;
                }
            }
        }
        return  ans;
    }

    //反转链表
    public ListNode reverseList(ListNode head) {
        //快慢指针, 快指针指向下一个要执行的node, 慢指针将结果结果指向前一个
//        ListNode pre = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode temp = curr.next;
//            curr.next = pre;
//            pre = curr;
//            curr = temp;
//        }
//        return pre;


        // //递归
         if(head == null || head.next == null){
             return head;
         }
         ListNode curr = reverseList(head.next);
         head.next.next = head;
         head.next = null;
         return curr;

    }

    //两两交换节点
    public ListNode swapPairs(ListNode head) {
        //迭代法
        //四个节点
        //tmp->start->end->下一个
        //每次让tmp指向end, start指向end的下一个, end指向start, 再把tmp往前移动一位
         ListNode pre = new ListNode(0);
         pre.next = head;
         ListNode tmp = pre;
         while(tmp.next!=null && tmp.next.next!= null){
             ListNode start = tmp.next;
             ListNode end = tmp.next.next;
             tmp.next = end;
             start.next = end.next;
             end.next = start;
             tmp = start;
         }
         return pre.next;

        //假如有1234四个节点,可以让1指向3  2指向4   再让3指向2  那么就是1324  就可以交换中间两个,再继续往后替换就可以
        //因为第一个节点没有办法替换,所以新建一个节点
//        ListNode pre = new ListNode(0);
//        pre.next = head;
//        ListNode temp = pre;
//        while(temp.next!=null && temp.next.next != null){
//            ListNode start = temp.next;
//            ListNode end = temp.next.next;
//            temp.next = start;
//            start.next = end.next;
//            end.next = start;
//            temp = start;
//        }
//        return pre.next;

        //递归
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode next = head.next;
//        head.next = swapPairs(next.next);
//        next.next = head;
//        return next;

    }

    //环形链表

    public boolean hasCycle(ListNode head) {
        //1. Hash法
//        Set<ListNode> exist = new HashSet<>();
//        while(head != null){
//            if(!exist.add(head)){
//                return true;
//            }
//            head = head.next;
//        }
//        return false;

        //2. 快慢指针
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        return true;

    }




    //环形链表
    public ListNode detectCycle(ListNode head) {
        //1. Hash表解法
//         Set<ListNode> exist = new HashSet<>();
//         if(head == null || head.next == null) return null;
//         while(head!=null){
//             if(exist.contains(head.next)){
//                 return head;
//             } else {
//                 exist.add(head.next);
//             }
//             head = head.next;
//         }
//         return null;

        //2. 双指针
        //假设环之前的节点个数为a个, 环的节点个数为b,
        //fast走的步数是slow的2倍,         即 fast = 2slow;
        //第一次相遇时fast比slow多走了n圈, 即 fast = slow + nb;
        //两式相消得到slow = nb

        //慢指针每一次到环的起点所走的路程为a + nb
        //第一次相遇时a已经走了nb个节点, 则只需要再走a个节点就可以到环起始的位置
        //a = ? 可以让fast再指向head, 每次走一步, 走到环其实位置刚好是a步, 此时fast与slow再次相遇
//        if(head == null || head.next == null) return null;
//        ListNode fast = head, slow = head;
//        while(true){
//            if(fast == null || fast.next == null) return null;
//            fast = fast.next.next;
//            slow = slow.next;
//            if(fast == slow) break;
//        }
//        fast = head;
//        while(fast!=slow){
//            fast = fast.next;
//            slow = slow.next;
//        }
//        return fast;

        if (head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;

    }


    //删除数组重复项
    public int removeDuplicates(int[] nums) {
        //思路: 排序数组
        //采用快慢指针, 慢指针始终指向第一个不重复的数据
        //快指针往后移动, 遇到不一样的, 就放到i+1的位置, 并把i+1
//        if(nums.length <= 1) return nums.length;
//
//        int i = 0;
//        int j = 1;
//        while(j<nums.length){
//            if(nums[j]!=nums[i]){
//                i++;
//                nums[i] = nums[j];
//            }
//            j++;
//        }
//        return i+1;

        if (nums.length <=1) return nums.length;
        int i = 0;
        for (int j = i+1; j < nums.length; j++) {
            if (nums[j] != nums[i]){
                nums[++i] = nums[j];
            }
        }
        return i+1;
    }
    //旋转数组

    public void rotate(int[] nums, int k) {
        //这是一个神奇的故事
        //往后移动K位 等于k之前的数组反转, k之后的数据反转, 为什么K要%nums.length, 因为题目没有说k<nums.length
        k = k % nums.length;
        revers(0, nums.length-1, nums);
        revers(0, k-1, nums);
        revers(k, nums.length-1, nums);

    }

    public void revers(int start, int end, int[] nums){
        while(start < end){
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            end--;start++;
        }
    }


    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //递归  傻递归
        //递归终止条件是l1 == null || l2  == null
        // if(l1 == null){
        //     return l2;
        // } else if(l2 == null){
        //     return l1;
        // } else if(l1.val>l2.val){
        //     l2.next = mergeTwoLists(l1, l2.next);
        //     return l2;
        // } else {
        //     l1.next = mergeTwoLists(l1.next, l2);
        //     return l1;
        // }

        //迭代
        ListNode pre = new ListNode(-1);
        ListNode move = pre;
        while(l1 != null && l2 != null){
            if (l1.val >= l2.val) {
                move.next = l2.next;
                l2 = l2.next;
            } else {
                move.next = l1.next;
                l1 = l1.next;
            }
            move = move.next;
        }
        move.next = l1 == null? l2: l1;
        return pre.next;
//        ListNode pre = new ListNode(-1);
//        ListNode move = pre;
//        while(l1 != null && l2 != null){
//            if(l1.val>=l2.val){
//                move.next = l2;
//                l2 = l2.next;
//            } else {
//                move.next = l1;
//                l1 = l1.next;
//            }
//            move = move.next;
//        }
//        move.next = l1 == null ? l2 : l1;
//        return pre.next;


    }

    //加1
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1; i>=0; i--){
            digits[i]++;
            digits[i]%=10;
            if(digits[i] != 0) return digits;
        }

        int[] another = new int[digits.length+1];
        another[0] = 1;
        return another;

    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] ints = solution.twoSum(new int[]{2,7,11,15}, 9);
//        System.out.println(Arrays.toString(ints));

//        int i = solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
//        System.out.println(i);

//        int[] aaa = new int[]{0, 1, 0, 3, 12};
//        solution.moveZeroes(aaa);
//        System.out.println(Arrays.toString(aaa));

//        int i = solution.climbStairs(5);
//        System.out.println(i);

//        List<List<Integer>> lists = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
//        System.out.println(lists);

        int i1 = solution.removeDuplicates(new int[]{2, 2, 3, 4, 4, 5,5,5,5,6});
        System.out.println(i1);

    }




    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //两数之和
    public int[] twoSum(int[] nums, int target) {

        //xunhuan
//        for (int i = 0; i < nums.length-1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return new int[0];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
