$name$
----------------
$if(generate_eclipse_project_files.truthy)$
Note: To generate an Eclipse configuration file, run the following in this directory:
```
sbt eclipse
```
$endif$
$if(generate_ensime_configuration_files.truthy)$
Note: To generate an Ensime configuration file, run the following in this directory:
```
sbt ensimeConfig
```
$endif$

The <code>Application</code> class in the default package is a Java main class that briefly sets up [Akka Streams][streams] for you. 
You can then instantiate further RIoT Akka Streams components by using the RIoT builders, e.g.:

```
// General Purpose I/O pins: Input Source, Output flow step, Output Sink

Source<State, NotUsed> gpio15 =     
        GPIO.in(15).withPullupResistor().asSource(system, mat);

Flow<State, State, NotUsed> gpio7 =
        GPIO.out(7).initiallyHigh().asFlow(system);

Sink<GPIO.State, NotUsed> gpio9 =
        GPIO.out(9).initiallyLow().shuttingDownLow().asSink(system);



// I2C: Raw device sink, Flow step with custom driver logic

final Sink<RawI2CProtocol.Command, NotUsed> rawSink = 
        I2C.rawDevice().onBus(1).at(0x12).asSink(system);
        
Flow<MyDriver.InputMessage, MyDriver.OutputMessage, NotUsed> myDevice = 
        I2C.device(myDriverConfig).onBus(1).at(MyDriver.ADDRESS).asFlow(system);
// where 'MyDriver' is a class encapsulating the device's specific I2C protocol
        
        
        
// SPI and more coming in future releases...
``` 

These can be mixed with Akka treams components from other sources, such as the [Alpakka Project][alpakka], which contains a number of community-contributed integration components, for example an [MQTT][mqtt] sink and source.

The components can then be assembled into streams and run:

```
tickSource.via(gpio7).to(logSink).run(mat);
```

RIoT comes with a tool (RIoT Control) which simplifies deployment to your Raspberry Pi. This tool is already preconfigured in this project. Set-up the name of your device and the user credentials to use for deployment in the build.sbt file:

```
riotTargets := Seq(
  riotTarget("raspberrypi", "pi", "raspberry")
)
```
Then run <code>sbt riotRun</code> to compile your code, copy it to the device, and run it locally. 

Once you're happy with the result, just run <code>sbt riotInstall</code> to set up your code as a service, which will automatically start when the device boots.

