package foolkey.pojo.root.CAO.key;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.tool.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 最小健壮性，最大性能
 * Created by geyao on 2017/4/26.
 */
@Component("keyCAO")
public class KeyCAO extends AbstractCAO{

    /**
     * 判断是否含服务器 RSA key
     * @return
     */
    public boolean containsServerRSAKey() {
        Map keyMap = cache.getMap(keyToken);
        return keyMap != null && keyMap.containsKey(rsaKeyDTOToken);
    }

    /**
     * 获取RSA key
     * @return
     */
    public RSAKeyDTO getServerRSAKey(){
        Map keyMap = cache.getMap(keyToken);
        return (RSAKeyDTO) keyMap.get(rsaKeyDTOToken);
    }

    /**
     * 判断缓存中是否有一个用户的 aes key
     * @param userToken
     * @return
     */
    public boolean containsAESKey(String userToken){
        Map userMap = cache.getMap(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        return keyMap != null && keyMap.containsKey(aesKeyToken);
    }

    /**
     * 判断缓存中是否有一个用户的 rsa key
     * @param userToken
     * @return
     */
    private boolean containsRSAKey(String userToken){
        Map userMap = cache.getMap(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        return keyMap != null && keyMap.containsKey(rsaKeyDTOToken);
    }

    /**
     * 获取一个用户的 rsa key DTO
     * @param userToken
     * @return
     */
    private RSAKeyDTO getUserRSAKeyDTO(String userToken){
        Map userMap = cache.getMap(userToken);
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
        Map userMap = cache.getMap(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        if (keyMap != null)
            return (String) keyMap.get(aesKeyToken);
        return null;
    }




    /**
     * 更新服务器 rsa key 缓存
     * @param newRSAKeyDTO
     * @return
     */
    public RSAKeyDTO updateServerRSAKeyDTO(RSAKeyDTO newRSAKeyDTO){
        Map keyMap = cache.getMap(keyToken);
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
        Map userMap = cache.getMap(userToken);
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
        Map userMap = cache.getMap(userToken);
        Map keyMap = (Map) userMap.get(keyToken);
        keyMap.put(aesKeyToken, newAESKey);
        return newAESKey;
    }
}
