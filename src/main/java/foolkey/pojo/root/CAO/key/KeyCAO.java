package foolkey.pojo.root.CAO.key;

import com.alibaba.fastjson.JSON;
import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.CAO.userInfo.UserCAO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.tool.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 最小健壮性，最大性能
 * Created by geyao on 2017/4/26.
 */
@Component("keyCAO")
public class KeyCAO extends AbstractCAO{

    @Autowired
    private UserCAO userCAO;

    //直接对应缓存中，服务器的RSAKeyDTO
    private String rsaKeyAddress = keyToken + Cache.separator + rsaKeyDTOToken;


    //根据用户的token，获取他的aesKey在缓存中的key
    public static String getUserAESKeyAddress(String token){
        return token + Cache.separator + keyToken + Cache.separator + aesKeyToken;
    }

    /**
     * 判断是否含服务器 RSA key
     * @return
     */
    public boolean containsServerRSAKey() {
        return cache.isContainToken(rsaKeyAddress);
    }

    /**
     * 获取RSA key
     * @return
     */
    public RSAKeyDTO getServerRSAKey(){
        String value = cache.getString(rsaKeyAddress);
        return JSON.parseObject( value, RSAKeyDTO.class);
    }

    /**
     * 判断缓存中是否有一个用户的 aes key
     * @param userToken
     * @return
     */
    public boolean containsAESKey(String userToken){
        String key = getUserAESKeyAddress( userToken );
        return cache.isContainToken( key );
    }

    /**
     * 获取一个用户的 aes key
     * @param userToken
     * @return base64 格式
     */
    public String getUserAESKeyBase64(String userToken){
        String key = getUserAESKeyAddress( userToken );
        return cache.getString( key );
    }




    /**
     * 更新服务器 rsa key 缓存
     * @param newRSAKeyDTO
     * @return
     */
    public void updateServerRSAKeyDTO(RSAKeyDTO newRSAKeyDTO){
        String rsaKeyDTOStr = JSON.toJSONString( newRSAKeyDTO );
        cache.set(rsaKeyAddress, rsaKeyDTOStr );
    }

    /**
     * 更新用户 aes key 缓存
     * @param newAESKey
     * @param userToken
     * @return
     */
    public void updateUserAESKey(String newAESKey, String userToken){
        String key = getUserAESKeyAddress( userToken );
        cache.set( key, newAESKey );
    }
}
