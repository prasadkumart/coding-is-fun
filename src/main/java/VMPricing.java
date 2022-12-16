import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Arrays;

public class VMPricing {

    public static String interpolate(int n, List<Integer> instances, List<Float> prices)
    {
        // find the closets cords for n
        List<Integer> res = findClosestElements(instances, n);

        // using y=mx+b to find the next co-ordinate
        Map<Integer,Float> hm = new HashMap<>();
        for(int i=0;i<instances.size();++i )
        {
            hm.put(instances.get(i), prices.get(i));
        }
        int x1= res.get(0);
        int x2 = res.get(1);
        Float y1 = hm.get(x1);
        Float y2 = hm.get(x2);
        Float m = (y2-y1)/(x2-x1);
        Float y = m*(n-x1)+y1;

        DecimalFormat df = new DecimalFormat("0.00");
        //df.setRoundingMode(RoundingMode.UP);
        return df.format(y);
    }

    public static List<Integer> findClosestElements( List<Integer> instances, int x) {
        // Initialize binary search bounds
        int k = 2;
        int left = 0;
        int right = instances.size()-k;
        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - instances.get(mid) > instances.get(mid + k) - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // Create output in correct format
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++) {
            result.add(instances.get(i));
        }
        return result;
    }

    public static String interpolate1(int n, List<Integer> instances, List<Float> price) {
        if (null == instances || instances.isEmpty() || null == price || price.isEmpty()) {
            return null;
        }

        return calculatePrice(instances.stream().mapToInt(Integer::intValue).toArray(), getFloats(price), n);
    }
    private static String calculatePrice(int[] instances, float[] price, int n) {
        //if (n < instances[0]) return String.valueOf(price[price.length-1]);

        DecimalFormat df = new DecimalFormat("0.00");
        int length = instances.length;

        if (n > instances[length-1]) {
            int i = n - instances[length-1];
            int j = instances[length-1] - instances[length-2];
            float diff = (float) i / j;
            System.out.println(diff);

            int priceLength = price.length;
            float p = price[priceLength-2] - price[priceLength-1];
            df.setRoundingMode(RoundingMode.UP);

            return  (df.format(price[priceLength-1] - (diff * p)));
        } else {
            for(int k =0; k<length;k++) {
                if (instances[k] == n) {
                    return String.valueOf(price[k]);
                }
                if (instances[k] > n) {
                    int i = n-instances[k-1];
                    int j = instances[k-1] - instances[k-2];
                    float diff = (float) i / j;
                    System.out.println(diff);

                    float p1 = price[k-2] - price[k-1];
                    df.setRoundingMode(RoundingMode.UP);
                    return  (df.format(price[k-1] - (diff * p1)));
                }
            }
        }

        return null;
    }

    public static float[] getFloats(List<Float> floatValues) {
        int length = floatValues.size();
        float[] result = new float[length];
        int i = 0;
        for (Float floatValue: floatValues) {
            result[i++] = floatValue.floatValue();
        }
        return result;
    }


    public static void main(String[] args) {

        Integer[] instances = {10,25,50,100,500};
        //double [] price = {2.46,2.58,2.0,2.25,3.0};
        Float[] price = {17.0f,18f,20f,22f,29.15f}; //19.20
        //Float[] price = {0.0f,0.0f,0.0f,0.0f,29.15f}; //19.20
        int n = 40;

        VMPricing vp = new VMPricing();
        //System.out.println(calculatePrice(instances, price, n)); // try 2000

        System.out.println(interpolate(n, Arrays.asList(instances), Arrays.asList(price)));
    }
}
