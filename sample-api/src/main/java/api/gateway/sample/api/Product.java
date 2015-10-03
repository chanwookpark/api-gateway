package api.gateway.sample.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chanwook
 */
@Getter
@Setter
@AllArgsConstructor
public class Product implements Serializable {

    private String productId;

    private String productName;

}
