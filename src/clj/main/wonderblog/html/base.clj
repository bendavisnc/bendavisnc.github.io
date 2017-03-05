(ns wonderblog.html.base
  (:require
    [wonderblog.constants :as constants]
    [hiccup.page :refer [html5]]
  )
  )

(defn container [& content]
  "The basic container that wraps all of the dynamic content."
  [:div {:class "container"}
    content])

; <link rel="stylesheet" type="text/css" media="screen and (max-width: 360px)" href="portrait.css">


(defn render [& dynamic-content]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
    [:head
      [:title constants/sitename]
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=yes"}]
      ; [:script {:src "./js/bootstrap.js"}]
      ; [:link {:rel "stylesheet", :href "./css/bootstrap.css"}]
      [:script {:src "./js/jquery-3.1.1.js"}]
      [:script {:src "./js/d3.js"}]
      [:script {:src "./js/main.js"}]
      [:link 
        {
          :rel "stylesheet"
          :type "text/css"
          :media "screen and (max-width: 800px)"
          :href "./css/mobile-style.css"
        }
      ]
      [:link 
        {
          :rel "stylesheet"
          :type "text/css"
          :media "screen and (min-width: 801px)"
          :href "./css/style.css"
        }
      ]
    ]
    [:body
      (container dynamic-content)
    ]))

