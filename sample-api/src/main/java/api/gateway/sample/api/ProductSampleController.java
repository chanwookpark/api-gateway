package api.gateway.sample.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chanwook
 */
@RestController
public class ProductSampleController {

    @RequestMapping("/product")
    public Product getProduct() {
        return new Product("P001", "MacBook");
    }
}
