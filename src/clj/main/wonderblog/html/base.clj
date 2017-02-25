(ns wonderblog.html.base
  (:require
    [wonderblog.constants :as constants]
    [hiccup.page :refer [html5]]
  )
  )

(defn container [& content]
  [:div {:class "container"}
    content])

(defn render [& dynamic-content]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
    [:head
      [:title constants/sitename]
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
      [:script {:src "js/jquery-3.1.1.js"}]
      [:script {:src "js/d3.js"}]
      [:script {:src "js/main.js"}]
      [:link {:rel "stylesheet", :href "css/style.css"}]
    ]
    [:body
      (container dynamic-content)
    ]))
