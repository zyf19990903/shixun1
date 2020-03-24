package com.zhengyuanfang.es.repository;

import com.zhengyuanfang.es.doc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ProductDoc,Integer> {
}
