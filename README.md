# Projeto de mock

Este projeto simula dados fictícios para alimentar outro endpoint.

## Endpoint
**GET** */{id}/transacoes/{ano}/{mês}*

**{id}** - Id do usuário, invervalo entre 1000~1000000.

**{ano}** - valor do ano, inteiro.

**{mês}** - valor do mês, inteiro, intervalo entre 1~12.

## Exemplo
GET /1000/transacoes/2021/05
```json
[
    {
        "descricao": "pinaqu romilajo nedizi yenato",
        "data": "1622556977878",
        "valor": -5000
    },
    {
        "descricao": "qifejofe mujoçi gizuyu dadociçi",
        "data": "1622586297685",
        "valor": -10000
    }
]
```
