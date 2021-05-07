package it.edu.demo.utils;

import java.math.BigInteger;

/**
 * User: William Cheng
 * Create Time: 2018/9/24 16:23
 * Description:
 */
public class CalcUtil {

    public static BigInteger calculateFactorial(BigInteger param) {

        return cal(param);
    }

    private static BigInteger cal(BigInteger param) {
        if (param.intValue() == 1) {
            return param;
        }

        BigInteger result = new BigInteger(param.toString());

        BigInteger subtract = result.subtract(new BigInteger("1"));

        result = result.multiply(cal(subtract));

        return result;
    }
}