(ns wonderblog.html.base
  (:require
    [wonderblog.constants :as constants]
    [hiccup.page :refer [html5]]
  )
  )

;;
;;
;; Defines the base html document that all of the generated pages make use of.

(defn- container [& content]
  "The basic container that wraps all of the dynamic content."
  [:div {:class "container"}
    content])


(defn render [& dynamic-content]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
    [:head
      [:title constants/sitename]
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=yes"}]
      [:script {:src "./js/jquery-3.1.1.js"}]
      [:script {:src "./js/d3.js"}]
      [:script {:src "./js/main.js"}]
      [:link 
        {
          :rel "stylesheet"
          :type "text/css"
          :media "screen and (max-width: 1199px)"
          :href "./css/mobile-style.css"
        }
      ]
      [:link 
        {
          :rel "stylesheet"
          :type "text/css"
          :media "screen and (min-width: 1200px)"
          :href "./css/style.css"
        }
      ]
    ]
    [:body
      (container dynamic-content)
    ]))

