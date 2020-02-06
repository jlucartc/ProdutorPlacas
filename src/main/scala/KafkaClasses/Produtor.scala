package KafkaClasses

import org.apache.kafka.clients.producer._
import java.util.{Date, Properties, Random}
import java.util.concurrent.TimeUnit

class Produtor extends Thread{

    var letras: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    var placas = new Array[String](10)

    for( a <- 0 until (placas.length-1) ){

      placas(a) = gerarPlaca()

    }

    def gerarPlaca() : String = {

      var tamanhoLetras : Int = 3
      var tamanhoNumeros : Int = 4

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

      var rand : Random = new Random()

      var tuplas : Array[(String,Int)] = Array(("A",5),("B",1),("C",2),("D",10),("E",5),("F",6),("G",15),("H",9),("I",10),("J",20))

      while(true){

        val rand = new Random()

        val placa = placas(rand.nextInt(9))

        Thread.sleep(10)

        //var placa = gerarPlaca()

        val producer : Producer[String,String] = new KafkaProducer(props)

        val date = new Date()
        //var timestamp = new Timestamp(date.getTime())

        val record : ProducerRecord[String,String] = new ProducerRecord("placas",placa.toString+";1")

        producer.send(record)
        producer.close()

        println("Placa produzida: "+placa)

        TimeUnit.MILLISECONDS.sleep(rand.nextInt(10)*10)

      }

    }

    override def run(): Unit = {

      publicarPlaca()

    }

  }
