import KafkaClasses.{PlacasCEPProdutor, Produtor};


object Main extends App {

  //var prod : Produtor = new Produtor()

  var prod : PlacasCEPProdutor = new PlacasCEPProdutor()

  prod.start()

}
