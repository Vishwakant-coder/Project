import cv2

face_classifier=cv2.CascadeClassifier('C:/Users/vishwa/AppData/Roaming/Python/Python312/site-packages/cv2/data/haarcascade_frontalface_default.xml')

def face_extractor(img):
    gray=cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    faces=face_classifier.detectMultiScale(gray,1.3,5)
    if len(faces) ==0:   # The len() function is used to return an integer 
        return None      # value which indicates the number of items in an object.
    
    for(x,y,w,h) in faces:
        cropped_face=img[y:y+h,x:x+w]
    return cropped_face


cap=cv2.VideoCapture(0)
count=0

print("Length of Adimission No. is 10")
adi=input("Enter Your ADIMISSION No. : ")
x=len(adi)
if x<15:
    try:
        with open("D:\\PROJECT\\STDData_sheet.txt", "a+") as f:
            f.write("\n") 
            f.write(adi)

        while True:
            ret, frame=cap.read()
            if face_extractor(frame) is not None:
                face=cv2.resize(face_extractor(frame),(200,200))
                face=cv2.cvtColor(face,cv2.COLOR_BGR2GRAY)

                file_name_path='D:/PROJECT/face/'+adi+str(count)+'.jpg'

                cv2.imwrite(file_name_path,face)

                cv2.putText(face,str(count),(50,50),cv2.FONT_HERSHEY_COMPLEX,1,(0,225,0),2)
                cv2.imshow('Face Cropper',face)

                count=count+1

            else:
                print("Face not Found")

            if cv2.waitKey(1)==13 or count==16:
                break

        print("Name update.") 

        cap.release()
        cv2.destroyAllWindows()
           # print(f"Total Numbers of Students are {count_student} ")
        print("Data set collection completed.")
    except:
        print("Name not update.")
else:
    print("Invalid Adimission No.")