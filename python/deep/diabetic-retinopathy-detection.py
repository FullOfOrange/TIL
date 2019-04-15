
# coding: utf-8

# In[149]:


import cv2
import numpy as np
import pandas as pd
import os
from matplotlib import pyplot as plt
from keras.models import Sequential
from keras.layers.core import Dense, Activation, Dropout
from keras.optimizers import SGD, RMSprop
from keras.utils import np_utils
from sklearn.model_selection import train_test_split


# In[72]:


def processing(img) :
    original = cv2.imread(img, cv2.IMREAD_COLOR)
    blue,green,red = cv2.split(original)
    clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8,8)) # 그린 채널만 사용하여 히스토그램평활화를 위한 clahe 알고리즘 적용
    img = clahe.apply(green)
    tmp = img

    # 침식과 팽창연산을 이용한 3번의 열림연산과 닫힘연산 수행 ->
    tmp_img = cv2.morphologyEx(img, cv2.MORPH_OPEN, cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(7,7)), iterations = 1)
    tmp_img = cv2.morphologyEx(tmp_img, cv2.MORPH_CLOSE, cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(7,7)), iterations = 1)
    tmp_img = cv2.morphologyEx(tmp_img, cv2.MORPH_OPEN, cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(13,13)), iterations = 1)
    tmp_img = cv2.morphologyEx(tmp_img, cv2.MORPH_CLOSE, cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(13,13)), iterations = 1)
    tmp_img = cv2.morphologyEx(tmp_img, cv2.MORPH_OPEN, cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(25,25)), iterations = 1)
    tmp_img = cv2.morphologyEx(tmp_img, cv2.MORPH_CLOSE, cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(25,25)), iterations = 1)

    # 원 영상에서 뺼셈을 한 후 clahe 알고리즘 적용
    img = cv2.subtract(tmp_img,img)
    img = clahe.apply(img)

    ret,threshold = cv2.threshold(img,15,255,cv2.THRESH_BINARY) # 이진화
    image, contours, hierarchy = cv2.findContours(threshold.copy(),cv2.RETR_LIST,cv2.CHAIN_APPROX_SIMPLE) # 노이즈 제거를 위한 Contours 적용
    mask = np.ones(img.shape[:2], dtype="uint8") * 255 # 필터마스크
    for cnt in contours:
        if cv2.contourArea(cnt) <= 200: # 영역이 200보다 작으면 mask적용
            cv2.drawContours(mask, [cnt], -1, 0, -1)
    image = cv2.bitwise_and(img, img, mask=mask) # mask 적용
    ret,threshold = cv2.threshold(image,15,255,cv2.THRESH_BINARY_INV) # threshold
    normal = cv2.erode(threshold, cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(3,3)), iterations=1) # 침식연산
    ret = cv2.subtract(tmp, ~normal)
    ret = cv2.resize(ret, (600,600))
    ret = ret.reshape(360000,)
    return ret


# In[142]:


# 이미지 데이터가 저장된 폴더를 열어 데이터 전처리후 행렬형태로 저장 및 엑셀로 저장되어있는 정답데이터 추출
def search(dirname):
    cnt = 0
    filenames = os.listdir(dirname)
    filelist = list()
    for filename in filenames:
        full_filename = os.path.join(dirname, filename)
        ext = os.path.splitext(full_filename)[-1]
        if ext == '.jpeg': 
            img = processing(full_filename)
            filelist.append(os.path.splitext(filename)[0])
            print(cnt)
            if(cnt==0) :
                x_data = img
                cnt += 1
            else :
                x_data = np.vstack([x_data, img])
                cnt += 1
    
    df = pd.read_csv("../trainLabels.csv",sep=",") # 정답데이터가 있는 엑셀파일이 저장된 경로 설정
    filelist = pd.DataFrame(filelist,columns=['image'])
    y_data = pd.merge(filelist,df)
    y_data = np.array(y_data['level'])
    y_data = y_data.reshape(y_data.shape[0],1)
    return x_data, y_data

x_data, y_data = search("../list/") # 학습데이터가 있는 엑셀파일이 저장된 경로 설정


# In[148]:


x_train, x_test, y_train, y_test = train_test_split(x_data, y_data, test_size = 0.2, random_state = 0)
x_train = x_train.astype('float32')
x_test = x_test.astype('float32')

x_train /= 255 # normalize
x_test /= 255

y_train = np_utils.to_categorical(y_train, 5) # 결과가 0~4이므로 멀티클래스화
y_test = np_utils.to_categorical(y_test, 5)


# In[162]:


# CNN 모델링
model = Sequential()
model.add(Dense(256, input_shape=(360000,), activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(256, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(128, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(5, activation='softmax'))
model.summary()


# In[163]:


model.compile(loss='categorical_crossentropy', optimizer=RMSprop(), metrics=['accuracy'])

history = model.fit(x_train, y_train, batch_size=256, epochs=20, verbose=1, validation_split=0.2)


# In[164]:


score = model.evaluate(x_test, y_test, verbose=1)
print('test score :', score[0])
print('test accuracy:', score[1])


# In[165]:


plt.plot(history.history['acc'])
plt.plot(history.history['val_acc'])
plt.title('model accuracy')
plt.ylabel('accuracy')
plt.xlabel('epoch')
plt.legend(['train','test'],loc='upper left')
plt.show()


# In[172]:


x_data = processing("../10003_left.jpeg") # 학습데이터가 아닌 임의의 데이터가 저장된 경로
x_data = x_data.reshape(1,360000)
y = model.predict_classes(x_data)


# In[173]:


print(y)

