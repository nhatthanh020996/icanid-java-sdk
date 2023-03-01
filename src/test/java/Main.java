import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TestAuth testAuth = new TestAuth();
//        System.out.println(testAuth.testPerformAuthentication());
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime expired_at = now.plusSeconds(604799);
//        System.out.println(expired_at);
        testAuth.testAuthAndGetUserInfo();

    }
}
