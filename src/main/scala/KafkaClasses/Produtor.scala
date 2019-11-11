package KafkaClasses

import org.apache.kafka.clients.producer._
import java.util.{Date, Properties, Random}
import java.util.concurrent.TimeUnit
import java.lang.Thread
import java.sql.Timestamp

class Produtor extends Thread{

    var letras: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    var placas = new Array[String](10);

    for( a <- 0 to (placas.length-1) ){

      placas(a) = gerarPlaca()

    }

    def gerarPlaca() : String = {

      var tamanhoLetras : Int = 3;
      var tamanhoNumeros : Int = 4;

      var i : Int = 0;
      var palavra : String = "";

      var rand : Random = new Random();

      for( i <- 0 until tamanhoLetras){

        var numero = rand.nextInt(5);

        palavra+= letras.substring(numero,numero+1);


      }

      palavra+="-";

      for(i <- 0 until tamanhoNumeros){

        var numero = rand.nextInt(5);

        palavra+= Integer.toString(numero);

      }

      return palavra;

    }

    def publicarPlaca() {

      var props : Properties = new Properties();

      props.put("bootstrap.servers","localhost:9092");
      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
      props.put("acks","1")

      var i = 0;

      var rand : Random = new Random();

    var tuplas : Array[(String,Int)] = Array(("A",5),("B",1),("C",2),("D",10),("E",5),("F",6),("G",15),("H",9),("I",10),("J",20));

      while(true){

        var rand = new Random();

        var placa = tuplas(rand.nextInt(10));

        var producer : Producer[String,String] = new KafkaProducer(props);

        var date = new Date();
        var timestamp = new Timestamp(date.getTime());

        var record : ProducerRecord[String,String] = new ProducerRecord("placas",placa.toString())

        producer.send(record);
        producer.close();

        println("Placa produzida: "+placa);

        TimeUnit.MILLISECONDS.sleep(rand.nextInt(10)*10);

      }

    }

    override def run() = {

      publicarPlaca();

    }

  }
