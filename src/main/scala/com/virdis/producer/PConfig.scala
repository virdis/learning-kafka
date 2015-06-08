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
    new ProducerConfig(
      new Properties()
    )
  }
}