from firebase import firebase as db
import serial
from threading import Thread

ard=serial.Serial("COM13",9600)
dbInstance = db.FirebaseApplication("https://androidproject-9dae5.firebaseio.com/", None)
tubes = [0,0,0]
fridge = 0
ac = 0

def retrieveData():
    global tubes
    global fridge
    global ac
    stat = dbInstance.get("/Tubelights", "")
    tubes = []
    tubes.append(stat["bedTube"])
    tubes.append(stat["hallTube"])
    tubes.append(stat["kitchenTube"])
    stat = dbInstance.get("/Refrigerator", "")
    fridge = stat["onOffSwitch"]
    stat = dbInstance.get("/AC", "")
    ac = stat["onStat"]

def writeData(arr):
    dbInstance.put('/Refrigerator', 'eggDistance', arr[0])
    #dbInstance.put('/Refrigerator', 'milkMeter', arr[2])
    #dbInstance.put('/Refrigerator', 'vegRotMeter', arr[1])
    print(arr)

def recvArrayOfReply(strData):
    retArr=[]
    tempStr=""
    for i in strData:
        if i == ',' or i == ';':
            retArr.append(float(tempStr))
            tempStr = ""
        else:
            tempStr = tempStr + str(i)
    return retArr

while True:
    retrieveData()
    print("TubelightStats\nBed,Hall,Kitchen\n", str(tubes), fridge, ac)
    strSend=""
    for i in tubes:
        strSend = strSend + str(i) + ":"
    strSend = strSend + str(ac) + ":" + str(fridge)
    if(len(strSend)<9):
        continue
    print("Sent Data:",strSend)
    strSend = strSend + "\n"
    ard.write(strSend.encode('utf-8'))
    strRecv=ard.readline()
    print(strRecv.decode('utf-8'))
    arr=recvArrayOfReply(strRecv.decode('utf-8'))
    t1=Thread(target=writeData,args=(arr,))
    t1.start()

