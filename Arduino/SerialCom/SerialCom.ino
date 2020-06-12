int tubelight[3]={22,23,24};
int signalPin = 7;
int receiverPin=6;
int ref=25;
int ac=26;
int lastState[3]={1,1,1};
float duration,dist;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  for(int i=0;i<3;i++)
    pinMode(tubelight[i],OUTPUT);
  pinMode(ref,OUTPUT);
  pinMode(ac,OUTPUT);
  pinMode(signalPin,OUTPUT);
  pinMode(receiverPin,INPUT);
  
  digitalWrite(22,HIGH);
  digitalWrite(23,HIGH);
  
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(signalPin,LOW);
  delayMicroseconds(2);
  digitalWrite(signalPin,HIGH);
  delayMicroseconds(10);
  digitalWrite(signalPin,LOW);
  duration=pulseIn(receiverPin,HIGH);
  dist=(duration*0.0343)/2;
  
  
  if(Serial.available()){
  String input=Serial.readString();
  int rep=0;
  for(int i=0;i<3;i++,rep+=2)
  { 
    int sig=(input[rep]-48)==1?0:1;
    digitalWrite(tubelight[i],sig);
    lastState[i]=sig;
  }
  int sig1=(input[rep]-48)==1?0:1;
  digitalWrite(ac,sig1);
  rep+=2;
  sig1=(input[rep]-48)==1?0:1;
  digitalWrite(ref,sig1);
  // EggDistance, MilkMeter , vegRotMeter
  String distStr=String(dist);
  String toReturn=distStr;
  toReturn=toReturn;
  Serial.println(toReturn);
  }
  else
    for(int i=0;i<3;i++)
      digitalWrite(tubelight[i],lastState[i]);
}
