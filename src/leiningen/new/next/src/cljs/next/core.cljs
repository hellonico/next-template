(ns {{project-ns}}.core
  (:require [reagent.core :as reagent :refer [atom]]
          [reagent.session :as session]
          [secretary.core :as secretary :include-macros true]
          [goog.events :as events]
          [shodan.console :as console :include-macros true]
          [ajax.core :refer [GET POST]]
          [goog.history.EventType :as EventType])
(:import goog.History))

;; -------------------------
;; Views

(defn home-page []
[:div [:h2 "Home Page hello"]
[:div [:a.button.button-primary {:href "#/about"} "go to about page"]]])

(defn about-page []
[:div [:h2 "About hello"]
; [:div [:a.button.button-primary {:on-click
;   #(GET
;       "http://localhost:4567/example/simple.beans"
;       {:handler (fn[e] (console/info e))
;        :error-handler (fn[e] (console/error e))}
;        )
;        } "Price"]]
[:div [:a.button.button-primary {:href "#/"} "go to the home page"]]])

(defn current-page []
[:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
(session/put! :current-page #'home-page))

(secretary/defroute "/about" []
(session/put! :current-page #'about-page))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
(doto (History.)
(events/listen
 EventType/NAVIGATE
 (fn [event]
   (secretary/dispatch! (.-token event))))
(.setEnabled true)))

;; -------------------------
;; Initialize app
(defn mount-root []
(reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
(hook-browser-navigation!)
(mount-root))
