package com.loginov._849;

public class MaximizeDistanceToClosestPerson {

    public static void main(String[] args) {
//        int[] seats = {1, 0, 1};
//        int[] seats = {1, 0};
//        int[] seats = {1, 1, 0};
//        int[] seats = {1, 1, 1, 0};
//        int[] seats = {1, 0, 0, 1}; // tricky one: 1 (LC option) or 2 seats will be the max distance?
//        int[] seats = {1, 0, 0, 0, 1};
        int[] seats = {1,1,0,0,0,1,0};
        System.out.println(new MaximizeDistanceToClosestPerson().getMaxDistanceFromClosestPerson(seats));
    }

    public int getMaxDistanceFromClosestPerson(int[] seats) {
        if (seats == null) throw new NullPointerException("Seats array is null");
        if (seats.length < 2 || seats.length > 20000)
            throw new IllegalArgumentException("Unknown for this seats size");
        // check edges first to separate and optimize logic for them
        int maxDistLeft, maxDistRight = -1;
        maxDistLeft = getMaxDistOnLeftEdge(seats);
        if (maxDistLeft >= seats.length / 2) return maxDistLeft;
        maxDistRight = getMaxDistOnRightEdge(seats);
        if (maxDistRight >= seats.length / 2) return maxDistRight;
        if (maxDistRight > seats.length / 3 && maxDistRight >= maxDistLeft) return maxDistRight;
        if (maxDistLeft > seats.length / 3 && maxDistLeft > maxDistRight) return maxDistLeft;
        // now we can check all the gaps in between. we can skip the first know seat with 1
        int maxDist = maxDistRight > maxDistLeft ? maxDistRight : maxDistLeft;
        int currGap = 0;
        for (int s = maxDistLeft + 1; s < seats.length - maxDistRight; s++) {
            int seat = seats[s];
            if (seat == 0) currGap++;
            else {
                // end of another gap - update result if needed
                // 1st option: if we want a largest distance for gaps of even seats, for 2 seats -> 2 (LC option)
                //maxDist = (currGap / 2 + 1) > maxDist ? (currGap / 2 + 1) : maxDist;
                // 2nd option: if we want a shortest distance for gaps of even seats, for 2 seats -> 1
                maxDist = (currGap / 2 + currGap % 2) > maxDist ? (currGap / 2 + currGap % 2) : maxDist;
                currGap = 0;
                // after we finished another gap, check if we can find a longer distance or is it already max
                if (isMaxDistMoreThanHalfOfRemainingSeats(maxDist, s+1, seats.length-maxDistRight))
                    return maxDist;

            }
        }
        return maxDist;
    }

    protected int getMaxDistOnLeftEdge(int[] seats) {
        int res = 0;
        for (int s = 0; s < seats.length; s++) {
            int seat = seats[s];
            if (seat == 0) res++;
            else break;
        }
        return res;
    }

    protected int getMaxDistOnRightEdge(int[] seats) {
        int res = 0;
        for (int s = seats.length - 1; s >= 0; s--) {
            int seat = seats[s];
            if (seat == 0) res++;
            else break;
        }
        return res;
    }

    protected boolean isMaxDistMoreThanHalfOfRemainingSeats(int maxDist, int start, int end) {
        return maxDist > (end - start) / 2;
    }

}
