import java.time.Duration;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

/**
 * A skeleton for a RIoT application: Create a timer that sends a "Tick" message
 * each second, then print it to the console.
 */
public class Application {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException {
        ActorSystem system = ActorSystem.create("$name$");
        Materializer mat = ActorMaterializer.create(system);

        // Example: This timer source sends a String each second:
        Source<String, ?> tickSource = Source.tick(Duration.ZERO, Duration.ofSeconds(1), "Tick!");

        // This sink will output the values returned straight to the console:
        Sink<String, ?> stdOutSink = Sink.foreach(o -> System.out.println(o));

        // Streams are built by a Materializer (mat), and go from a source (via a number
        // of flow steps) to a sink:
		tickSource.to(stdOutSink).run(mat);

	    // Use RIoT's Builders (e.g. riot.GPIO, riot.I2C) or Akka Streams components
	    // from other projects (e.g. Alpakka) to create additional steps, and use them
	    // in your streams.

	}

}
