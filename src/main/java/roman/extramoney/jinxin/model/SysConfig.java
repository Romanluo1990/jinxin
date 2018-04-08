package roman.extramoney.jinxin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import roman.extramoney.jinxin.model.common.Identify;

import javax.persistence.*;

@Data
@Table(name = "sys_config")
public class SysConfig implements Identify {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "`key`")
    @ApiModelProperty(value = "配置key",example = "amountAvailable")
    private String key;

    @Column(name = "`value`")
    @ApiModelProperty(value = "配置value",example = "1000")
    private String value;

}