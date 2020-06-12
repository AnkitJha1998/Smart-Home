# Smart-Home

Smart Home is a Major project of Mine for Partial fulfillment of B.Tech Degree in SRM Institute of Science and Technology. 
Here in this project, we aim to control home appliances using our Android App. In order to do so we have devised 3 modules which are to be implemented successfully. The three modules need to be set up successfully.
Below is the Architecture of a part of the final year project.

![GithubUpload](https://user-images.githubusercontent.com/37044020/84534598-d9486c80-ad07-11ea-9245-1d55dd2e3126.jpg)

<br>
<br>



## Arduino

Arduino is simplest to implement. For that just go to the Arduino Site and download suitable arduino IDE [here](https://www.arduino.cc/en/main/software). Then After installations, load up the SerialCom.ino File. 

![arduinoSerialCom](https://user-images.githubusercontent.com/37044020/84524005-dabd6900-acf6-11ea-8be3-f4cf4d44dc51.png)

This is how it will look like. Note the COM port from Tools-> Port. Now connect Arduino mega with following connections. 

### Connections
- Pin 22 -> In of relay board connecting tubelight 1
- Pin 23 -> In of relay board connecting tubelight 2
- Pin 24 -> In of relay board connecting tubelight 3
- Pin 25 -> In of relay board connecting Fridge
- Pin 26 -> In of relay board connecting AC

Towards 220V side, connect one end to the central i.e. Common pin, and other end to right of it i.e. NO(Normal Open) Side

After connections are done, go to the code, press Ctrl + U. Code will be uploaded on the Arduino.

## Python

For this you will need some libraries for it to work. Below are the libraries required.

- python-firebase
- requests
- pyserial

Go to the cmd, and type the following commands

``` 
pip instal python-firebase 
pip install pyserial
pip install requests
```

After that mention the COM port here as highlighted in the diagram

![pythonCode](https://user-images.githubusercontent.com/37044020/84532352-bddb6280-ad03-11ea-9ade-cbe128fa8edc.PNG)


After that, run the Python Script.

## Android

This just requires you to install the apk kept [here](https://github.com/AnkitJha1998/Smart-Home/tree/master/Android/APK). After installing it, the Sign In page comes up which looks like this.

![log InScreenSmall](https://user-images.githubusercontent.com/37044020/84522590-8ca76600-acf4-11ea-872b-7228ccdf36ac.png)

<br>

Now Sign Up in this app to get started.

![image](https://user-images.githubusercontent.com/37044020/84530256-70112b00-ad00-11ea-9477-c1f764f3b236.png)

<br>

When you log in, this page loads up

![signedInMenuSmall](https://user-images.githubusercontent.com/37044020/84522594-8d3ffc80-acf4-11ea-8f24-97f3f0fc4592.png)

<br>

From here you can load tubelight, refrigerator and AC status. Images show the layout

![tubelightsSmall](https://user-images.githubusercontent.com/37044020/84522595-8dd89300-acf4-11ea-8c60-4a6425b4ea59.png)

<b>Tubelight Page</b>

<br>
<br>

![refrigeratorSmall](https://user-images.githubusercontent.com/37044020/84522591-8d3ffc80-acf4-11ea-9133-1eb0205f5032.png)

<b>Refrigerator Page</b>

<br>
<br>

![acSmall](https://user-images.githubusercontent.com/37044020/84522565-7ef1e080-acf4-11ea-9cde-0c7f11a2521b.png)

<b>AC Page</b>

<br>
<br>

It is very easy to operate the application. All we need to do is to toggle the switches and this way we can turn on the appliance. 





