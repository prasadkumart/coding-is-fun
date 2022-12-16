import java.util.ArrayList;

public class BinaryStrings {
    static ArrayList<String> get_binary_strings(Integer n) {
        ArrayList<String> result = new ArrayList<>();

        dfs("", n, result);

        return result;
    }

    static void dfs(String slate, Integer n,ArrayList<String> result) {
        if (n == 0) {
            result.add(slate);
            return;
        }

        dfs(slate + "0", n-1, result);
        dfs(slate + "1", n-1, result);
    }

    public static void main(String[] args) {
        System.out.println(get_binary_strings(3));
    }
}
