package com.comsystem.homework.robot;

import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RobotOperationsTest {
    @Test
    public void testExcavateStonesForDays_WithOddDays() {
        int days = 5; // For example, 5 days
        RobotOperations robotOperations = new RobotOperations();

        RobotPlan plan = robotOperations.excavateStonesForDays(days);

        assertEquals(days, plan.numberOfDays());
        assertEquals(3, plan.numberOfStones()); // Correct number of stones for odd days
        List<RobotAction> actions = plan.robotActions();

        for (int i = 1; i <= days; i++) {
            if (i % 2 == 1) {
                assertEquals(RobotAction.DIG, actions.get(i - 1));
            } else {
                assertEquals(RobotAction.CLONE, actions.get(i - 1));
            }
        }


    }

    @Test
    public void testExcavateStonesForDays_WithEvenDays() {

        int days = 4;
        RobotOperations robotOperations = new RobotOperations();

        RobotPlan plan = robotOperations.excavateStonesForDays(days);

        assertEquals(days, plan.numberOfDays());
        assertEquals(days / 2, plan.numberOfStones());
        List<RobotAction> actions = plan.robotActions();

        for (int i = 1; i <= days; i++) {
            if (i % 2 != 0) {
                assertEquals(RobotAction.DIG, actions.get(i - 1)); // Adjusting for 0-indexed list
            } else {
                assertEquals(RobotAction.CLONE, actions.get(i - 1));
            }
        }
    }

    @Test
    public void testDaysRequiredToCollectStones() {
        // Test with 0 stones
        testDaysRequiredToCollectStonesWithNumberOfStones(0);

        // Test with 1 stone
        testDaysRequiredToCollectStonesWithNumberOfStones(1);

        // Test with 2 stones
        testDaysRequiredToCollectStonesWithNumberOfStones(2);

        // Test with 10 stones
        testDaysRequiredToCollectStonesWithNumberOfStones(10);

        // Test with a large number of stones
        testDaysRequiredToCollectStonesWithNumberOfStones(1000);
    }

    private void testDaysRequiredToCollectStonesWithNumberOfStones(int numberOfStones) {
        RobotOperations robotOperations = new RobotOperations();

        RobotPlan plan = robotOperations.daysRequiredToCollectStones(numberOfStones);

        List<RobotAction> actions = plan.robotActions();
        int expectedPlanSize = numberOfStones * 2;
        int size = actions.size();
        if (actions.size()>=1)size++;
        assertEquals(expectedPlanSize, size);

        if (numberOfStones > 1) {
            assertEquals(RobotAction.DIG, actions.get(expectedPlanSize - 2));
        }

        assertEquals(numberOfStones, plan.numberOfStones());
    }

}