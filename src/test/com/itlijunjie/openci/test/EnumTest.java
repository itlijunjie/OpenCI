package com.itlijunjie.openci.test;

import com.itlijunjie.openci.util.enums.BuildStatusEnum;
import org.junit.Assert;
import org.junit.Test;

public class EnumTest {
    @Test
    public void test() {
        Assert.assertEquals("构建中", BuildStatusEnum.getName(BuildStatusEnum.Build.getValue()));
    }
}
