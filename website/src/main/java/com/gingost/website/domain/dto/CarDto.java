package com.gingost.website.domain.dto;

import com.gingost.website.domain.Car;
import com.gingost.website.domain.Item;
import com.gingost.website.domain.vo.ButtonCss;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/61:37
 **/

@Getter
@Setter
public class CarDto {
    private Integer id;
    private String itemName;
    private String itemLogo;
    private String itemNumCss;
    private Double itemPrice;
    private Double itemTotal;

    public CarDto(Car car,Item item){
        ButtonCss buttonCss=new ButtonCss(car.getItemNum());
        this.id=car.getId();
        //this.itemNumCss=buttonCss.getBtnLeft()+buttonCss.getInput()+buttonCss.getBtnRight();
        this.itemNumCss=buttonCss.getInput();
        this.itemName=item.getItemName();
        this.itemPrice=item.getItemPrice();
        this.itemLogo="<img src='"+item.getItemLogo()+"' alt='' width='150px'>";
        this.itemTotal=car.getItemTotal();
    }
}
