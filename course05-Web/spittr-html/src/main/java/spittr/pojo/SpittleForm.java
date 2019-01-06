package spittr.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class SpittleForm {

    @NotNull(message = "内容不可为空")
    @Size(min = 1, max = 140, message = "超出最大长度140字符")
    private String message;

    @Min(value = -90, message = "无效数据")
    @Max(value = 90, message = "无效数据")
    private Double latitude;

    @Min(value = -180, message = "无效数据")
    @Max(value = 180, message = "无效数据")
    private Double longitude;
}
