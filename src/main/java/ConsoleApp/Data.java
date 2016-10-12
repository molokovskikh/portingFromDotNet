package ConsoleApp;

/**
 * Created by s.molokovskikh on 12.10.2016.
 */
public class Data implements DataExtension {

    private static final int OFFSET_NUMBER1 = 0;
    private static final int OFFSET_NUMBER2 = 4;

    private  final byte [] structData;

    public Data(){
        structData = new byte[]{ 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 };
    }

    private void setNumber(int number,int offset) {
        int signum = Integer.signum(number);
        if(signum==0){

        }
        for(int i = 0; i < Integer.BYTES; i++){
            structData[ i + offset ] =  (byte) ( 0xFF & (number >>> (i*8)) );
        }
    }

    private int getNumber(int offset) {
        int number = 0;
        for(int i = 0; i < Integer.BYTES; i++){
            number |=  ((structData[ i + offset ] & ( i < 3 ? 0x0FF : structData[ i + offset ])) << i*8);
        }
        return number;
    }

    public void setNumber1(int number) {
       setNumber(number, OFFSET_NUMBER1);
    }

    public int getNumber1() {
      return getNumber(OFFSET_NUMBER1);
    }

    public void setNumber2(int number) {
        setNumber(number, OFFSET_NUMBER2);
    }

    public int getNumber2() {
        return getNumber(OFFSET_NUMBER2);
    }

    @Override
    public String toString() {
        return String.format("Number 1: %d Number2: %d", this.getNumber1(), this.getNumber2());
    }

    @Override
    public int Summarize(Data data) {
        Data data1 = this;
        int dataNumber1 = data.getNumber1() * data.getNumber2();
        int sum = data1.getNumber1() * data1.getNumber2() + dataNumber1;
        return sum;
    }

    /* Source on .NET
     public struct Data
    {
        public int Number1
        {
            get;
            set;
        }

        public int Number2
        {
            get;
            set;
        }

        public override string ToString()
        {
            return "Number1: " + this.Number1 + " Number2: " + this.Number2;
        }
    }
     */
}
