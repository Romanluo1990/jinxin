package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import roman.extramoney.jinxin.model.common.Identify;

import java.util.Date;
import javax.persistence.*;

@Data
public class Account implements Identify{
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "open_id")
    @ApiModelProperty(value = "小程序openId",example = "123456789")
    private String openId;

    @Column(name = "nick_name")
    @ApiModelProperty(value = "用户昵称",example = "张宝宝")
    private String nickName;

    private String image;

    @Column(name = "phone")
    @ApiModelProperty(value = "电话",example = "18888888888")
    private String phone;

    @Column(name = "type")
    @ApiModelProperty(value = "类型",example = "1")
    private Integer type;

    /**
     * 状态（0:待审核 1:审核通过 2:审核不通过）
     */
    @ApiModelProperty(value = "状态（0:待审核 1:审核通过 2:审核不通)",example = "0")
    private Integer status;

    /**
     * 审核信息
     */
    @ApiModelProperty(value = "审核信息",example = "审核通过")
    private String statusMessage;

    @Column(name = "user_name")
    @ApiModelProperty(value = "用户名",example = "admin")
    private String userName;

    @ApiModelProperty(value = "密码",example = "123456")
    private String password;

    @ApiModelProperty(value = "加盐",example = "123456")
    private String salt;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间",example = "2018-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String creator;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间",example = "2018-01-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updator;

}