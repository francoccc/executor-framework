package com.yqlzy.executor.framework;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

public class TestDisruptor {

    public static class MessageEvent {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class MessageEventFactory implements EventFactory<MessageEvent> {

        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }


    public static class MessageTranslator implements EventTranslatorOneArg<MessageEvent, String> {

        @Override
        public void translateTo(MessageEvent event, long sequence, String arg0) {
            event.setMessage(arg0);
        }
    }

    public static class MessageThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Simple Disruptor Test Thread");
        }
    }

    public static class MessageEventHandler implements EventHandler<MessageEvent> {

        @Override
        public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println(event.getMessage());
        }
    }


    public static class MessageExceptionHandler implements ExceptionHandler<MessageEvent> {

        @Override
        public void handleEventException(Throwable ex, long sequence, MessageEvent event) {

        }

        @Override
        public void handleOnStartException(Throwable ex) {

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {

        }
    }

    /**
     * 消费者生产类
     */
    public static class MessageEventProducer{
        private RingBuffer<MessageEvent> ringBuffer;

        public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        /**
         * 将接收到的消息输出到ringBuffer
         * @param message
         */
        public void onData(String message){
            EventTranslatorOneArg<MessageEvent,String> translator = new MessageTranslator();
            ringBuffer.publishEvent(translator, message);
        }
    }


    public static void main(String[] args) {
        String message = "Hello Disruptor!";
        int ringBufferSize = 1024;//必须是2的N次方
        Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(),ringBufferSize,new MessageThreadFactory(), ProducerType.SINGLE,new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);
        producer.onData(message);
    }
}
