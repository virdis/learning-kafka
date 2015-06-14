package com.virdis.learningkafka

import java.util.UUID

import com.virdis.producer.{PConfig, KafkaProducer}
import kafka.message.DefaultCompressionCodec

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

object Main {
  def main(args: Array[String]) {
    println("Hello World!!!")
    MyTopicProducer.send("This is a test message")
  }
}

