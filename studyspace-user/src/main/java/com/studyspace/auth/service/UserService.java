



import com.studyspace.common.result.ApiResult;

import java.util.Date;

//用于提供调用方法的路径
public interface UserService {
    public ApiResult getUserById(long id);
    public ApiResult getUserByUsername(String username);
    public ApiResult login(String username, String password);
    public ApiResult register(String username, String password, String email, Date created_at);
    public ApiResult deleteUser(long id);
    public ApiResult getAllUsers();
    public ApiResult insertUser(String username, String password, String email, String created_at);
    public ApiResult getCurrentUser(); // 获取当前登录用户信息

}
