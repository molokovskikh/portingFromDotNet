package ConsoleApp;

import org.junit.Assert;
import sun.misc.Unsafe;

import java.lang.reflect.Field;


/**
 * Created by s.molokovskikh on 12.10.2016.
 */
public class Class2 {

    private DataHandler dataGetter;

    public DataHandler getDataGetter() {
        return dataGetter;
    }

    public void setDataGetter(DataHandler dataGetter) {
        this.dataGetter = dataGetter;
    }

    public void ExecuteDataGetter(){
        if(this.getDataGetter() != null){
            byte result = this.proccess();
            this.getDataGetter().invoke(result);
        }
    }

    private static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            return unsafe;
        } catch (NoSuchFieldException|IllegalAccessException e) {
            throw new SecurityException();
        }
    }


    private byte proccess(){

        byte val = 0;
        Class1 class1 = new Class1();
        Data[] d = class1.getData(10);
        for ( int i = 0; i < 6; i++ ){
            d[i].setNumber1(i * 10);
            d[i].setNumber2( - (i % 3) - 1 );
        }

        int size = Integer.BYTES * 5;

        Unsafe unsafe = getUnsafe();
        long r = unsafe.allocateMemory(size);

        try {
            long p = r;

            for (int i = 0; i < 10; i += 2){
                int sum = d[i].Summarize( d[ i + 1 ]);
                Assert.assertTrue( d[ i + 1].getNumber1() >= 0);
                unsafe.putInt(p,sum);
                p += Integer.BYTES;
            }

            long b = r;
            for (int i = 0; i < size; i++)
            {
                val ^= unsafe.getByte(b);
                b++;
            }
        }
        catch(Exception e) {
            unsafe.freeMemory(r);
        }

        return val;
    }

/*  Source on .NET

    public delegate void DataHandler(byte result);

    public event DataHandler DataGetter;

    public void ExecuteDataGetter()
    {
        if (this.DataGetter != null)
        {
            byte result = this.Process();
            this.DataGetter.Invoke(result);
        }
    }

    private unsafe byte Process()
    {
        byte val = 0;
        Class1 class1 = new Class1();
        var d = class1.GetData(10);
        for (int i = 0; i < 6; i++)
        {
            d[i].Number1 = i * 10;
            d[i].Number2 = -(i % 3) - 1;
        }

        int size = Marshal.SizeOf(typeof(int)) * 5;
        IntPtr r = Marshal.AllocHGlobal(size);
        try
        {
                int* p = (int*)r.ToPointer();
            for (int i = 0; i < 10; i += 2)
            {
                int sum = d[i].Summarize(d[i + 1]);
                Assert.IsTrue(d[i + 1].Number1 >= 0);
                    *p = sum;
                p++;
            }

                byte* b = (byte*)r.ToPointer();
            for (int i = 0; i < size; i++)
            {
                val ^= *b;
                b++;
            }
        }
        finally
        {
            Marshal.FreeHGlobal(r);
        }

        return val;
    }
  */
}
