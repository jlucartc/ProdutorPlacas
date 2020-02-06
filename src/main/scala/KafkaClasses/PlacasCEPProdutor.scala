package KafkaClasses

import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.apache.kafka.clients.producer._
import java.util.{Calendar, Date, Properties, Random}
import java.util.concurrent.TimeUnit

class PlacasCEPProdutor extends Thread {

  var props : Properties = new Properties()

  props.put("bootstrap.servers","localhost:9092")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks","1")

  override def run(): Unit ={
    while(true) {
      publicarPlaca()
    }
  }



  def publicarPlaca() {

    var props : Properties = new Properties()

    props.put("bootstrap.servers","localhost:9092")
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("acks","1")

    val producer : Producer[String,String] = new KafkaProducer(props)

    val rand = new Random()

    val record : ProducerRecord[String,String] = new ProducerRecord("cep-placas","2019-12-16 "+"1"+rand.nextInt(9).toString+":20:36"+";0.0000;0.0000")

    producer.send(record)

    producer.close()

    //println("Placa produzida: "+placa)

    TimeUnit.MILLISECONDS.sleep(50)

  }

}
