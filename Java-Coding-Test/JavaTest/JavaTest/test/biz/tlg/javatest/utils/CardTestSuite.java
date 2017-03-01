package biz.tlg.javatest.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestSimpleCard.class,
   TestComplexCard.class
})

public class CardTestSuite {

}
