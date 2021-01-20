curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Kacper", "lastname": "Surówka", "address": {"street": "street1"}}'
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Ada", "lastname": "Test", "address": {"street": "street2"}}'
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Michał", "lastname": "Test2", "address": {"street": "street3"}}'

curl localhost:9999/api/clients | jq
curl localhost:9999/api/clients | python -m json.tool