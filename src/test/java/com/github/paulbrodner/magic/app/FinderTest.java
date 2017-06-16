package com.github.paulbrodner.magic.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration("classpath:magic-context.xml")
public class FinderTest extends AbstractTestNGSpringContextTests
{

    @Autowired
    Finder finder;

    @Test
    public void testOnFinder() throws Exception
    {
        Assert.assertNotNull(finder);
    }
}
