package com.virdis.producer
import java.util.{ UUID, Properties }
import kafka._
import kafka.producer._
import kafka.message._

case class KProducer(

                      topic: String,
                      brokerList: String,
                      clientId: String = UUID.randomUUID().toString,
                      synchronously: Boolean = true,
                      compress: Boolean = true,

                      batchSize:Int  = 200,
                      messageSendMaxRetries: Integer = 3,
                      requestRequiredAcks: Integer = -1

                      ) {


  val props = new Properties()

  val codec = if(compress) DefaultCompressionCodec.codec else NoCompressionCodec.codec
  props.put("compression.codec", codec.toString)
  props.put("producer.type", if(synchronously) "sync" else "async")
  props.put("metadata.broker.list", brokerList)
  props.put("batch.num.messages", batchSize.toString)
  props.put("message.send.max.retries", messageSendMaxRetries.toString)
  props.put("request.required.acks",requestRequiredAcks.toString)
  props.put("client.id",clientId.toString)

  val producer = new Producer[AnyRef, AnyRef](new ProducerConfig(props))

  def kafkaMessage(message: Array[Byte], partition: Array[Byte]): KeyedMessage[AnyRef, AnyRef] = {
    if (partition == null)
      new KeyedMessage(topic, message)
    else
      new KeyedMessage(topic, partition, message)
  }


  def send(message: String, partition: String = null): Unit = send(message.getBytes("UTF8"), if (partition == null) null else partition.getBytes("UTF8"))

  def send(message: Array[Byte], partition: Array[Byte]): Unit = {
    try {
      producer.send(kafkaMessage(message, partition))
    } catch {
      case e: Exception =>
        e.printStackTrace
        System.exit(1)
    }
  }

}
