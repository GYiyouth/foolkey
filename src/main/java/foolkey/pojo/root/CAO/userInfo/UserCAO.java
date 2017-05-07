package foolkey.pojo.root.CAO.userInfo;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.vo.assistObject.RoleEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.pojo.root.vo.dto.TeacherDTO;
import foolkey.tool.cache.Cache;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by geyao on 2017/4/27.
 */
@Component(value = "userCAO")
public class UserCAO extends AbstractCAO{


    /**
     * 在缓存中开辟一个新的用户缓存区
     * @param token
     */
    public void initUserCache(String token){
        Map userMap = new HashedMap();
        Map keyMap = new HashedMap();
        Map blackListMap = new HashedMap();

        userMap.put(keyToken, keyMap);
        userMap.put(blackListToken, blackListMap);

        cache.put(token, userMap);
    }

    /**
     * 把用户信息存储在缓存中，同时更新id_token的对称关系
     * @param token
     * @param studentDTO
     */
    public void saveStudentDTO(String token, StudentDTO studentDTO){
        saveIdToken(token, studentDTO.getId());
        Map map = getUserMap(token);
        map.put(userInfoToken, studentDTO);
        cache.put(token, map);
    }

    /**
     * 判断缓存中有无该用户的缓存
     * @param token
     * @return
     */
    public boolean containsUser(String token){
        return cache.isContainToken(token);
    }

    /**
     * 先问有没有该用户的缓存
     * 再问缓存有无该用户的StudentDTO
     *
     * @param token
     * @return
     */
    public boolean containStudentDTO(String token){
        if ( containsUser(token)) {
            Map userMap = getUserMap(token);
            return (userMap.containsKey(userInfoToken));
        }
        return false;
    }

    /**
     * 从缓存中获取用户 StudentDTO
     * @param token
     * @return
     */
    public StudentDTO getStudentDTO(String token){
        Map userMap = getUserMap(token);
        return  (StudentDTO) (userMap.get(userInfoToken));
    }

    /**
     * 从缓存中获取用户缓存区
     * @param token
     * @return
     */
    public Map getUserMap(String token){
        return cache.getMap(token);
    }

    /**
     * 存储用户的对称密钥
     * @param token
     * @param AESKey
     */
    public void saveStudentAESKey(String token, String AESKey){
        Map userMap = cache.getMap(token);
        Map keyMap = new HashMap();
        keyMap.put(aesKeyToken, AESKey);
        userMap.put(keyToken, keyMap);
    }

    /**
     * 清除某个用户的缓存区
     * @param token
     */
    public void removeUserCache(String token){
        cache.getCache().remove(token);
    }

    /**
     * 根据id获取token
     * @param id
     */
    public String getUserToken(Long id){
        Map idTokenMap = cache.getMap(studentId_token);
        return idTokenMap.get(id).toString();
    }

    /**
     * 根据token获取id
     * @param token
     * @return
     */
    public Long getUserId(String token){
        Map tokenIdMap = cache.getMap(studentToken_id);
        return (Long) tokenIdMap.get(token);
    }

    /**
     * 在缓存中保存id与token的对应关系
     * @param token
     * @param id
     * @return
     */
    public void saveIdToken(String token, Long id){
        Map id_tokenMap = cache.getMap(studentId_token);
        Map token_idMap = cache.getMap(studentToken_id);
        id_tokenMap.put(id, token);
        token_idMap.put(token, id);
    }

    public void saveTeacherDTO(String token, TeacherDTO teacherDTO){
        Map map = getUserMap(token);
        map.put(teacherInfoToken, teacherDTO);
        cache.put(token, map);
    }
}
