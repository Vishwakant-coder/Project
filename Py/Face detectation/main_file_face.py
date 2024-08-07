import cv2
import numpy as np
from os import listdir
from datetime import datetime
from os.path import isfile, join

# 

print("1. Send Attendance file on your Whatsapp")
print("2. Reset the Attendance sheet")
print("3. Take attendance")
choise=int(input("Enter your choise : "))
pw_list=[]

#

def send_file():
    # make in furture   
    pass

def reset_file():
    Course=input("Enter the Course and Section name : ")
    with open("D:\\PROJECT\\Attend_Confirm.txt", "a+") as f:
        f.truncate(0)
        now = datetime.now()
        Date = now.strftime("%d/%m/%Y")
        Time = now.strftime("%H:%M:%S")
        f.writelines(f"{Course}  ,  {Date}  ,  {Time}\n\n\n")
        print("File update")
#
if choise==1:
    pw=input("Enter the passwor :")
    number=input("Enter your number :")
    if pw in pw_list:
        send_file()
    else:
        print("Worng password")
#
if choise==2:
    pw=input("Enter the passwor :")
    if pw in pw_list:
        reset_file()
    else:
        print("Worng password")

#

if choise==3:
    adi='0'
#   Face Recognition Model Training
    while (adi !='taken'):

        data_path = 'D:/PROJECT/face/'
        onlyfiles = [f for f in listdir(data_path) if isfile(join(data_path, f))]

        Training_Data, Labels = [], []

        for i, files in enumerate(onlyfiles):
            image_path = join(data_path, files)
            images = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
            Training_Data.append(np.asarray(images, dtype=np.uint8))
            Labels.append(i)

        Labels = np.asarray(Labels, dtype=np.int32)

        model = cv2.face.LBPHFaceRecognizer_create()

        model.train(np.asarray(Training_Data), np.asarray(Labels))
#                   Complete...
#       create a list of students of class
        adi_list=[]
        file= open("D:\\PROJECT\\STDData_sheet.txt", "r")
        for line in file:
            line_strip=line.strip()
            adi_list.append(line_strip)
        # print(name_list)
            
#                   Complete...
            
#
           
        print("Length of Adimission No. is 10")
        adi=input("Enter Your ADIMISSION No. : ")

#             Complete...

#

        def mark_attendance(adi):
            with open("D:\\PROJECT\\Attend_Confirm.txt", "a") as f:
                now = datetime.now()
                Date = now.strftime("%d/%m/%Y")
                Time = now.strftime("%H:%M:%S")
                f.writelines(f"{adi} , {Date} , {Time} , Preset\n")

#              Complete...
            
#
        
        face_classifier = cv2.CascadeClassifier('C:/Users/vishwa/AppData/Roaming/Python/Python312/site-packages/cv2/data/haarcascade_frontalface_default.xml')

        def face_detector(img, size=0.5):
            gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
            faces = face_classifier.detectMultiScale(gray, 1.3, 5)

            if len(faces) == 0:
                return img, []

            for (x, y, w, h) in faces:
                cv2.rectangle(img, (x, y), (x+w, y+h), (0, 255, 0), 2)
                roi = img[y:y+h, x:x+w]
                roi = cv2.resize(roi, (200, 200))

            return img, roi

#          Complete...

#
        if adi in adi_list :
            cap = cv2.VideoCapture(0)
            
            while True:
                ret, frame = cap.read()
                image, face = face_detector(frame)

                try:
                    face = cv2.cvtColor(face, cv2.COLOR_BGR2GRAY)
                    result = model.predict(face)
                    mark=""
                    mark1=""

                    if result[1] < 500:
                        confidence = int(100*(1-(result[1])/300))

                    if confidence > 82 and adi in adi_list:
                        
                        cv2.putText(image, "✔",(250, 450), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 255, 255), 2)
                        cv2.imshow('Face Cropper', image)
                        mark="match"
                        mark_attendance(adi)
                        print(f"{adi} Attendance is Marked.")
                        print("\n\n\n\n\n")

                    else:
                        cv2.putText(image, "Unknown", (250, 450), cv2.FONT_HERSHEY_COMPLEX, 1, (0, 0, 255), 2)
                        cv2.imshow('Face Cropper', image)
                        mark="unmatch"

                except:
                    cv2.putText(image, "Face Not Found", (250, 450), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 2)
                    cv2.imshow('Face Cropper', image)
                    mark="unmatch"
                    pass
                mark1="match"

                if cv2.waitKey(1) == 13 or mark==mark1 :
                    break

            cap.release()
            cv2.destroyAllWindows()

        else:
            print("Worng ID")
            print("\n\n\n\n\n")
#    Complete...