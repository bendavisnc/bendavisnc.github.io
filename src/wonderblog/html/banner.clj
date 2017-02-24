(ns wonderblog.html.banner
  (:require
    [wonderblog.constants :as constants]
    [hiccup.page :refer [html5]]
  )
  )

(defn html []
	[:div {:class "banner"}
	  [:a constants/sitename]
  ])

