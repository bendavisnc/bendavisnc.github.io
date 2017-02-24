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
      ; [:script {:src "https://code.jquery.com/jquery-2.2.4.js" :integrity "sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=", :crossorigin "anonymous"}]
      [:link {:rel "stylesheet", :href "css/style.css"}]
    ]
    [:body
      (container dynamic-content)
    ]))
