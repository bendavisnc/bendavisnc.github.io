(ns wonderblog.html.core
  (:require
    [wonderblog.html.about :as about-html]
    [hiccup.page :refer [html5]]
  )
  )

(defn render []
  (about-html/render))
