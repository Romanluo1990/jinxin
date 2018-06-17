package roman.extramoney.jinxin.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class AccountVo {

    @ApiModelProperty(value = "用户昵称",example = "张宝宝")
    private String nickName;

    private String image;

    @Column(name = "phone")
    @ApiModelProperty(value = "电话",example = "18888888888")
    private String phone;

}
