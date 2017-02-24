(ns wonderblog.html.banner
  (:require
    [wonderblog.constants :as constants]
    [wonderblog.html.bannerlinks :as bannerlinks]
    [hiccup.page :refer [html5]]
  )
  )

(defn html []
	[:div {:class "banner"}
	  [:a {:class "sitelink"} constants/sitename]
	  (bannerlinks/html)
  ])

