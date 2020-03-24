package com.zhengyuanfang.es.repository;

import com.zhengyuanfang.es.doc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<ProductDoc,Integer> {
    List<ProductDoc> findByProductNameLikeOrProductAbstractLike(String productName, String productAbstract);
}
