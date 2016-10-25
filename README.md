### bank sync exer
bank transfer synchronization using spring boot and mybatis
- it runs under http://localhost:8083/bank/transfer

Example:

url: http://localhost:8083/bank/transfer

Method-type: POST
Header:Content-type: application/json

json: {"fromAccountNumber": "987654", "toAccountNumber": "123456", "transferAmount": "100"}