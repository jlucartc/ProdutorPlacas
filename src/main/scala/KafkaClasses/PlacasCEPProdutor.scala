package KafkaClasses

import java.sql.Timestamp

import org.apache.kafka.clients.producer._
import java.util.{Date, Properties, Random}
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

  def gerarPlaca() : String = {

    val tamanhoLetras : Int = 3
    val tamanhoNumeros : Int = 4
    val letras: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    var i : Int = 0
    var palavra : String = ""

    var rand : Random = new Random()

    for( i <- 0 until tamanhoLetras){

      var numero = rand.nextInt(5)

      palavra+= letras.substring(numero,numero+1)


    }

    palavra+="-"

    for(i <- 0 until tamanhoNumeros){

      var numero = rand.nextInt(5)

      palavra+= Integer.toString(numero)

    }

    palavra

  }

  def publicarPlaca() {

    var props : Properties = new Properties()

    props.put("bootstrap.servers","localhost:9092")
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("acks","1")

    var i = 0

    //var tuplas : Array[(String,Int)] = Array(("A",5),("B",1),("C",2),("D",10),("E",5),("F",6),("G",15),("H",9),("I",10),("J",20))

    val rand = new Random()

    //val placa = placas(rand.nextInt(10))

    Thread.sleep(10)

    var placa = gerarPlaca()

    val producer : Producer[String,String] = new KafkaProducer(props)

    //val date = new Date()

    val hora = rand.nextInt(15)

    val minuto = rand.nextInt(59)

    val data = "2019-11-19 "+hora.toString+":"+minuto.toString+":"+"00"

    val timestamp = Timestamp.valueOf(data)

    val lat = rand.nextFloat()*100

    val long = rand.nextFloat()*100

    val record : ProducerRecord[String,String] = new ProducerRecord("placas-cep",placa+";"+timestamp.getTime.toString+";"+lat+";"+long)

    producer.send(record)

    producer.close()

    //println("Placa produzida: "+placa)

    TimeUnit.MILLISECONDS.sleep(50)

  }

}
