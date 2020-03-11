package com.zhengyuanfang.mapper;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.out.OrderListOutDTO;
import com.zhengyuanfang.po.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Page<OrderListOutDTO> findAll();

    Page<OrderListOutDTO> findAll(@Param("orderId") Long orderId,
                                  @Param("status") Byte status,
                                  @Param("totalPrice") Double totalPrice,
                                  @Param("customerName") String customerName,
                                  @Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime);
}