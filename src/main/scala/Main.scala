package com.virdis.learningkafka

import java.util.UUID

import com.virdis.producer.{PConfig, KafkaProducer}
import kafka.message.DefaultCompressionCodec


object Main {
  def main(args: Array[String]) {
    println("Hello World!!!")
    MyTopicProducer.send("This is a test message")
  }
}

