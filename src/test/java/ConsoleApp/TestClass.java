package ConsoleApp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by s.molokovskikh on 12.10.2016.
 */
@RunWith(JUnit4.class)
public class TestClass implements DataHandler {

    /* Source on .NET
     [Test]
        public void TestMe()
        {
            Class2 class2 = new Class2();
            class2.DataGetter += new Class2.DataHandler(class2_DataGetter);
            class2.ExecuteDataGetter();
        }

        void class2_DataGetter(byte result)
        {
            Assert.IsTrue(result == 175);
        }
     */

    @Override
    public void invoke(byte result) {
        class2_DataGetter(result);
    }

    @Test
    public void TestMe()
    {
        Class2 class2 = new Class2();
        class2.setDataGetter(this);
        class2.ExecuteDataGetter();
    }

    void class2_DataGetter(byte result)
    {
        Assert.assertTrue((result & 0xFF) == 175);
    }

}
