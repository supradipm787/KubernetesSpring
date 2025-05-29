#!/usr/bin/env bash
#set -euo pipefail

set -uo pipefail  # Omit `-e`

run_step() {
  echo "Running $1"
  if $1; then
    echo "$1 succeeded"
  else
    echo  "$1 failed"
  fi
}

cd "$(dirname "$0")"

echo "Installing kind"
run_step ../config/kind/deploy.sh
echo "Waiting for state stabalization for kind"
sleep 5

echo "Installing ingress"
run_step ../config/ingress/deploy.sh
echo "Waiting for state stabalization for ingress"
sleep 5

echo "Installing monitoring"
run_step ../config/monitoring/deploy.sh
echo "Waiting for state stabalization for monitoring"
sleep 5

echo "Installing postgres"
run_step ../config/postgres/deploy.sh
echo "Waiting for state stabalization for postgres"
sleep 60

echo "Installing populate_db"
./populate-db.sh
echo "Populate db script executed"

