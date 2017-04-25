package foolkey.pojo.send_to_client;

import javax.persistence.Transient;

/**
 * Created by geyao on 2017/4/25.
 */
public abstract class AbstractDTO {
    @Transient
    private Boolean flag1;
    @Transient
    private Boolean flag2;
    @Transient
    private Boolean flag3;

    public Boolean getFlag1() {
        return flag1;
    }

    public void setFlag1(Boolean flag1) {
        this.flag1 = flag1;
    }

    public Boolean getFlag2() {
        return flag2;
    }

    public void setFlag2(Boolean flag2) {
        this.flag2 = flag2;
    }

    public Boolean getFlag3() {
        return flag3;
    }

    public void setFlag3(Boolean flag3) {
        this.flag3 = flag3;
    }
}
