#!/bin/bash

PAGE_INDEX=$1

mkdir -f out

drawio --export \
  --format png \
  --border 10 \
  --page-index $PAGE_INDEX \
  --output out/ \
  doc/drawio/