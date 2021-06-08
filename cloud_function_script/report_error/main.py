from flask import Response
from google.cloud import bigquery
import json
import numpy as np
import datetime


def report_error(request):

    client = bigquery.Client()
    table_id = "caramel-pulsar-315700.data_report.user_report"

    if request.method == 'GET':
        return 'Send POST Request'
    elif request.method == 'POST':
        temp = request.args.get('name')
        temp_img = request.data
        print(temp_img)
        nparr = np.fromstring(temp_img, np.uint8)
        print(str(nparr))
        time = datetime.datetime.now()
        my_list = [(str(nparr), False, temp, time)]

        table = client.get_table(table_id)
        print("Table has {} rows".format(table.num_rows))
        errors = client.insert_rows(table, my_list)  # Make an API request.
        if errors == []:
            response = {'status': 'updated'}
            response_json = json.dumps(response)
        else:
            response = {'status': 'not updated'}
        print(response_json)
        return Response(response=response_json, mimetype="application/json")
