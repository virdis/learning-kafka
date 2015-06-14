package com.virdis.producer

import java.util.Properties

import kafka.producer.{Producer, ProducerConfig}

/**
 * Created by sandeep on 6/7/15.
 */
trait PConfig {

  def topic: String
  def brokerList: String
  def clientId: String
  def synch: String
  def compression: String
  def batchSize: Int
  def retries: Int
  def maxTimeToBlockInAsyncMode: Int

}

trait ConfigBuilder extends (PConfig => Producer[AnyRef, AnyRef]) {

  def apply(pconfig: PConfig): Producer[AnyRef, AnyRef] = {
    val props  =  new Properties()
    props.put("compression.codec", pconfig.compression)
    props.put("producer.type", pconfig.synch)
    props.put("metadata.broker.list", pconfig.brokerList)
    props.put("batch.num.messages", pconfig.batchSize.toString)
    props.put("message.send.max.retries", pconfig.retries.toString)
    props.put("request.required.acks", pconfig.maxTimeToBlockInAsyncMode.toString)
    props.put("client.id", pconfig.clientId)

    new Producer[AnyRef, AnyRef](new ProducerConfig(props))
  }
}