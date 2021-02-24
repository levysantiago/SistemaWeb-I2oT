# Hardware

The hardware of this project consists of two Wemos boards. In this project I used both the Wemos D1 R1 version and Wemos D1 R2, the differences are minimal, basically the numeration of the board pins and some internal config.
But the idea is that you can use any version, you just need to configure the right way.

## Before running the code to the board

### Instaling board dependences

To deploy the code to the wemos board you'll need to download and install the [Arduino Software](https://www.arduino.cc/en/Main/Software).

The next important dependence is the wemos board configuration. With the Arduino Software opened, you go on menu File/Preferences and inside the "Additional Boards Manager URLs:" you paste this link:

```
http://arduino.esp8266.com/stable/package_esp8266com_index.json
```

After that, you go on menu _Tools/Board/Boards Manager_, wait for the window to load, then you search for _esp8266_ and install the dependence **esp8266 by ESP8266 Community**. In my case I used the version _2.4.2_.

### Instaling libraries dependences

To install the **ArduinoJson**, you can go to _Tools/Manage Libraries_, wait for the window to load, then you search for _ArduinoJson_ and install the dependence **ArduinoJson**. In my case I used the version _5.13.3_.

You will also need the **MFRC522** to read the RFID cards with the RFID reader, you can go to the same wintow _Tools/Manage Libraries_, then you search for _MFRC522_ and install the dependence **MFRC522**. In my case I used the version _1.4.0_.

- In case something goes wrong with the libraries instalation, I also attached the .zip files here on github in `hardware/libraries/`. You can move them to the arduino root folder inside `libraries` folder, and extract them.

### Getting the code

The arduino codes are in the path [SistemaWeb-I2oT/hardware](https://github.com/Levysantiago/SistemaWeb-I2oT/tree/master/hardware). They are `i2ot_R1.ino` and `i2ot_R2.ino`. You can just open them with the Arduino Software and go to the next step.

- The `i2ot_R1.ino` follows the pin configuration of Wemos D1 R1 board.
- The `i2ot_R2.ino` follows the pin configuration of Wemos D1 R2 board.

### Configuring the platform

If you passed through the [Installing dependences](#installing-dependences) section, you already have the wemos board configuration installed in your Arduino Software.

#### D1 R1

To select the right configuration, you go on menu _Tools/Board_ and look for **LOLIN(WEMOS) D1 mini Lite**. Then you go on menu Tools again and config the **Upload Speed** to **115200**. The Board configuration on Tools menu should be this way:

```
Board: "LOLIN(WEMOS) D1 mini Lite"
Upload Speed: "115200"
CPU Frequency: "80 MHz"
Flash Size: "1M(no SPIFFS)"
Debug port: "Disabled"
Debug Level: "None"
lwIPVariant: "v2 Lower Memory"
VTables: "Flash"
Erase Flash: "Only Sketch"
```

#### D1 R2

To select the right configuration, you go on menu _Tools/Board_ and look for **LOLIN(WEMOS) D1 R2 & mini**. Then you go on menu Tools again and config the **Upload Speed** to **115200**. The Board configuration on Tools menu should be this way:

```
Board: "LOLIN(WEMOS) D1 R2 & mini"
Upload Speed: "115200"
CPU Frequency: "80 MHz"
Flash Size: "4M(1M SPIFFS)"
Debug port: "Disabled"
Debug Level: "None"
lwIPVariant: "v2 Lower Memory"
VTables: "Flash"
Erase Flash: "Only Sketch"
```

### Configuring the code

For the wemos board to be allowed to connect to your network, you should edit the following code part:

```
const char* ssid = "SSID";
const char* password = "PASSWORD";
```

- In _ssid_ you'll put your network/wifi name;
- The _password_ is the password to connect to your wifi;

Now you're able to send the code to the board.

**OBS.:** Remember, your server and application should be running now because after the code passed to the board, it'll begin to get the measures and send to the server.

**OBS.:** If you're on linux and the system isn't letting you access the board, you can try to give access by typing on the terminal the command below and replacing the _\[port\]_ with the port where the board is connected (usually USB0, ACM0...). You can check this on the Arduino Software too on menu _Tools/Port_.

```
$ sudo chmod a+rw /dev/tty/[port]
```

### Schematic

`Soon I'll be posting the schematic of the hardware`
