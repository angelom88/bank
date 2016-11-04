### Bank sync exer
bank transfer synchronization using spring boot and MyBatis
- it runs under **http://localhost:8083/bank/transfer**

Example:

url: **http://localhost:8083/bank/transfer**

Method-type: POST

Header:Content-type: application/json

json: {"fromAccountNumber": "987654", "toAccountNumber": "123456", "transferAmount": "100"}


To execute integration test:
- execute command: **mvn clean test verify**
- JMeter test reports will be generated inside project folder:  
   
   - report: **<project-dir>/target/jmeter/report/index.html** (ie: **https://angelom88.github.io/bank-report/**)