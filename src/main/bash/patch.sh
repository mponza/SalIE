#!/usr/bin/env bash


patch ext/entity2vec/utils.py < ext/patches/entity2vec/utils.patch.txt
patch ext/entity2vec/entity_models.py < ext/patches/entity2vec/entity_models.patch.txt