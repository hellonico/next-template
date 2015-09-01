#!/usr/bin/env bash
ps axj | grep lein | awk '{print $2}' | xargs kill -9  
