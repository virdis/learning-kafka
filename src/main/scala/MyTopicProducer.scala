package com.virdis.learningkafka

import java.util.UUID

import com.virdis.producer.{PConfig, KafkaProducer}
import kafka.message.DefaultCompressionCodec

/**
 * Created by sandeep on 6/14/15.
 */

object MyTopicProducer extends KafkaProducer {

  override val config = new PConfig {
    override def brokerList: String = "localhost:9092"

    override def retries: Int = 3

    override def compression: String = DefaultCompressionCodec.codec.toString

    override def synch: String = "sync"

    override def batchSize: Int = 200

    override def topic: String = "myTopic"

    override def clientId: String = UUID.randomUUID().toString

    override def maxTimeToBlockInAsyncMode: Int = -1
  }
}