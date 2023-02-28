import com.galaxyed.Auth;
import com.galaxyed.IdSdk;

import java.util.Map;

import java.io.IOException;

public class TestAuth {
    private Auth auth = new Auth("f3352c2a-b6a3-11ed-afa1-0242ac120002", "fa1e328e-b6a3-11ed-afa1-0242ac120002");
    private IdSdk idSdk = IdSdk.getInstance("f3352c2a-b6a3-11ed-afa1-0242ac120002", "fa1e328e-b6a3-11ed-afa1-0242ac120002");
    private String muid = "000aa565-ee61-41d7-afa4-2c13947d4929";

    public void testPerformAuthentication() throws IOException {
        auth.authenticate();
    }

    public Map<String, Object> testAuthAndGetUserInfo() throws IOException {
        return idSdk.authAndGetUserInfo(this.muid);
    }
}
