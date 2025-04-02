package com.rodrigotroy.commons.core.model;


import com.rodrigotroy.commons.core.model.response.Outcome;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 08-08-2023
 * Time: 17:43
 */
public class OutcomeTest {
    @DataProvider
    public Object[][] cases() {
        return new Object[][]{
                {-1, Outcome.NONE},
                {0, Outcome.OK},
                {1, Outcome.ERROR},
                {2, Outcome.WARN},
                {3, Outcome.NONE},
                {-1, Outcome.NONE},
                {"OK", Outcome.OK},
                {"ok", Outcome.OK},
                {"ERROR", Outcome.ERROR},
                {"ErrOR", Outcome.ERROR},
                {"error", Outcome.ERROR},
                {"EEE", Outcome.NONE},
                {"", Outcome.NONE},
                {" ", Outcome.NONE},
                {"-", Outcome.NONE},
                {null, Outcome.NONE},
                {"WARN", Outcome.WARN},};
    }

    @org.testng.annotations.Test(dataProvider = "cases")
    public void testFromValue(Object value,
                              Outcome expectedOutcome) {
        Outcome result = value instanceof Integer i ?
                         Outcome.fromValue(i) : Outcome.fromValue((String) value);

        Assert.assertNotNull(result);
        Assert.assertEquals(result,
                            expectedOutcome);
    }
}
