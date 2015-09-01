(ns {{project-ns}}.core
  (:require [compojure.core :refer [GET defroutes]]
          [compojure.route :refer [not-found resources]]
          [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
          [hiccup.core :refer [html]]
          [hiccup.page :refer [include-js include-css]]
          [prone.middleware :refer [wrap-exceptions]]
          [ring.middleware.reload :refer [wrap-reload]]
          [environ.core :refer [env]]))

(def home-page
(html
 [:html

  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
  [:link {:href "//fonts.googleapis.com/css?family=Raleway:400,300,600" :rel "stylesheet" :type "text/css"}]
   (include-css "css/skeleton.css")
   (include-css "css/normalize.css")
   (include-css (if (env :dev) "css/site.css" "css/site.min.css"))
   ]

  [:body

  [:div.container

  [:div.row
  [:div {:class "one-half column" :style "margin-top: 25%"}
    [:h4 "Super Hello"]
    [:p "This page is a ultra simple page"]]]

    [:div.row
   [:div#app
    ; [:h3 "ClojureScript has not been compiled!"]
    ; [:p "please run "
    ;  [:b "lein figwheel"]
    ;  " in order to start the compiler"]
     ]
   (include-js "js/app.js")]

   ]]]))

(defroutes routes
(GET "/" [] home-page)
(resources "/")
(not-found "Not Found"))

(def app
(let [handler (wrap-defaults #'routes site-defaults)]
  (if (env :dev) (-> handler wrap-exceptions wrap-reload) handler)))
