package com.virdis.producer


import kafka.producer.{KeyedMessage, Producer}

/**
 * Created by sandeep on 6/7/15.
 */
trait KafkaProducer extends ConfigBuilder {

  lazy val producer: Producer[AnyRef, AnyRef] = new ConfigBuilder{}.apply(config)
  def config: PConfig

  def send(message: String, partition: String = null) = {
    _send(message.getBytes("UTF-8"), if (partition == null) null else partition.getBytes("UTF-8"))
  }

  private [this] def _send(msg: Array[Byte], part: Array[Byte]) = {
    try {
      if (part == null) producer.send(new KeyedMessage(config.topic, msg)) else producer.send(new KeyedMessage(config.topic, part, msg))
    } catch {
      case e: Exception =>
        e.printStackTrace
        System.exit(1)
    }

  }

}
