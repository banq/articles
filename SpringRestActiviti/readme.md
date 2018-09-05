curl -u admin:admin http://localhost:8080/repository/deployments
List resources in a deployment:
curl -u admin:admin http://localhost:8080/repository/deployments/1/resources
curl -u admin:admin http://localhost:8080/repository/process-definitions
curl -u admin:admin http://localhost:8080/repository/process-definitions/simpleProcess:1:3/model

Start Process:
curl -u admin:admin -H "Content-Type: application/json" -d '{"processDefinitionKey":"simpleProcess", "variables": [ {"name":"person", "value":"John"}]}' http://localhost:8080/runtime/process-instances

List of variables by Process Instance ID
curl -u admin:admin http://localhost:8080/runtime/process-instances/4/variables

List of tasks:
curl -u admin:admin http://localhost:8080/runtime/tasks

Add Variables to Task (by Id):
curl -u admin:admin -H "Content-Type: application/json" -d '[{"name" : "newTaskVariable", "scope" : "local", "type" : "string", "value" : "This is variable Value"}]' http://localhost:8080/runtime/tasks/9/variables

dentity Links (by Id):
curl -u admin:admin http://localhost:8080/runtime/tasks/9/identitylinks

Complete Task (by Id):
curl -u admin:admin -H "Content-Type: application/json" -d '{"action" : "complete"}' http://localhost:8080/runtime/tasks/9

Historic process instance:
curl -u admin:admin http://localhost:8080/history/historic-process-instances/4
