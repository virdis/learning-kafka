package com.virdis.producer

import java.util.Properties

import kafka.producer.ProducerConfig

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

trait ConfigBuiler extends (PConfig => ProducerConfig) {

  def apply(pconfig: PConfig): ProducerConfig = {
    val props  =  new Properties()
    props.put("compression.codec", pconfig.compression)
    props.put("producer.type", pconfig.synch)
    props.put("metadata.broker.list", pconfig.brokerList)
    props.put("batch.num.messages", pconfig.batchSize.toString)
    props.put("message.send.max.retries", pconfig.retries.toString)
    props.put("request.required.acks", pconfig.maxTimeToBlockInAsyncMode.toString)
    props.put("client.id", pconfig.clientId)

    new ProducerConfig(props)
  }
}