package com.rodrigotroy.commons.core.model;


import com.rodrigotroy.commons.core.model.response.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 21-09-2022
 * Time: 11:24
 */
public class IResponseTest {
    @DataProvider
    public Object[][] cases() {
        return new Object[][]{
                {ErrorResponse.fromMessage("Error"),
                        Outcome.ERROR},
                {OKResponse.fromMessage("OK"),
                        Outcome.OK},
                {WarnResponse.fromMessage("Warn"),
                        Outcome.WARN},
                {ExceptionResponse.fromException(new Exception("Error")),
                        Outcome.ERROR},
                {new DefaultResponse("OK",
                                     "detalle ok",
                                     Outcome.OK.getValue()), Outcome.OK},
                {new DefaultResponse("Error",
                                     "detalle e",
                                     Outcome.ERROR.getValue()), Outcome.ERROR},
                {new DefaultResponse("Warn",
                                     "detalle w",
                                     Outcome.WARN.getValue()), Outcome.WARN},
                {new OKResponse("OK"), Outcome.OK},
                {new ErrorResponse("Error"), Outcome.ERROR},
                {new NullResponse(), Outcome.NONE},
                {new WarnResponse("Warm"), Outcome.WARN}};
    }


    @Test(dataProvider = "cases")
    public void testValidateList(IResponse response,
                                 Outcome outcome) {
        Assert.assertEquals(response.getOutcome(),
                            outcome);
    }
}
