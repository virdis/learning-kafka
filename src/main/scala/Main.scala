package com.virdis.learningkafka

import java.util.UUID

import com.virdis.producer.{PConfig, KafkaProducer}
import scala.io._



object Main {
  def main(args: Array[String]) {
    println("Lets send some messages....")
    println("type some message and hit return.")
    while(true){
      Source.stdin.getLines().foreach{
        line =>
          if (line.toString.contains("Quit") || line.toString.contains("q") || line.toString.contains("Q")) System.exit(0)
          else
            MyTopicProducer.send(line.toString)
      }
    }
  }
}

