HIGHFLIP SERVER
====================================
# Introduction

# How to use
Directly launch highflip server with jar file in the bash environment. 
can use "highflip.server.adaptor.path" property to specify the adaptor jar file path
```bash
java -jar highflip-server-*.jar --highflip.server.adaptor.path=<filepath_adaptor.jar>
```



# Default Adaptor

- PlatformAdaptor: DefaultPlatformAdaptor
- AuthenticationAdaptor: ByPassAuthenticationAdaptor
- JobAdaptor: DumbJobAdaptor
- TaskAdaptor: DumbTaskAdaptor
- DataAdaptor: FixedSingleDataAdaptor
- PartnerAdaptor: FixedPartnerDataAdaptor
- AlgorithmAdaptor: AlgorithmNameListAdaptor
- UserAdaptor: FixedSingleUserAdaptor





