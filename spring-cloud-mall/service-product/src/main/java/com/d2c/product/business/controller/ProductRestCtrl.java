package com.d2c.product.business.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d2c.product.business.model.Product;
import com.d2c.product.business.service.ProductService;

@RestController
public class ProductRestCtrl {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/api/product", method = RequestMethod.GET)
    public Product findBySn(@RequestParam(value = "sn", required = true) String sn) {
        return productService.findBySn(sn);
    }

    @RequestMapping(value = "/api/product/cache", method = RequestMethod.GET)
    public Product findCacheBySn(@RequestParam(value = "sn", required = true) String sn) {
        return productService.findCacheBySn(sn);
    }

    @RequestMapping(value = "/api/product/update/{id}", method = RequestMethod.GET)
    public int updatePriceById(@PathVariable(name = "id") Long id, @RequestParam(value = "price", required = true) BigDecimal price) {
        return productService.updatePriceById(id, price);
    }

}
