package model.xml;

import common.Constants;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name= Constants.ORDER)
public class OrderXML {
    @XmlElement(name=Constants.ID)
    private int id;
    @XmlElement(name=Constants.ORDER_ITEM)
    private List<OrderItemXML> orderItems;
}
