class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;
        int firstCritical = -1;
        int prevCritical = -1;

        while (curr.next != null) {
            ListNode next = curr.next;

            boolean isCritical =
                (curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevCritical);
                    maxDistance = Math.max(maxDistance, index - firstCritical);
                }

                prevCritical = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        if (firstCritical == -1 || prevCritical == firstCritical) {
            return new int[] {-1, -1};
        }

        return new int[] {minDistance, maxDistance};
    }
}
