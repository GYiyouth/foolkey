package foolkey.pojo.root.DAO.com;

import foolkey.pojo.root.vo.assistObject.RSAKeyDTO;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.ConverterByteBase64;
import foolkey.tool.cache.Cache;
import foolkey.tool.security.RSACoder;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by geyao on 2017/4/26.
 */
@Repository("rsaKeyDAO")
public class RSAKeyDAO {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    @Resource(name = "rsaCoder")
    private RSACoder rsaCoder;
    @Resource(name = "localCache")
    private Cache cache;

    private static final String token1 = "key";
    private static final String token2 = "rsaKeyDTO";


    public RSAKeyDAO() {
        super();
    }

    private RSAKeyDTO addRSAKey() throws Exception{
        byte[] pubbytes = rsaCoder.getPublicKey();
        byte[] pribyte = rsaCoder.getPrivateKey();
        RSAKeyDTO rsaKeyDTO = new RSAKeyDTO();
        String pubStr = ConverterByteBase64.byte2Base64(pubbytes);
        String priStr = ConverterByteBase64.byte2Base64(pribyte);
        rsaKeyDTO.setPubBase64Str(pubStr);
        rsaKeyDTO.setPriBase64Str(priStr);
        rsaKeyDTO.setId(1L);
        hibernateTemplate.update(rsaKeyDTO);
        return rsaKeyDTO;
    }

    /**
     * 从数据库获取服务器端的DTO
     * @return
     * @throws Exception
     */
    public RSAKeyDTO getServerRSAKeyDTO() throws Exception{
        RSAKeyDTO rsaKeyDTO = hibernateTemplate.get(RSAKeyDTO.class, 1L);
        if (rsaKeyDTO == null) {
            System.out.println("未从数据库取到密钥  " + this.getClass() );
            rsaKeyDTO = addRSAKey();
        }
        return rsaKeyDTO;
    }

    /**
     * 从数据库获取userId对应的RSAKeyDTO
     * @param userId
     * @return
     * @throws Exception
     */
    private RSAKeyDTO getUserRSAKeyDTO(Long userId) throws Exception{
        RSAKeyDTO rsaKeyDTO;
        List<RSAKeyDTO> list = (List<RSAKeyDTO>)
                hibernateTemplate.find("from RSAKeyDTO r where  r.userId = ? order by r.id desc ", userId);
        if (list.size() > 0) { // 数据库里取到了
            rsaKeyDTO = list.get(0);
        }else { // 数据库没取到
            //不能新建，因为这个是app传来的
        }
        return null;
    }

    /**
     * 仅负责持久一个DTO
     * @param rsaKeyDTO
     * @throws Exception
     */
    private void saveRSAKeyDTO(RSAKeyDTO rsaKeyDTO) throws Exception{
        hibernateTemplate.save(rsaKeyDTO);
    }
}
