#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

export PGPASSWORD=$(kubectl get secrets/localdb-secret -n postgres --template={{.data.password}} | base64 -d)
export PGUSER=$(kubectl get secrets/localdb-secret -n postgres --template={{.data.username}} | base64 -d)
export PGHOST=localhost
export PGDATABASE=localdb

echo "In populate-db.sh script post export of PGDatabase"
psql -a -f ../dat/data.sql
