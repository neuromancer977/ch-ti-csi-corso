package com.manning.sbia.ch06.custom;

import java.text.DecimalFormatSymbols;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manning.sbia.ch06.file.AssertLine;

/**
 * @author bazoud
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JobCompositeItemWriterTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    char decimalSeparator = DecimalFormatSymbols.getInstance().getDecimalSeparator();
    
    @Test
    public void testFixedWidthBeanWrapperFieldExtractor() throws Exception {
        JobExecution exec = jobLauncherTestUtils.launchJob();
        Assert.assertEquals(BatchStatus.COMPLETED, exec.getStatus());

        Resource ouput= new FileSystemResource("./target/outputs/composite-pipeseparator.txt");
        AssertLine.assertLineFileEquals(ouput, 1, "PR....210|124.60|BlackBerry 8100 Pearl");
        AssertLine.assertLineFileEquals(ouput, 7, "PR....216|289.20|AT&T 8525 PDA");
        AssertLine.assertLineFileEquals(ouput, 8, "PR....217|13.70|Canon Digital Rebel XT 8MP");

        ouput= new FileSystemResource("./target/outputs/composite-beanwrapperextractor.txt");
        AssertLine.assertLineFileEquals(ouput, 1, "PR....210124" + decimalSeparator + "60BlackBerry 8100 Pearl         ");
        AssertLine.assertLineFileEquals(ouput, 7, "PR....216289" + decimalSeparator + "20AT&T 8525 PDA                 ");
        AssertLine.assertLineFileEquals(ouput, 8, "PR....217 13" + decimalSeparator + "70Canon Digital Rebel XT 8MP    ");
    }

}
