# simple-calc-java

Tinkering with gRPC.

# Protocol Documentation
<a name="top"/>

## Table of Contents

- [simple_calc.proto](#simple_calc.proto)
    - [Operands](#simplecalc.Operands)
    - [Result](#simplecalc.Result)
  
  
  
    - [SimpleCalc](#simplecalc.SimpleCalc)
  

- [Scalar Value Types](#scalar-value-types)



<a name="simple_calc.proto"/>
<p align="right"><a href="#top">Top</a></p>

## simple_calc.proto



<a name="simplecalc.Operands"/>

### Operands



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| operands | [double](#double) | repeated |  |






<a name="simplecalc.Result"/>

### Result



| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| value | [double](#double) |  |  |





 

 

 


<a name="simplecalc.SimpleCalc"/>

### SimpleCalc


| Method Name | Request Type | Response Type | Description |
| ----------- | ------------ | ------------- | ------------|
| add | [Operands](#simplecalc.Operands) | [Result](#simplecalc.Operands) |  |
| sub | [Operands](#simplecalc.Operands) | [Result](#simplecalc.Operands) |  |
| mult | [Operands](#simplecalc.Operands) | [Result](#simplecalc.Operands) |  |
| div | [Operands](#simplecalc.Operands) | [Result](#simplecalc.Operands) |  |

 



## Scalar Value Types

| .proto Type | Notes | C++ Type | Java Type | Python Type |
| ----------- | ----- | -------- | --------- | ----------- |
| <a name="double" /> double |  | double | double | float |
| <a name="float" /> float |  | float | float | float |
| <a name="int32" /> int32 | Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint32 instead. | int32 | int | int |
| <a name="int64" /> int64 | Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint64 instead. | int64 | long | int/long |
| <a name="uint32" /> uint32 | Uses variable-length encoding. | uint32 | int | int/long |
| <a name="uint64" /> uint64 | Uses variable-length encoding. | uint64 | long | int/long |
| <a name="sint32" /> sint32 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int32s. | int32 | int | int |
| <a name="sint64" /> sint64 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int64s. | int64 | long | int/long |
| <a name="fixed32" /> fixed32 | Always four bytes. More efficient than uint32 if values are often greater than 2^28. | uint32 | int | int |
| <a name="fixed64" /> fixed64 | Always eight bytes. More efficient than uint64 if values are often greater than 2^56. | uint64 | long | int/long |
| <a name="sfixed32" /> sfixed32 | Always four bytes. | int32 | int | int |
| <a name="sfixed64" /> sfixed64 | Always eight bytes. | int64 | long | int/long |
| <a name="bool" /> bool |  | bool | boolean | boolean |
| <a name="string" /> string | A string must always contain UTF-8 encoded or 7-bit ASCII text. | string | String | str/unicode |
| <a name="bytes" /> bytes | May contain any arbitrary sequence of bytes. | string | ByteString | str |

