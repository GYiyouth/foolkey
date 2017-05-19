package foolkey.pojo.root.CAO.userInfo;

import com.alibaba.fastjson.JSON;
import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.CAO.key.KeyCAO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.cache.Cache;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by geyao on 2017/4/27.
 */
@Component(value = "userCAO")
public class UserCAO extends AbstractCAO{

    //获取用户的aesKey在缓存中的地址
    public static String getUserAESKeyAddress(String token){
        return KeyCAO.getUserAESKeyAddress( token );
    }

    //获取用户的StudentDTO地址
    public static String getUserStudentDTOAddress(String token){
        return token + Cache.separator + userInfoToken;
    }

    //获取用户的TeacherDTO地址
    public static String getUserTeacherDTOAddress(String token){
        return token + Cache.separator + teacherInfoToken;
    }

    //获取用户黑名单在缓存中的地址
    public static String getUserBlackListAddress(String token){
        return token + Cache.separator + blackListToken;
    }

    //根据id获取token所在的地址
    public static String getUserTokenAddress( Long id ){
        return id + Cache.separator + tokenToken;
    }


    /**
     * 在缓存中开辟一个新的用户缓存区
     * @param token
     */
    public void initUserCache(String token){
        //目前什么都不用做
    }

    /**
     * 把用户信息存储在缓存中，不会更新id_token的对称关系
     * @param token
     * @param studentDTO
     */
    public void saveStudentDTO(String token, StudentDTO studentDTO){
        String key = getUserStudentDTOAddress( token );
        String value = JSON.toJSONString( studentDTO );
        cache.set( key, value );
    }

    /**
     * 判断缓存中有无该用户的缓存
     * AES和studentDTO缺一不可
     * @param token
     * @return
     */
    public boolean containsUser(String token){
        boolean aesFlag = cache.isContainToken( getUserAESKeyAddress( token ) );
        boolean studentFlag = cache.isContainToken( getUserStudentDTOAddress( token ) );
        return aesFlag && studentFlag;
    }

    /**
     * 先问有没有该用户的缓存
     * 再问缓存有无该用户的StudentDTO
     *
     * @param token
     * @return
     */
    public boolean containStudentDTO(String token){
        String key = getUserStudentDTOAddress( token );
        return cache.isContainToken( key );
    }

    /**
     * 从缓存中获取用户 StudentDTO
     * @param token
     * @return
     */
    public StudentDTO getStudentDTO(String token){
        String key = getUserStudentDTOAddress( token );
        String value = cache.getString( key );
        return JSON.parseObject( value, StudentDTO.class );
    }


    /**
     * 存储用户的对称密钥
     * @param token
     * @param AESKey
     */
    public void saveStudentAESKey(String token, String AESKey){
        String key = getUserAESKeyAddress( token );
        cache.set( key, aesKeyToken );
    }

    /**
     * 清除某个用户的缓存区
     * @param token
     */
    public void removeUserCache(String token){
        cache.remove( getUserAESKeyAddress( token) );
        cache.remove( getUserStudentDTOAddress( token ));
        cache.remove( getUserTeacherDTOAddress( token ));
        cache.remove( getUserBlackListAddress( token ));
    }

    /**
     * 根据id获取token
     * @param id
     */
    public String getUserToken(Long id){
        String key = getUserTokenAddress( id );
        return cache.getString( key );
    }



    /**
     * 在缓存中保存id与token的对应关系
     * 只需要维护从id-token的索引关系
     * @param token
     * @param id
     * @return
     */
    public void saveIdToken(String token, Long id){
        String key = getUserTokenAddress( id );
        cache.set( key, token );
    }

    public void saveTeacherDTO(String token, TeacherDTO teacherDTO){
        String key = getUserTeacherDTOAddress( token );
        String value = JSON.toJSONString( teacherDTO );
        cache.set( key, value);
    }
}
