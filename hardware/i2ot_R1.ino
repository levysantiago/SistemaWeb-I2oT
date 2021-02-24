#include <SPI.h>
#include <MFRC522.h>
#include <ArduinoJson.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

/*RC522 (RFID READER) CONFIGURATION*/
/*D1 R1*/
#define SS_PIN D10
#define RST_PIN D9
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance.
char st[20];

/*WIFI CONFIGURATION*/
WiFiServer server(80);
const char* ssid = "SSID";
const char* password = "PASSWORD";
int buzzer = D3;

/*HTTP CLIENT*/
HTTPClient http;

/*JSON CONFIGURATION*/
StaticJsonBuffer<100> jsonBuffer;
String json;
JsonObject& root = jsonBuffer.createObject();

void setup()
{
  Serial.begin(9600);
  pinMode(buzzer, OUTPUT);
  delay(10);

  // Connect to WiFi network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");

  // Start the server
  server.begin();
  Serial.println("Server started");

  // Print the IP address
  Serial.print("Use this URL : ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");

  SPI.begin();      // Inicia  SPI bus
  mfrc522.PCD_Init();   // Inicia MFRC522
  delay(1000);

  Serial.println("Aproxime o seu cartao do leitor...");
  Serial.println();
  if (!root.success()) {
    Serial.println("parseObject() failed");
    return;
  }
}

void loop()
{
  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent())
  {
    return;
  }
  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial())
  {
    return;
  }
  tone(buzzer, 3000, 100);

  //Mostra UID na serial
  Serial.print("\n\nUID da tag :");
  String conteudo = "";
  byte letra;
  for (byte i = 0; i < mfrc522.uid.size; i++)
  {
    Serial.print(mfrc522.uid.uidByte[i], HEX);
    conteudo.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  Serial.print("\n\n");
  Serial.print("Mensagem : ");
  conteudo.toUpperCase();

  json = "";
  root["id"] = 2;
  root["nome"] = "RfidReader_2";
  root["local"] = "Sala18_2";
  root["tag_lida"] = conteudo;
  root.printTo(json);
  Serial.println(json);
  jsonBuffer.clear();
  JsonObject& root = jsonBuffer.createObject();

  if (WiFi.status() == WL_CONNECTED) {
    http.begin("http://192.168.43.27:5001/rfid");
    int httpCode = http.POST(json);
    if (httpCode > 0) { //Check the returning code
      Serial.println(httpCode);
      String payload = http.getString();
      Serial.println(payload);
    }else{
      Serial.println("Nenhum retorno");
    }
    http.end();

    tone(buzzer, 3000, 100);
    delay(100);
    tone(buzzer, 3000, 100);
  }

  delay(1000);
}
