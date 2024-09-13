package com.hmall.trade.listenner;

import com.hmall.api.client.TradeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * {@code @Author} 19667
 * {@code @create} 2024/9/13 13:57
 */
@Component
@RequiredArgsConstructor
public class PayStatusListener {
    private final TradeClient tradeClient;
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "trade.pay.success.queue",durable = "true"),
            exchange = @Exchange("pay.direct"),
            key = "pay.success"
    ))
    public void listenPaySuccess(Long orderId){
        tradeClient.markOrderPaySuccess(orderId);
    }
}
