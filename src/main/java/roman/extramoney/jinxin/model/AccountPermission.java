package roman.extramoney.jinxin.model;

import lombok.Data;
import roman.extramoney.jinxin.model.common.Identify;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "account_permission")
public class AccountPermission implements Identify {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "account_id")
    private Long accountId;

    /**
     * 权限标识
     */
    private String permission;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "update_time")
    private Date updateTime;

    private String updator;

}