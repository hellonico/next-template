#!/usr/bin/env bash
lein figwheel &
lein garden auto &
lein ring server-headless &
