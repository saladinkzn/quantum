import com.google.gson.Gson;

import java.util.List;

/**
 * @author sala
 */
public class TestGSON {
    public static void main(String[] args) {
        String json = "[\"main :: (b a c ) -> (b a c )\\nmain (b a c ) = do \\n    a <- H a \\n    a c <- qnot a c \\n    a <- H a \\n\"]";
        Gson gson = new Gson();
        final List list = gson.fromJson(json, List.class);
        System.out.println(list);
    }
}
