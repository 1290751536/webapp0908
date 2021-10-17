import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class tmp {
    public static void main(String[] args) {
        String tmp = UUID.randomUUID().toString();
        String s = "123.png";
        s = s.substring(s.lastIndexOf("."));
        System.out.println(tmp + s);
    }
}
