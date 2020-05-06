package com.gingost.website.domain.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/62:15
 **/

@Getter
@Setter
public class ButtonCss {
    private String btnLeft="<button id='del' style='border: 1px solid #ccc; width: 26px; height: 26px; line-height: 26px; padding: 0; text-align: center; background-color: #ededed;overflow: hidden;'>-</button>";
    private String input;
    private String btnRight="<button id='add' style='border: 1px solid #ccc; width: 26px; height: 26px; line-height: 26px; padding: 0; text-align: center; background-color: #ededed;overflow: hidden;'>+</button>";;

    public ButtonCss(Integer num){
        this.input="<input type='text' id='num' value='"+num+"' style='margin: 0; padding: 0; width: 48px; height: 26px;font-size: 16px; line-height: 26px; text-align: center; color: #666; border: 1px solid #ccc; outline: 0; background: #FFF;'>";
    }
}
