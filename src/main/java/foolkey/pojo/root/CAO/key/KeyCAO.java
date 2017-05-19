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

    //直接对应缓存中的RSAKeyDTO
    private String rsaKey = keyToken + Cache.separator + rsaKeyDTOToken;

    /**
     * 判断是否含服务器 RSA key
     * @return
     */
    public boolean containsServerRSAKey() {
        return cache.isContainToken( rsaKey );
    }

    /**
     * 获取RSA key
     * @return
     */
    public RSAKeyDTO getServerRSAKey(){
        String value = cache.getString(rsaKey);
        RSAKeyDTO rsaKeyDTO = JSON.parseObject( value, RSAKeyDTO.class);
        return rsaKeyDTO;
    }

    /**
     * 判断缓存中是否有一个用户的 aes key
     * @param userToken
     * @return
     */
    public boolean containsAESKey(String userToken){
        if (cache.isContainToken(userToken)) {
            Map userMap = cache.getString(userToken);
            Map keyMap = (Map) userMap.get(keyToken);
            return keyMap != null && keyMap.containsKey(aesKeyToken);
        }else{
            userCAO.initUserCache(userToken);
            return false;
        }

    }

    /**
     * 判断缓存中是否有一个用户的 rsa key
     * @param userToken
     * @return
     */
    private boolean containsRSAKey(String userToken){
        Map userMap = cache.getString(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        return keyMap != null && keyMap.containsKey(rsaKeyDTOToken);
    }

    /**
     * 获取一个用户的 rsa key DTO
     * @param userToken
     * @return
     */
    private RSAKeyDTO getUserRSAKeyDTO(String userToken){
        Map userMap = cache.getString(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        if (keyMap != null)
            return (RSAKeyDTO) keyMap.get(rsaKeyDTOToken);
        return null;
    }

    /**
     * 获取一个用户的 aes key
     * @param userToken
     * @return base64 格式
     */
    public String getUserAESKeyDTO(String userToken){
        Map userMap = cache.getString(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        if (keyMap != null)
            return (String) keyMap.get(aesKeyToken);
        System.out.println("该用户没有keyMap");
        System.out.println(userMap);
        System.out.println(keyMap);
        return null;
    }




    /**
     * 更新服务器 rsa key 缓存
     * @param newRSAKeyDTO
     * @return
     */
    public RSAKeyDTO updateServerRSAKeyDTO(RSAKeyDTO newRSAKeyDTO){
        Map keyMap = cache.getString(keyToken);
        keyMap.put(rsaKeyDTOToken, newRSAKeyDTO);
        return newRSAKeyDTO;
    }

    /**
     * 更新用户的 rsa key 缓存
     * @param newRSAKeyDTO
     * @param userToken
     * @return
     */
    private RSAKeyDTO updateUserRSAKeyDTO(RSAKeyDTO newRSAKeyDTO, String userToken){
        Map userMap = cache.getString(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        keyMap.put(rsaKeyDTOToken, newRSAKeyDTO);
        return newRSAKeyDTO;
    }

    /**
     * 更新用户 aes key 缓存
     * @param newAESKey
     * @param userToken
     * @return
     */
    public String updateUserAESKey(String newAESKey, String userToken){
        Map userMap = cache.getString(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        keyMap.put(aesKeyToken, newAESKey);
        return newAESKey;
    }
}
