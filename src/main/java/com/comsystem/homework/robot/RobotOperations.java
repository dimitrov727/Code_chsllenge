package com.comsystem.homework.robot;


import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RobotOperations {

    /**
     * An algorithm that converts a number of days into an action plan.
     *
     * @param days the number of days that the robot can work
     * @return The action plan <em>must maximize</em> the number of stones that the robot will dig. In other words, this
     * algorithm must try to achieve the highest value of {@link RobotPlan#numberOfStones} possible for the
     * number of provided days. The value of {@link RobotPlan#numberOfDays} is equal to the input
     * days parameter
     * @see RobotPlan
     */
    public RobotPlan excavateStonesForDays(int days) {
        int stones = 0;
        List<RobotAction> action = new ArrayList<>();
        try {
            for (int i = 1; i <= days; i++) {
                if (i % 2 == 1) {
                    action.add(RobotAction.DIG);
                    stones++;
                } else {
                    action.add(RobotAction.CLONE);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new RobotPlan(days, stones, action);


    }

    /**
     * An algorithm that converts a number of stones into an action plan. Essentially this algorithm is the inverse of
     * {@link #excavateStonesForDays(int)}.
     *
     * @param numberOfStones the number of stones the robot has to collect
     * @return The action plan <em>must minimize</em> the number of days necessary for the robot to dig the
     * provided number of stones. In other words, this algorithm must try to achieve the lowest value of
     * {@link RobotPlan#numberOfDays} possible for the number of provided stones. The value of
     * {@link RobotPlan#numberOfStones} is equal to the numberOfStones parameter
     * @see RobotPlan
     */
    public RobotPlan daysRequiredToCollectStones(int numberOfStones) {

        List<RobotAction> plan = new ArrayList<>();
        try {
            for (int i = 1; i <= numberOfStones; i++) {
                plan.add(RobotAction.DIG);
                plan.add(RobotAction.CLONE);
            }
            int lastIndex = plan.size() - 1;

            if (numberOfStones < plan.size()) {
                plan.remove(lastIndex);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new RobotPlan(plan.size(), numberOfStones, plan);
    }

}
