# Serviço de criaçao da url encurtada

![image](https://github.com/user-attachments/assets/9ce14cba-c321-47db-a151-cc92c0519c07)


# Como utilizar

Para utilizar do serviço de redirecionamento primeiro é necessário encurtar a URL atráves do seguinte endpoint no api Gateway: https://fs3fpubwwl.execute-api.us-east-2.amazonaws.com/create com o seguinte body:


`
{
    "originalUrl": "" //Url a ser encurtada
    "expirationTime": "" // tempo de expiração em timestamp
}
`


retorno: 


`
{
    "shortened_url": "https://fs3fpubwwl.execute-api.us-east-2.amazonaws.com/a16d2f00"
}
`
