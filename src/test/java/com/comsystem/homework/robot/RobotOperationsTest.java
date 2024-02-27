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

}