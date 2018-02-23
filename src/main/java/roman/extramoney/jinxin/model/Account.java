package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
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

    @Column(name = "user_name")
    @ApiModelProperty(value = "用户名",example = "admin")
    private String userName;

    @ApiModelProperty(value = "密码",example = "123456")
    private String password;

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