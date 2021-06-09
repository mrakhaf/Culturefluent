import cv2
import numpy as np
import json
import logging
from flask import Response
from tensorflow.keras.models import load_model
from tensorflow.python.lib.io import file_io
from tensorflow.keras.preprocessing import image


def prediction_1(request):

    model_file = file_io.FileIO(
        'gs://model-v1/Model Export ver1/1.h5', mode='rb')
    temp_model_location = '/tmp/temp_model.h5'
    temp_model_file = open(temp_model_location, 'wb')
    temp_model_file.write(model_file.read())
    temp_model_file.close()
    model_file.close()
    model = load_model(temp_model_location)

    if request.method == 'GET':
        return 'Send POST Request'
    elif request.method == 'POST':
        nparr = np.fromstring(request.data, np.uint8)
        img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        img = cv2.resize(img, (224, 224))
        img = np.reshape(img, (1, 224, 224, 3))
        img = img/255
        print('Success Resize')

        prediction = model.predict(img)
        prediction_list = prediction.tolist()

        response = {'data': prediction_list,
                    'status': 'true'}
        print(nparr)
        response_json = json.dumps(response)
        print(response_json)
        return Response(response=response_json, status=200, mimetype="application/json")
