(ns styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]))

(defstyles screen
  (let [body (rule :body)]
    (body
     {:font-family "Avenir Next"
      :font-size   "12px"
      :line-height 1.2})))
