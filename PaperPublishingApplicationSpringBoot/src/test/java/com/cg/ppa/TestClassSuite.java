package com.cg.ppa;

import org.junit.runner.RunWith;

import com.cg.ppa.tests.CategoryTest;
import com.cg.ppa.tests.NewsTest;
import com.cg.ppa.tests.PaperTest;
import com.cg.ppa.tests.UserTest;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;

@RunWith(JUnitPlatform.class)
@SelectClasses({ CategoryTest.class, NewsTest.class, PaperTest.class, UserTest.class })
public class TestClassSuite {

}
