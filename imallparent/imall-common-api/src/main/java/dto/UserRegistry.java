package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistry {

    private String name;

    private String phone;

    private String password;

    private String nickName;

    private String type;

}
