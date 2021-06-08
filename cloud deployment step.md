Cloud Function for Prediction:

Create bucket to store the model:
-Region
-asia-southeast2
-standard

Create cloud function:
-asia-southeast2
-memory : 1Gb
-timeout : 60s
-HTTP triggered
-Allow unauthenticated
-Write the code (main.py and requirement.txt)
-add permission for function invoker.

Cloud Function for Report Error:

Create bigquery table:
-Create Dataset
-Create table :
-instrument : STRING, REQUIRED
-status : BOOLEAN, REQUIRED
-correction_name : STRING, REQUIRED
-time : TIMESTAMP, REQUIRED
-table expiration : NEVER

Create cloud function:
-asia-southeast2
-memory : 1Gb
-timeout : 60s
-HTTP triggered
-Allow unauthenticated
-Write the code (main.py and requirement.txt)
-add permission for function invoker.
