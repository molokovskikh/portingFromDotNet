package ConsoleApp;

/**
 * Created by s.molokovskikh on 12.10.2016.
 */
public class Class1 {

    public Data[] getData(int count) {

        Data[] result = new Data[count];
        for (int i=0; i < count; i++){
            result[i] = new Data();
        }
        return result;
    }
    /*
    Source in .NET
        public Data[] GetData(int count)
        {
            return new Data[count];
        }
     */
}
