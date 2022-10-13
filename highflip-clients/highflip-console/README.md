HIGHFLIP CONSOLE
====================================================


# Tutorial


## Non-interactively Mode
```shell

java -jar -Dhighflip.url=grpc://user:pass@127.0.0.1:8571 highflip-console-*.jar job add --name=test --dag={} 

```

## Interactively Mode
```shell
java -jar highflip-console-*.jar
shell:> connect grpc://user:pass@127.0.0.1:8571
shell:> job add -name=
shell:> exit
```

# Commands
- dag
  + tojson
  + fromjson

- job
  + create
  + info
  + control
  + status
  + delete
  + list

- task
  + control
  + info
  + status
  + list

- data
  + create
  + upload
  + download
  + list
  + schema

- operator
  + list
  + get

- partner
  + create
  + list
  + info

- version