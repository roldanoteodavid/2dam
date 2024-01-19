package model.xml;

import common.Constants;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = Constants.ORDER_ITEM)
public class OrderItemXML {
    @XmlElement(name = Constants.MENU_ITEM)
    private String menu_item;
    @XmlElement(name = Constants.QUANTITY)
    private int quantity;
}
